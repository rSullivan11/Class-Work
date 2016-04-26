package occ.cs272.ic12;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *  CS 272 Example: ReverseServer.java
 *
 *  Listens on port 5678.  When a client connects, the server
 *  reverses whatever the client sends, and sends it back.
 *
 *  EX01: Modify the program to put the accept() statement and the
 *       code following it into an endless loop, so the server can
 *       accept another client as soon as the current client finishes.
 *
 *  EX02: Modify the program so the server can handle several clients
 *        simultaneously. After the server accepts a connection from
 *        the client, the server should create a separate thread to
 *        handle the communication with that client and loop back
 *        to the accept() statement, waiting for another client to connect.
 */
public class ReverseServer
{
    public static void main(String[] args)
    {
        try
        {
            // TODO: // fix these so the program works
            Socket client = null;
            ServerSocket server = null;
            InputStream input = null;
            PrintWriter output = null;

            try
            {
                // Step 1. Start the server and wait for someone to connect
                server = new ServerSocket(5678);
                System.out.println("Server started on port 5678");
                client = server.accept();

                // If you get here, then a client has connected,
                System.out.println("Reverse client connected on port 5678");

                // Step 2. Open streams in both directions.
                input = client.getInputStream();
                output = new PrintWriter(client.getOutputStream(), true);

                // Use a Scanner to read the data from the input stream
                Scanner cin = new Scanner(input);

                // Now, read from client, process data, and send it back to client
                while (cin.hasNext())
                {
                    String s = cin.nextLine();
                    System.out.println("Received from client : " + s);

                    int len = s.length();
                    char[] c = new char[len];
                    for (int i = 0; i < len ; i++)
                        c[i] = s.charAt(len - 1 - i);

                    System.out.println("Sent to client : " + new String(c));
                    output.println(c);
                }
            }
            finally
            {
                client.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
