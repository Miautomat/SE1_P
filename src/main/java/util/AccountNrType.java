package util;

import java.io.Serializable;

/**
 * @author Mieke Narjes 04.12.16
 */
@SuppressWarnings("serial")
public class AccountNrType implements Serializable {
    /**
     * @see http://snipplr.com/view/15322/iban-regex-all-ibans/
     */
    private static final String AccountNr_Pattern = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}";
    private String accountNr;
    
    public AccountNrType(String accountNr) {
        if (!isValidAccountNr(accountNr)) {
            throw new IllegalArgumentException("not a valid accountNr");
        }
        this.accountNr = accountNr;
    }
    
    public String getAccountNr() {
        return accountNr;
    }
    
    public static boolean isValidAccountNr(String accountNr) {
        return accountNr.matches(AccountNr_Pattern);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountNr == null) ? 0 : accountNr.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AccountNrType other = (AccountNrType) obj;
        if (accountNr == null) {
            if (other.accountNr != null)
                return false;
        } else if (!accountNr.equals(other.accountNr))
            return false;
        return true;
    }
}
