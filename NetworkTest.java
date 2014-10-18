
public class NetworkTest {

	public static void main(String[] args) throws Exception {
		Client client = new Client("localhost", 25000);
		int turn = Client.connect();
		if (turn == 0)
		{
			System.out.println("connected... we will go first");
		}else
		{
			System.out.println("connected... we will go second");
		}
	}

}
