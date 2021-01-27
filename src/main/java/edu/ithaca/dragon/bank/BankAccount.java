package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        //Make sure '@' exists or else return false. 
        if (email.indexOf('@') == -1){ 
            return false;
        }
        else if(email.isEmpty()){
            return false;
        }
        //Make sure '@' is not at first index. First index invalid is equiv class with boundry as index 0(non-inclusive)
        else if (email.indexOf('@') == 0){
            return false;
        }
        //Make sure '@' isn't at last index. Last index invalid is equiv class with boundry (non-inclusive) -1
        else if (email.indexOf('@') == email.length()-1){
            return false;
        }
        //Make sure '.' isn't at last index. Last index invalid is equiv class with max index as boundry
        else if (email.indexOf('.') == email.length()-1){
            return false;
        }
        //Make sure '@' is before '.'. This is also boundary case as '.' where the boundry '@' sets that of '.'
        else if (email.indexOf('.') - email.indexOf('@') <= 1){
            return false;
        }
        
        else {
            return true;
        }
    }
}
