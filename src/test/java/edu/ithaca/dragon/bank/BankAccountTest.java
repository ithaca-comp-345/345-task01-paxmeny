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

        assertEquals(100, bankAccount.getBalance()); //Makes sure a valid withdraw works
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300)); //Amount > Balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-1)); //Amount < 0
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
        assertFalse( BankAccount.isEmailValid("@.")) //middle and both sides boundry
        // empty case w/o @ and w/ . - boundry case
        assertFalse( BankAccount.isEmailValid("email.com")) //Left-middle boundry case
        // empty case w @ and w/o . - boundry case
        assertFalse( BankAccount.isEmailValid("email@mail")) //Middle-right boundry case 

        // empty prefix w/o @ - boundry case
        assertFalse( BankAccount.isEmailValid("mail")) //Left-middle and middle-right boundry case

        // empty top level domain w/o . - boundry case
        assertFalse( BankAccount.isEmailValid("@email")) //empty left already checked, middle to right boundry
        // empty low level domain w/ . - boundry case
        assertFalse( BankAccount.isEmailValid("email@.")) //middle to right boundry
        // empty domains w/ . - boundry case
        assertFalse(BankAccount.isEmailValid(".")) //left to middle and middle to right boundry

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
