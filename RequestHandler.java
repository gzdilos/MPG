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
	ServerSocket welcomeSocket;
	
	
	public RequestHandler(Socket connection, ArrayList<Socket> clients) throws Exception {
		this.connection = connection;
		//this.s = s;
		this.clients = clients;
		// create server socket
		welcomeSocket = new ServerSocket(25000);
	}

	@Override
	public void run() {
		//int serverPort = 25000; 
	 

		while (true){
			try {	
				Socket connectionSocket = connection;
				System.out.println("connection from " + connection);	    
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} // end of while (true)

	} // end of main()

}
