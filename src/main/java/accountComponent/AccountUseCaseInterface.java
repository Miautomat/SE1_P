package accountComponent;

import bankComponent.BankNotFoundException;

public interface AccountUseCaseInterface {
    
    /**
     * processes a money-transfer from the adressor to the recipient. The
     * bookingStatistic of the bank should be increased after each transfer.
     * 
     * @param addressorId
     * @param recipientId
     * @param amount
     *            - has to be a positive int
     * @throws AccountNotFoundException
     *             if addressor or recipient weren't found
     */
    public void transfer(int addressorId, int recipientId, int amount)
        throws AccountNotFoundException, BankNotFoundException;
    
    /**
     * @return actual budget according to all bookings
     */
    public int getActualBudget(int accountId);
}
