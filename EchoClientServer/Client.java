import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {

		int port = 9999;

		System.out.print("Enter host:");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String host = br1.readLine();

		Socket socketTCP = new Socket(host, port);

		InputStream inputStream = System.in;
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		OutputStream outputStream = socketTCP.getOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		InputStream inputStream2 = socketTCP.getInputStream();
		InputStreamReader inputStreamReader2 = new InputStreamReader(inputStream2);
		BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
		String string = null;

		while(!(string = bufferedReader.readLine()).equals("")){
			bufferedWriter.write(string + "\n");
			bufferedWriter.flush();
			System.out.println(bufferedReader2.readLine());
		}

		socketTCP.close();

	}
}
