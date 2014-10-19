
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	private static ArrayList<Socket> clients;
	private static ArrayList<String> sendToP1;
	private static ArrayList<String> sendToP2;
	//private static int count = 1;
	public static void main(String[] args)throws Exception {
		clients = new ArrayList<Socket>();
		sendToP1 = new ArrayList<String>();
		sendToP2 = new ArrayList<String>();
		// create server socket
		ServerSocket welcomeSocket = new ServerSocket(25000);
		System.out.println("The server is listening on: " + welcomeSocket);

		while (clients.size() != 2) {
			//System.out.println("Accepting connections");
			// accept connection from connection queue
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("connection from " + connectionSocket);	    
			//add this connection to our list of clients if we haven't already
			if (!clients.contains(connectionSocket))
			{
				clients.add(connectionSocket);
				//if this is the second client we have added, we notify all connected clients that a match is ready to begin
				if (clients.size() == 2)
				{
					System.out.println("got 2 clients, sending ready messages...");
					for (int i = 0; i < 2; i++)
					{
						DataOutputStream outToClient = new DataOutputStream(clients.get(i).getOutputStream());
						//the number after "ready" tells the client to go first or second
						String readyMessage = new String("ready " + i + "\n");
						outToClient.writeBytes(readyMessage);
					}
				}
			}
			Runnable runnable;
			if (clients.size() == 1)
			{
				runnable = new RequestHandler((connectionSocket), clients, sendToP2);
			}else
			{
				runnable = new RequestHandler((connectionSocket), clients, sendToP1);
			}
			Thread thread = new Thread(runnable);
			thread.start();					    
		} 
		
		while (true)
		{
			if (sendToP1.size() != 0)
			{
				DataOutputStream outToClient = new DataOutputStream(clients.get(0).getOutputStream());
				outToClient.writeBytes(sendToP1.get(0) + '\n');
				System.out.println("sent: " + sendToP1.get(0) + " to player 1...");
				sendToP1.remove(0);
			}
			if (sendToP2.size() != 0)
			{
				DataOutputStream outToClient = new DataOutputStream(clients.get(1).getOutputStream());
				outToClient.writeBytes(sendToP2.get(0) + '\n');
				System.out.println("sent: " + sendToP2.get(0) + " to player 2...");
				sendToP2.remove(0);
			}
		}
	}
	/*
	public static void main(String[] args)throws Exception {
		clients = new ArrayList<Socket>();
		// see if we do not use default server port
		int serverPort = 25000; 
		
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
		    		System.out.println("got 2 clients, sending ready messages...");
		    		for (int i = 0; i < 2; i++)
		    		{
		    			DataOutputStream outToClient = new DataOutputStream(clients.get(i).getOutputStream());
		    			//the number after "ready" tells the client to go first or second
		    			String readyMessage = new String("ready " + i);
						outToClient.writeBytes(readyMessage);
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
	*/
} 