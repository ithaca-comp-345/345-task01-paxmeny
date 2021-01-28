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

    /**
     * Returns true if all chars in str are found in charSet, false otherwise
     * @param str String to verify
     * @param charSet Character set to verify against
     * @return True if str only contains chars in charSet, false otherwise
     */
    private static boolean isBuiltFrom(String str, String charSet) {
        String s;
        for (char c : str.toCharArray()) {
            s = "" + c;
            if (!charSet.contains(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if str has consecutive c's, false otherwise
     * @param str String to check for consecutive chars
     * @param c Char to check
     * @return True if consecutive c's, false otherwise
     */
    private static boolean hasConsecutive(String str, char c) {
        String s = "" + c + c;
        return str.contains(s);
    }

    /**
     * Returns true if:
     * only chars in prefix are (a-z), (0-9), '_', '.' and '-'
     * only chars in domain are (a-z), (0-9), and '-'
     * only chars in topLevelDomain are (a-z)
     * no leading or trailing '_', '.', '-' in the prefix
     * no leading or trailing '-' in the domain
     * no topLevelDomain with length < 2
     * no consecutive speical characters,
     * flase otherwise
     * @param email Address to verify
     * @return True if valid email, false otherwise
     */
    public static boolean isEmailValid(String email) {
        // allowed character sets
        String alpha, digit, special;
        alpha = "abcdefghijklmnopqrstuvwxyz";
        digit = "0123456789";
        special = "_.-";

        int prefixIndex = email.indexOf('@');
        int domainIndex = email.lastIndexOf('.');

        if (prefixIndex == -1 || domainIndex == -1) {  // if @ or . is missing return false
            return false;
        } 

        String prefix, domain, topLevelDomain;
        prefix = email.substring(0, prefixIndex);
        domain = email.substring(prefixIndex + 1, domainIndex);
        topLevelDomain = email.substring(domainIndex + 1);

        if (prefix.length() == 0 || domain.length() == 0 || topLevelDomain.length() < 2) {  // if prefix or domain length == 0, or topLevelDomain < 2
            return false;
        } else if (!isBuiltFrom(prefix, alpha + digit + special)) {  // if any char in prefix is not (a-z), (0-9), '_', '.' or '-' return false
            return false;
        } else if (!isBuiltFrom(domain, alpha + digit + "-")) {  // if any char in domain is not (a-z), (0-9), or '-' return false
            return false;
        } else if (!isBuiltFrom(topLevelDomain, alpha)) {  // if any char in topLevelDomain is not (a-z) return false
            return false;
        } else if (special.contains("" + prefix.charAt(0))) {  // if prefix leading special char return false
            return false;
        } else if (special.contains("" + email.charAt(prefixIndex - 1))) {  // if prefix trailing special char return false
            return false;
        } else if (domain.charAt(0) == '-') {  // if domain leading special char return false
            return false;
        } else if (email.charAt(domainIndex - 1) == '-') {  // if domain trailing special char return false
            return false;
        }

        // if consecutive special chars return false
        for(char c : special.toCharArray()) {
            if (hasConsecutive(email, c)) {
                return false;
            }
        }
        return true;
    }
}