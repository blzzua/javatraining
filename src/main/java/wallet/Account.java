package wallet;

public class Account {
    private String currency = "UAH";
    public double balance = 0d;

    double getBalance(){
        return this.balance;
    };
    void setBalance(double NewBalance){
        this.balance = NewBalance;
    };

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCurrency() {
        return currency;
    }
}
