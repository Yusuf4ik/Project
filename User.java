package BankProject;

import java.io.Serializable;

public class User implements Serializable {
    String FirstName;
    String LastName;
    int id;
    String login;
    int password;
    int balanceInRubles;
    int balanceInDollars;

    public User(String firstName, String lastName, int id, String login, int password) {
        FirstName = firstName;
        LastName = lastName;
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    protected int getBalanceInRubles() {
        return balanceInRubles;

    }

    protected int getBalanceInDollars() {
        return balanceInDollars;
    }

    public void setBalanceInRubles(int balanceInRubles) {
        this.balanceInRubles = balanceInRubles;
    }
    public void getBalanceInRublesFromUsers(int money){
        balanceInRubles+=money;
    }

    public void setBalanceInDollars(int balanceInDollars) {
        this.balanceInDollars = balanceInDollars;
    }
    public void getBalanceInDollarsFromUsers(int money){
        balanceInDollars+=money;
    }
}
