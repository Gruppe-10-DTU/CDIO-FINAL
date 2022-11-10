package models;

import junit.framework.TestCase;

public class BalanceTest extends TestCase {

    public void testGetBalance() {
        Balance balance = new Balance(20);
        assertEquals(20, balance.getBalance());
    }

    public void testSetBalance() {
        Balance balance = new Balance(20);
        balance.setBalance(10);
        assertEquals(10,balance.getBalance());
        balance.setBalance(2);
        assertEquals(2, balance.getBalance());
    }
}