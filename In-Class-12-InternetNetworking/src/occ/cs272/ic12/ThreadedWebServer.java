package occ.cs272.ic12;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *  CS 272 Example: ThreadedWebServer.java
 *
 *  VerySimpleWebServer modified to use threads.
 *
 *
 *  EX01: Test the threaded web server of by connecting
 *       to it from two simple web clients at close to the same time.
 *       Find long text files to request so both clients will be connected
 *       to the server at the same time while downloading the requested
 *       files. Describe what you observe.
 *
 *  Put the program into a GUI with a button to start and stop the server.
 *
 */
public class ThreadedWebServer
{
    private static final int PORT = 12345;
    private static String docBase;

    public static void main(String [] args)
    {
        try
        {
            docBase = new File("U:/").getCanonicalPath();
            ServerSocket server = new ServerSocket(PORT);
            ThreadedWebServer web = new ThreadedWebServer();

            while (true)
            {
                // Wait for a connection
                System.out.println("Listening for a connection on " + PORT);
                Socket client = server.accept();

                // Spawn a thread to execute it
                web.new ClientThread(client);

                // Make an entry into the access logs
                System.out.println("Client connected from "
                    + client.getInetAddress());
            }
        }
        catch (Exception e) {
            System.err.println("Server has terminated");
            e.printStackTrace(System.err);
        }
    }

    // This is the thread that handles client requests
    class ClientThread extends Thread
    {
        private Socket client = null;
        private InputStream input = null;
        private BufferedReader file = null;
        private PrintWriter toClient = null;
        private Scanner fromClient = null;

        public ClientThread(Socket c)
        {
            try
            {
                client = c;
                input = client.getInputStream();
                toClient = new PrintWriter(client.getOutputStream(), true);
                fromClient = new Scanner(input);

                // Call the run() method
                start();
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
            }
        }

        public void run()
        {
            try
            {
                try
                {

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
                            toClient.println("Content-type: text/html");
                            toClient.println();
                            toClient.println("<h1>Forbidden</h1>");
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
                            while ((s=file.readLine()) != null)
                                toClient.println(s);
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
}
