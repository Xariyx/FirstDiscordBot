package model;

public class User {
    private final long id;
    private long balance;

    public User(long id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return this.id + " " + this.balance;
    }
}
