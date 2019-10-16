package echoApplication;
import java.net.Socket;
import java.io.*;

public class EchoClient {

	public static void main(String[] args) {
		try {
			System.out.println("Client started");
			Socket soc = new Socket("localhost",333);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a string: ");
			String str = userInput.readLine();
			PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
			out.println(str);
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println(in.readLine());
			soc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
