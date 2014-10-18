
public class NetworkTest {

	public static void main(String[] args) throws Exception {
		Client client = new Client("localhost", 25000);
		client.connect();
	}

}
