import java.util.*;

public class ATM {
	HashMap<Integer, Double> accounts;
	
	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.openAccount(123);
		System.out.println(atm.checkBalance(123) == 0);
		atm.openAccount(123, 5);
		System.out.println(atm.checkBalance(123) == 0);
		System.out.println(atm.depositMoney(123, 10));
		System.out.println(!atm.depositMoney(123, -5));
		System.out.println(atm.withdrawMoney(123, 3));
		System.out.println(!atm.withdrawMoney(123, -999));
		System.out.println(atm.checkBalance(123) == 7);
		System.out.println(!atm.withdrawMoney(123, 8));
		
		// Ensure openAccount only works once existing account is closed
		atm.openAccount(123, 99999);
		System.out.println(atm.checkBalance(123) != 99999);
		atm.closeAccount(123); // fails because balance is not zero
		atm.openAccount(123, 99999);
		System.out.println(atm.checkBalance(123) != 99999);
		atm.withdrawMoney(123, atm.checkBalance(123));
		atm.closeAccount(123);
		atm.openAccount(123, 99999);
		System.out.println(atm.checkBalance(123) == 99999);
		
	}
	public ATM() {
		accounts = new HashMap<Integer, Double>();
	}
	public void openAccount(int accountNum) {
		openAccount(accountNum, 0);
	}
	public void openAccount(int accountNum, double deposit) {
		if(accounts.containsKey(accountNum)) return;
		accounts.put(accountNum, deposit);
	}
	public void closeAccount(int accountNum) {
		if(!accounts.containsKey(accountNum)) return;
		if(accounts.get(accountNum) != 0) return;
		accounts.remove(accountNum);
	}
	public double checkBalance(int accountNum) {
		if(!accounts.containsKey(accountNum)) return 0;
		return (double)Math.round(accounts.get(accountNum) * 100) / 100; // Round to fix floating point error
	}
	public boolean depositMoney(int accountNum, double amount) {
		if(amount < 0) return false;
		if(!accounts.containsKey(accountNum)) return false;
		accounts.put(accountNum, accounts.get(accountNum) + amount);
		return true;
	}
	public boolean withdrawMoney(int accountNum, double amount) {
		if(amount < 0) return false;
		if(!accounts.containsKey(accountNum)) return false;
		double current = accounts.get(accountNum);
		if(amount > current) return false;
		accounts.put(accountNum, current - amount);
		return true;
	}
}
