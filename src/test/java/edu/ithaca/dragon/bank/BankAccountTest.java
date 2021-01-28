package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        // valid case
        assertTrue(BankAccount.isEmailValid("a@b.com"));
        // empty case
        assertFalse( BankAccount.isEmailValid(""));
        // empty prefix and low level domain w/o @
        assertFalse( BankAccount.isEmailValid(".com"));
        // empty prefix w/ @ - boundry case
        assertFalse( BankAccount.isEmailValid("@s.com"));
        // empty domains w/o .
        assertFalse( BankAccount.isEmailValid("a@"));
        // empty top level domain w/ . - boundry case
        assertFalse( BankAccount.isEmailValid("name@email."));
        // empty low level domain w/o . - boundry case
        assertFalse( BankAccount.isEmailValid("email.mail@com"));

        // empty case w/ @ and . - boundry case
        // empty case w/o @ and w/ . - boundry case
        // empty case w @ and w/o . - boundry case

        // empty prefix w/o @ - boundry case

        // empty top level domain w/o . - boundry case
        // empty low level domain w/ . - boundry case
        // empty domains w/ . - boundry case

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}
