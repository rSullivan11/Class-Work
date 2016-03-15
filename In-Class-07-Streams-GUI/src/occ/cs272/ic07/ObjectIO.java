package occ.cs272.ic07;
/**
 *  CS 272 Example: Illustrates object persistence
 *
 *  TODO: Modify ObjectIO.java so that the class Person does not
 *  implement the Serializable interface. What happens?
 */

import java.io.*;
public class ObjectIO
{
    public static void main(String [] args)
    {
        try
        {
            // 1. Create the three objects
            Person fred = new Person("Fred");
            Account general = new Account(fred, 110.0);
            Account savings = new SavingsAccount(fred, 500.0, 6.0);

            //  2. Write the objects out to a disk file
            ObjectOutputStream oos =
                new ObjectOutputStream(
                    new FileOutputStream("Objects.dat"));
            oos.writeObject(general);
            oos.writeObject(savings);
            oos.close();

            // 3. Read the objects in from disk
            ObjectInputStream ois =
                new ObjectInputStream(
                    new FileInputStream("Objects.dat"));
//           Account aGeneral = (Account)ois.readObject();
//            Account aSavings = (Account)ois.readObject();
            Object aGeneral = ois.readObject();
            Object aSavings = ois.readObject();

            // 4. Test the results and print some info
            if (aGeneral instanceof SavingsAccount)
                System.out.println("aGeneral account is a SavingsAccount");
            else if (aGeneral instanceof Account)
                System.out.println("aGeneral account is an Account");

            if (aSavings instanceof SavingsAccount)
                System.out.println("aSavings account is a SavingsAccount");
            else if (aSavings instanceof Account)
                System.out.println("aSavings account is an Account");
/*
            if (aGeneral.holder == aSavings.holder)
                System.out.println("The account holder, fred, is shared");
            else
                System.out.println("The account holder, fred, has been duplicated");
*/
            ois.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
    }
}

/**
 *  Helper classes. Note that data is not private, so that we can
 *  test if the reconstructed objects are shared after they are
 *  reconstructed.
*/

class Person implements Serializable
{
    String name;
    Person (String name)
    {
        this.name = name;
    }
}


class Account implements Serializable
{
    Person holder;
    double balance;
    Account(Person p, double amount)
    {
        holder = p;
        balance = amount;
    }
}

class SavingsAccount extends Account implements Serializable
{
    double rate;
    SavingsAccount(Person p, double amount, double r)
    {
        super(p,amount);
        rate = r;
    }
}

