import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class RequestHandler implements Runnable {
	
	//Used to signify the end of a message
	private final String doneSend = "wedone";
	private ArrayList<Socket> clients;
	private Socket connection;
	private ArrayList<String> otherClientMessages;
	public RequestHandler(Socket connection, ArrayList<Socket> clients, ArrayList<String> opponentMessages) throws Exception {
		this.connection = connection;
		this.clients = clients;
		this.otherClientMessages = opponentMessages;
	}

	public void run() {
		Socket connectionSocket = connection;
		//int serverPort = 25000; 
		while (true){
			try {	
				// create read stream to get input
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				String clientSentence;
				clientSentence = inFromClient.readLine();		    
				// process input
				if (clientSentence.startsWith("connect"))
				{
					//do nothing, we just added the new client to our list of clients
				}else //if (clientSentence.startsWith("initial"))
				{
					otherClientMessages.add(clientSentence);
					/*
					System.out.println("received initial combination from: " + connectionSocket);
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
					System.out.println("relayed initial message to " + sendTo);
					*/
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
}
