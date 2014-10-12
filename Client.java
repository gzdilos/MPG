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
	public static void main(String[] args) throws Exception {
		serverName = "localhost";
		if (args.length >= 1)
		    serverName = args[0];
			serverIPAddress = InetAddress.getByName(serverName);

		// get server port
		int serverPort = 25000; 
		//change above port number if required
		if (args.length >= 2)
		    serverPort = Integer.parseInt(args[1]);
		
		//first establish a connection and wait for another player
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes("connect");
		
		//wait for the server to tell us that another player has connected
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String sentenceFromServer;
		sentenceFromServer = inFromServer.readLine();
		//check that the message is simply a "ready" message, otherwise an error has occurred
		if (!sentenceFromServer.equals("ready"))
		{
			System.out.println("Expecting 'ready' message and received different information");
			return;
		}
		
		//now we need to prompt the user to select a colour sequence for the other player to guess
		//using the sendInitial method
		
		//how to decide who goes first?
		while(true)
		{
			//prompt user to make a guess and call the makeMove function
			//update our back end accordingly based on the move we receive from the other play
		}
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
		clientSocket.close();

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
		clientSocket.close();

		// extract info from received message and return in an array list
		String[] messageInfo = sentenceFromServer.split(" ");
		for (int j = 0; j < (messageInfo.length); j++)
		{
			receivedMove.add(Integer.parseInt(messageInfo[j]));
		}
		return receivedMove;
	}

}
