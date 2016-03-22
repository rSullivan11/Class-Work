package occ.cs272.ic06.threads;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * A bank account has a balance that can be changed 
 * by deposits and withdrawals.
 */
public class BankAccount
{
    /**
     * Constructs a bank account with a zero balance.
     */
    public BankAccount()
    {
        balance = 0;
    }

    /**
     * Deposits money into the bank account.
     * 
     * @param amount the amount to deposit
     */
    public void deposit(double amount)
    {
        balLock.lock();
            System.out.print(Thread.currentThread().getName() + 
                    " is depositing "  + amount);
            double newBalance = balance + amount;
            System.out.println(", new balance is " + newBalance);
            balance = newBalance;
            sufficientBalance.signalAll();
        balLock.unlock();
//        notify();
    }

    /**
     * Withdraws money from the bank account.
     * 
     * @param amount the amount to withdraw
     * @throws InterruptedException 
     */
    public void withdraw(double amount) throws InterruptedException
    {
//        if (balance < amount) {
//            try {
//                wait();
//            } catch (InterruptedException e) { e.getMessage(); }
//        }
        balLock.lock();
        try {
            while (balance < amount) 
                sufficientBalance.await();
            System.out.print(Thread.currentThread().getName() 
                    + " is withdrawing " + amount);
            double newBalance = balance - amount;
            System.out.println(", new balance is " + newBalance);
            balance = newBalance;
        } finally { balLock.unlock();}
    }

    /**
     * Gets the current balance of the bank account.
     * 
     * @return the current balance
     */
    public double getBalance()
    {
        return balance;
    }

    private double balance;
    private ReentrantLock balLock = new ReentrantLock();
    private Condition sufficientBalance = balLock.newCondition();
}
