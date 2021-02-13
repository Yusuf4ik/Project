package BankProject;

import ProjectLibrary.Books;
import ProjectLibrary.Readers;
import ProjectLibrary.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<User> listBank = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        try {
            FileInputStream inputStream = new FileInputStream("MyProjectBank");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            listBank = (ArrayList<User>) objectInputStream.readObject();
        } catch (Exception e) {
//            e.printStackTrace();

        }
        Auth();
    }

    public static void registration() throws IOException {
        System.out.println("Хотите зарегестрироватся? введите ваше имя, фамилию. ");
        String FirstName = reader.readLine();
        String LastName = reader.readLine();
        System.out.println("А так же введите ваш будущий login и пароль.");
        String login = reader.readLine();
        int pass = Integer.parseInt(reader.readLine());
        listBank.add(new User(FirstName, LastName, generateID(), login, pass));
        Serializable();
        AfterAuth();

    }


    public static void Auth() throws IOException {
        System.out.println("Привествуем вас в онлайн банке!");
        System.out.println("Если у вас уже есть аккаунт введите 1, чтобы зарегестрироватся 2.");
        int num = Integer.parseInt(reader.readLine());
        switch (num) {
            case 1:
                AuthUser();
                break;
            case 2:
                registration();
                break;
        }
    }

    public static void AfterAuth() throws IOException {

        System.out.println("Здравствуйте, пользователь!");
        System.out.println("Для проверки своего аккаунта в рублях введите 1.");
        System.out.println("Для проверки своего аккаунта в долларах введите 2.");
        System.out.println("Для пополнения счета нажмите 3.");
        System.out.println("Для проведения транзакций нажмите 4.");
        System.out.println("Для выхода из системы нажмите 9.");
        System.out.println("Если хотите вывести деньги со счета, 5");
        int num = (Integer.parseInt(reader.readLine()));
        try {


            switch (num) {
                case 1:
                    checkBalanceInRubles();
                    break;
                case 2:
                    checkBalanceInDollars();
                    break;
                case 3:
                    AddToBalance(listBank);

                    break;
                case 4:
                    Transactions();
                    exit();
                    break;
                case 5:
                    WithdrawMoney(listBank);
                    break;
                case 9:
                    exitSystem();
                    break;

            }
        } catch (Exception e) {
            System.out.println("Вы ввели неверные параметры");
        }

    }

    public static void Transactions() throws IOException {
        System.out.println("Для транзакций вам придется уточнить ваши данные :)");
        boolean answer = true;
        while (answer) {
            System.out.println("Введите ваш login и password");
            String name = reader.readLine();
            int pass = Integer.parseInt(reader.readLine());
            for (User user : listBank) {
                if (name.equals(user.getLogin()) && pass == user.getPassword()) {
                    for (User user1 : listBank) {
                        System.out.println(user1.getFirstName() + " " + user1.getId());
                    }
                    System.out.println("Кому вы хотите задонатить? Введите id счастливчика");
                    int id = Integer.parseInt(reader.readLine());
                    for (User user1 : listBank) {
                        if (id != 0 && id == user1.getId()) {
                            System.out.println("Введите сумму перевода");
                            int money = Integer.parseInt(reader.readLine());
                            System.out.println("Введите валюту(R-рубли, D-доллары)");
                            String currency = reader.readLine();
                            if (currency.equals("R")) {
                                if (money <= user.getBalanceInRubles()) {
                                    user1.setBalanceInRubles(user1.getBalanceInRubles() + money);
                                    Date date = new Date();
                                    System.out.println("Вы задонатили в " + date);
                                    user.setBalanceInRubles(user.getBalanceInRubles() - money);


                                } else {
                                    System.out.println("У вас недостаточно средств  в вашем балансе!");
                                }

                            }
                            if (currency.equals("D")) {
                                if (money <= user.getBalanceInDollars()) {
                                    user.getBalanceInRublesFromUsers(money);
                                    Date date = new Date();
                                    System.out.println("Вы задонатили в " + date);
                                    user.setBalanceInRubles(user.getBalanceInRubles() - money);

                                } else {
                                    System.out.println("У вас недостаточно средств  в вашем балансе!");
                                }
                            }
                            Serializable();


                        }
                    }
                    answer = false;


                } else if (!name.equals(user.getLogin()) && pass != user.getPassword()) {
                    System.out.println("Неверные данные, попробуйте ещё раз!");
                }


            }
        }
    }

    public static void checkBalanceInRubles() throws IOException {
        System.out.println("Простите за неудобства, но вам придется ввести свой пароль и логин)");
        System.out.println("Ваш логин пожалуйста!");
        String name = reader.readLine();
        System.out.println("Ваш пароль пожалуйста!");
        int pass = Integer.parseInt(reader.readLine());
        for (User user : listBank) {
            if (name.equals(user.getLogin()) && pass == user.getPassword()) {
                System.out.println(user.getBalanceInRubles());
                exit();
            }

        }
    }

    public static void checkBalanceInDollars() throws IOException {
        System.out.println("Простите за неудобства, но вам придется ввести свой пароль и логин)");
        System.out.println("Ваш логин пожалуйста!");
        String name = reader.readLine();
        System.out.println("Ваш пароль пожалуйста!");
        int pass = Integer.parseInt(reader.readLine());
        for (User user : listBank) {
            if (name.equals(user.getLogin()) && pass == user.getPassword()) {
                System.out.println(user.getBalanceInDollars());
                exit();
            }
        }

    }

    public static void AddToBalance(ArrayList<User> list) throws IOException {
        System.out.println("Введите сумму пополнения!");
        int addBalance = Integer.parseInt(reader.readLine());
        System.out.println(" Введите валюту(R-рубли, D-доллары)");
        String currency = reader.readLine();
        for (User user : listBank) {

            if (addBalance != 0) {


                if (currency.equals("R")) {
                    user.setBalanceInRubles(user.getBalanceInRubles() + addBalance);

                } else if (currency.equals("D")) {
                    user.setBalanceInDollars(user.getBalanceInDollars() + addBalance);

                }
                Serializable();
            } else {
                System.out.println("Вам нужно ввести корректную сумму(больше 0)!");
            }
        }

        exit();
    }

    public static void AuthUser() throws IOException {
        boolean answer = true;
        while (answer) {
            System.out.println("Введите ваш login и password");
            String name = reader.readLine();
            int pass = Integer.parseInt(reader.readLine());
            for (User user : listBank) {
                if (name.equals(user.getLogin()) && pass == user.getPassword()) {
                    System.out.println("Вы успешно вошли в аккаунт!");
                    answer = false;
                    AfterAuth();

                } else if (!name.equals(user.getLogin()) && pass != user.getPassword()) {
                    System.out.println("Неверные данные, попробуйте ещё раз!");
                }
            }

        }
        exit();
    }

    public static int generateID() {
        int id = (int) (Math.random() * 899) + 100000;

        boolean answer = checkID(id);

        if (answer) {
            return id;
        } else {
            return generateID();
        }
    }

    //Проверка на уникальность ID
    public static boolean checkID(int id) {
        for (User user : listBank) {
            if (id == user.getId()) {
                return false;
            }
        }
        return true;
    }

    static void exit() throws IOException {
        System.out.println("Если хотите выйти в главное меню, нажмите 1");
        String num = reader.readLine();
        if (num.equals("1")) {
            AfterAuth();
        }

    }

    static void exitSystem() throws IOException {
        Auth();
    }

    public static void Serializable() {
        try {
            FileOutputStream outputStream = new FileOutputStream("MyProjectBank");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(listBank);
            objectOutputStream.close();

        } catch (EOFException | FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WithdrawMoney(ArrayList<User> list) throws IOException {
        System.out.println("Простите за неудобства, но вам придется ввести свой пароль и логин)");
        System.out.println("Ваш логин пожалуйста!");
        String name = reader.readLine();
        System.out.println("Ваш пароль пожалуйста!");
        int pass = Integer.parseInt(reader.readLine());

        for (User user : list) {
            if (name.equals(user.getLogin()) && pass == user.getPassword()) {
                System.out.println("Ваш баланс в рублях: " + user.getBalanceInRubles() + ". Ваш баланс в Долларах: " + user.getBalanceInDollars());
            }
            else if(!name.equals(user.getLogin()) && pass != user.getPassword()){
                System.out.println("Неверные данные, попробуйте ввести снова");
                WithdrawMoney(list);
            }
        }
        System.out.println("Введите сумму вывода!");
        int addBalance = Integer.parseInt(reader.readLine());
        System.out.println(" Введите валюту(R-рубли, D-доллары)");
        String currency = reader.readLine();
        for (User user : listBank) {

            if (addBalance != 0) {


                if (currency.equals("R") && addBalance <= user.getBalanceInRubles()) {
                    user.setBalanceInRubles(user.getBalanceInRubles() - addBalance);

                } else if (currency.equals("D") && addBalance <= user.getBalanceInRubles()) {
                    user.setBalanceInDollars(user.getBalanceInDollars() - addBalance);

                }
                Serializable();
            } else {
                System.out.println("Вам нужно ввести корректную сумму(больше 0)!");
            }
        }

        exit();
    }

}
