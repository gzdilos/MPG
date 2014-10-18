import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Client {

	private static String serverName;
	private static int serverPort;
	private static InetAddress serverIPAddress;
	private static Socket clientSocket;
	
	public Client (String name, int serverPort) throws Exception, IOException
	{
		InetAddress serverIPAddress = InetAddress.getByName(serverName);
		serverName = name;
		this.serverPort = serverPort; 
		clientSocket = new Socket(serverIPAddress, serverPort);
	}
	
	public static int connect() throws Exception {
		
		//first establish a connection and wait for another player
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes("connect");
		
		//wait for the server to tell us that another player has connected
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String sentenceFromServer;
		sentenceFromServer = inFromServer.readLine();
		//check that the message is simply a "ready" message, otherwise an error has occurred
		if (!sentenceFromServer.startsWith("ready"))
		{
			System.out.println("Expecting 'ready' message and received different information");
			return 5;
		}
		//the integer after "ready" tells us whether we go first or second
		String messageDetails[] = sentenceFromServer.split(" ");
		return Integer.parseInt(messageDetails[1]);
		
		//now we are ready to send an initial combination for the other player to guess
	}
	
	//sends initial colour combination to the server for the other client to guess and returns our initial colour combination we need to guess
	private static ArrayList<Integer> sendInitial(ArrayList<String> combination) throws Exception
	{
		ArrayList<Integer> receivedMove = new ArrayList<Integer>();
		try {
			clientSocket = new Socket(serverIPAddress, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// create message from given move
		String message = new String("initial");
		for (int i = 0; i < combination.size(); i++)
		{
			if (i > 0)
			{
				message = message + " ";
			}
			message = message + combination.get(i);
		}
		
		// send message to server
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes(message + '\n');
		
		// wait for message from server containing our combination that we need to guess
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String sentenceFromServer;
		sentenceFromServer = inFromServer.readLine();

		// print output
		System.out.println("From Server: " + sentenceFromServer);
						
		// close client socket
		//clientSocket.close();

		// extract info from received message and return in an array list (start from index 1 because first word will be "initial"
		String[] messageInfo = sentenceFromServer.split(" ");
		for (int j = 1; j < (messageInfo.length); j++)
		{
			receivedMove.add(Integer.parseInt(messageInfo[j]));
		}
		return receivedMove;
	}
	
	//sends move to server and awaits opponent's move
	private static ArrayList<Integer> makeMove(ArrayList<Integer> move) throws Exception
	{
		ArrayList<Integer> receivedMove = new ArrayList<Integer>();
		try {
			clientSocket = new Socket(serverIPAddress, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// create message from given move
		String message = new String();
		for (int i = 0; i < move.size(); i++)
		{
			if (i > 0)
			{
				message = message + " ";
			}
			message = message + move.get(i);
		}
		
		// send message to server
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes(message + '\n');
		
		// create read stream and receive from server
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String sentenceFromServer;
		sentenceFromServer = inFromServer.readLine();

		// print output
		System.out.println("From Server: " + sentenceFromServer);
				
		// close client socket
		//clientSocket.close();

		// extract info from received message and return in an array list
		String[] messageInfo = sentenceFromServer.split(" ");
		for (int j = 0; j < (messageInfo.length); j++)
		{
			receivedMove.add(Integer.parseInt(messageInfo[j]));
		}
		return receivedMove;
	}

}
