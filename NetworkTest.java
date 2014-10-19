import java.util.ArrayList;


public class NetworkTest {

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> combination = new ArrayList<Integer>();
		combination.add(1);
		combination.add(2);
		combination.add(1);
		combination.add(2);
		ArrayList<Integer> ourAnswer;
		Client client = new Client("localhost", 25000);
		int turn = Client.connect();
		if (turn == 0)
		{
			System.out.println("connected... we will go first");
			Client.sendInitial(combination);
			ourAnswer = Client.receiveInitial();
		}else
		{
			System.out.println("connected... we will go second");
			ourAnswer = Client.receiveInitial();
			Client.sendInitial(combination);
		}
	}

}
