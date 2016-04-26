package bigjava.ch22;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server that executes the Simple Bank Access Protocol.
 */
public class BankServer
{
    public static void main(String[] args) throws IOException
    {
        final int ACCOUNTS_LENGTH = 10;
        Bank bank = new Bank(ACCOUNTS_LENGTH);
        final int SBAP_PORT = 8888;
        try (ServerSocket server = new ServerSocket(SBAP_PORT))
        {
            System.out.println("Waiting for clients to connect...");
    
            while (true)
            {
                try (Socket s = server.accept())
                {
                    System.out.println("Client connected.");
                    BankService service = new BankService(s, bank);
                    Thread t = new Thread(service);
                    t.start();
                }
            }
        }
    }
}
