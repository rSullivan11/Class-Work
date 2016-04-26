package occ.cs272.ic12;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *  CS 272 Example: ReverseClient.java
 *
 *  Connect to a server which reverses whatever
 *  the user inputs. Pass the host name on the command line?
 *
 */

public class ReverseClient
{
    public static void main(String [] args)
    {
        // We'll hard-code the host name here
        String host = "localhost"; 
        if (args.length > 0)
            host = args[0];

        final int PORT = 5678;
        try
        {
            // Streams we'll use in program
            InputStream fromServer = null;
            PrintWriter toServer = null;
            Socket server = new Socket(host, PORT);

            try
            {
                // TODO: Connect to the server using host and port
                System.out.println("Connected to ReverseSever host "
                                   + server.getInetAddress());

                // TODO: Open the two reading and writing streams
                // Hook up scanners to console (client) and server
                fromServer = server.getInputStream();
                toServer = new PrintWriter(server.getOutputStream());

                Scanner cin = new Scanner(System.in);
                Scanner sin = new Scanner(fromServer);
                
                // TODO: Read and process "conversation"
                while (true)
                {
                    System.out.print("# ");
                    System.out.flush();
                    // Read a line of input from client
                    String line = cin.nextLine();
                    
                    // Send it to the server
                    toServer.println(line);
                    
                    // Read the server response
                    String resp = sin.nextLine();
                    
                    // Print it
                    System.out.println(resp);
                    
                }
            }
            finally
            {
                fromServer.close();
                server.close();
                toServer.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
