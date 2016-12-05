package accountComponent;

public class AccountNotFoundException extends Exception {
    private static final long serialVersionUID = -6685799243638572684L;
    
    public AccountNotFoundException(int account_id) {
        super("Account with ID " + account_id + "was not found");
    }
}
