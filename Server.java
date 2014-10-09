
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	private static ArrayList<Socket> clients;
	public static void main(String[] args)throws Exception {


		// see if we do not use default server port
		int serverPort = 25001; 
		/* change above port number this if required */
		
		if (args.length >= 1)
		    serverPort = Integer.parseInt(args[0]);
	    
		// create server socket
		ServerSocket welcomeSocket = new ServerSocket(serverPort);

		while (true){

		    // accept connection from connection queue
		    Socket connectionSocket = welcomeSocket.accept();
		    System.out.println("connection from " + connectionSocket);
		    if (!clients.contains(connectionSocket))
		    {
		    	clients.add(connectionSocket);
		    }
		    
		    // create read stream to get input
		    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		    String clientSentence;
		    clientSentence = inFromClient.readLine();		    
		  
		    // process input
		    if (clientSentence.startsWith("connect"))
		    {
		    	
		    }else if (clientSentence.startsWith("initial"))
		    {
		    	
		    }

		} // end of while (true)

	} // end of main()

} // end of class TCPServer
