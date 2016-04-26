package occ.cs272.ic12;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *  CS 272 Example: VerySimpleWebServer.java
 *
 *  Serves a text file to an HTTP client submitting a GET
 *  request.  Exercises suggest extensions to make the
 *  server more functional.
 *
 *  EX01: Modify the program to send a different content type.
 *
 *  EX02: Modify the program to send a Content-length header giving the
 *       length of the file in bytes.
 *
 *  EX03: Modify the example to send a status code of 404 and
 *       reason Not Found when the file requested is not available.
 *
 *  EX04: Modify the example to use a loop so that when the server
 *       has responded to one client, it can accept a request for another.
 */
public class VerySimpleWebServer
{
    public static void main(String [] args)
    {
        Socket client = null;
        InputStream input = null;
        BufferedReader file = null;
        PrintWriter toClient = null;
        try
        {
            try
            {
                final int PORT = 12345;
                String docBase = new File("C:/").getCanonicalPath();

                // Start listening for requests
                ServerSocket server = new ServerSocket(PORT);
                System.out.println("Waiting for a client to connect");
                client = server.accept();

                // Open streams when client connects
                System.out.println("Client connected on port " + PORT);
                input = client.getInputStream();
                toClient = new PrintWriter(client.getOutputStream(), true);
                Scanner fromClient = new Scanner(input);

                // Retrieve the request command line
                String s = fromClient.next();

                // First token should be command
                if (!(s.equals("GET")))
                {
                    toClient.println("HTTP/1.1 501 Not Implemented");
                    toClient.println();
                    toClient.println("<h1>501 Not Implemented</h1>");
                }
                else // Second token is the file to retrieve
                {
                    String filename = docBase + fromClient.next();

                    // This code prevents a request like ../Windows/passwords.txt
                    File absoluteFile = new File(filename);
                    String absoluteName = absoluteFile.getCanonicalPath();
                    if (! absoluteName.startsWith(docBase))
                    {
                        toClient.println("HTTP/1.1 403 Forbidden");
                        toClient.println();
                        toClient.println("<h1>403 Forbidden</h1>");
                    }
                    else
                    {
                        // This is for debugging; in "real life" the logging would happen here
                        System.out.println("name = " + absoluteName);

                        // Let's read all of the headers and just throw them away
                        while (!(fromClient.nextLine().equals("")))
                            continue;

                        // Now, open up the requested file
                        file = new BufferedReader(new FileReader(filename));

                        // Send the headers back as plain text
                        toClient.println("HTTP/1.1 200 OK");
                        toClient.println("Content-type: text/plain");
                        toClient.println();

                        // Read the file and send it back to the client
                        String data;
                        while ((data = file.readLine()) != null)
                            toClient.println(data);
                    }
                }
            }
            finally
            {
                file.close();
                client.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            toClient.println("HTTP/1.1 501 Server Error");
            toClient.println("Content-type: text/html");
            toClient.println();
            toClient.println("<h1>Server Error</h1>");
        }
    }
}
