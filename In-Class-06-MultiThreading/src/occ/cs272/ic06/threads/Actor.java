package occ.cs272.ic06.threads;
/**
 *  CS 272 Example: Actor.java
 *
 *  Creates two threads which say their names and then sleep.  
 *  The testing main method thread also writes its name and sleeps.
 *
 *  @author Stephen Gilbert
 *  @version Spring 2016
 *
 *  TODO: Change the sleep times for threads Brad and Angie
 *  to 300 and 200 milliseconds respectively. How does
 *  the output change when you rerun the example?
 *
 *  TODO: What do you think will happen if you remove
 *  all of the sleep() statements from the program? Try it and see if
 *  you are right. Can you count on this behavior at all times?
 */


public class Actor implements Runnable
{
    // Time for this thread to sleep
    @SuppressWarnings("unused")
    private int time;
    @SuppressWarnings("unused")
    private String name;

    /**
     * @param name the name of this runnable.
     * @param time the time for this runnable.
     */
    public Actor(String name, int time)
    {
        this.name = name;
        this.time = time;
    }

    public void run()
    {
        // TODO: Write a loop from 1..5; Print the name + the iteration
        for (int i =1; i <= 5; ++i) {
            System.out.println(i + ". I'm " + name);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        
        // TODO: Sleep for time miliseconds in the loop
        
    }

    /**
     * A main method to test out our Runnable class.
     * THIS IS ONLY A TEST METHOD
     * @param args the command line.
     */
    public static void main(String args[])
    {
        // TODO: Create two Actor objects: brad and angie
        Actor brad = new Actor("Brad", 100);
        Actor angie = new Actor("Angie", 0);
        
        Thread bt = new Thread(brad);
        Thread at = new Thread(angie);
        
        // TODO: Start brad and angie running
        bt.start(); at.start();
        
        // Also, display the name of the main thread
        for (int i = 1; i <= 5; i++)
        {
            // What is the name of the current thread?
            System.out.println(Thread.currentThread().getName() + " " + i);
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                return;
            }
        }
    }
}
