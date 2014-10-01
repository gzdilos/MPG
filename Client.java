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
		int serverPort = 6789; 
		//change above port number if required
		if (args.length >= 2)
		    serverPort = Integer.parseInt(args[1]);
	}
	
	//sends move to server and awaits opponent's move
	public static ArrayList<String> makeMove(ArrayList<String> move) throws Exception
	{
		ArrayList<String> receivedMove = new ArrayList<String>();
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
			receivedMove.add(messageInfo[j]);
		}
		return receivedMove;
	}

}
