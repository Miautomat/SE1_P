package application.util;

public class TransactionInfo {
    
    private Integer amount;
    private AccountNrType accountNr;
    
    private TransactionInfo() {}
    
    public TransactionInfo(Integer amount, AccountNrType accountNr) {
        this.amount = amount;
        this.accountNr = accountNr;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public AccountNrType getAccountNr() {
        return this.accountNr;
    }
}
