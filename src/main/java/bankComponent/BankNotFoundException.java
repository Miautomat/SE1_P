package bankComponent;

/**
 * @author Mieke Narjes 05.12.16
 */
public class BankNotFoundException extends Exception {
    private static final long serialVersionUID = 7218522510544802607L;
    
    public BankNotFoundException(String bankNr) {
        super("Bank with ID " + bankNr + "was not found");
    }
    
    public BankNotFoundException(int bankId) {
        super("Bank with ID " + bankId + "was not found");
    }
}
