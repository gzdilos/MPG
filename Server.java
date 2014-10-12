
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
		    	//do nothing, we just added the new client to our list of clients
		    }else if (clientSentence.startsWith("initial"))
		    {
		    	//if the message starts with 'initial', then we have received the set of colours that the other client needs to guess
		    	//find the other client in our clients array
		    	Socket sendTo;
		    	if (connectionSocket.equals(clients.get(0)))
		    	{
		    		sendTo = clients.get(1);
		    	}else
		    	{
		    		sendTo = clients.get(0);
		    	}
		    	//sending answer to the other client
		    	DataOutputStream outToClient = new DataOutputStream(sendTo.getOutputStream());
				outToClient.writeBytes(clientSentence);
		    }

		} // end of while (true)

	} // end of main()

} // end of class TCPServer