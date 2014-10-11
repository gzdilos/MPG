
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
		    Socket connectionSocket = welcomeSocket.accept();
		    System.out.println("connection from " + connectionSocket);	    
		    //add this connection to our list of clients if we haven't already
		    if (!clients.contains(connectionSocket))
		    {
		    	clients.add(connectionSocket);
		    	//if this is the second client we have added, we notify all connected clients that a match is ready to begin
		    	if (clients.size() == 2)
		    	{
		    		for (int i = 0; i < 2; i++)
		    		{
		    			DataOutputStream outToClient = new DataOutputStream(clients.get(i).getOutputStream());
						outToClient.writeBytes("ready");
		    		}
		    	}
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