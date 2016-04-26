package occ.cs272.ic12;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *  CS 272 Example: HeaderServer.java
 *
 * When an HTTP client connects, the server sends
 * the client's request and its request headers back to it.
 *
 *  EX01: What happens if you omit sending the status line?
 *
 *  EX02: Revise the program to omit sending the content-type
 *      response header. How does the output change when you use 
 *      Firefox, IE or Chrome as the client?
 */
public class HeaderServer
{
    public static void main(String [] args)
    {
        try
        {
            // Our socket and the input and ouput streams
            Socket client = null;
            ServerSocket server = null;
            InputStream input = null;
            PrintWriter output = null;

            try
            {
                server = new ServerSocket(12345);
                client = server.accept();

                input = client.getInputStream();
                output = new PrintWriter(client.getOutputStream(), true);

                Scanner cin = new Scanner(input);

                // Start by sending the OK status line, content type, and blank line
                output.println("HTTP/1.1 200 OK");
                output.println("Content-type: text/plain");
                output.println();

                // Now, read and echo all of the rest of the client headers
                String s;
                while (! (s = cin.nextLine()).equals(""))
                    output.println(s);
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
