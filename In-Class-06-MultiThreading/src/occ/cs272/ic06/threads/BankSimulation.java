package occ.cs272.ic06.threads;
/**
 * 1. Creates 1 BankAccount with 200 signers
 * 2. 100 of the signers make 100 deposits, while the others
 *    make 100 withdrawals. 
 * 3. Every deposit and withdrawal is $ 100
 * 4. At the end, the Bank Balance should be 0.
*/
public class BankSimulation
{
   public static void main(String[] args)
   {
      BankAccount account = new BankAccount();
      final double AMOUNT = 100;
      final int REPETITIONS = 100;
      final int THREADS = 100;

      for (int i = 1; i <= THREADS; i++)
      {
         Depositor d = new Depositor(
            account, AMOUNT, REPETITIONS);
         Withdrawer w = new Withdrawer(
            account, AMOUNT, REPETITIONS);
         
         Thread dt = new Thread(d);
         Thread wt = new Thread(w);
         
         dt.start();
         wt.start();
      }
   }
}

