import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args){

		int port = 9999;
		ServerSocket socketTCP;
		try {
			socketTCP = new ServerSocket(port);
			Socket socket = socketTCP.accept();

			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String string = null;

			OutputStream outputStream = socket.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

			while((string = bufferedReader.readLine()) != null){
				System.out.println(string);
				Thread.currentThread();
				Thread.sleep(1000);
				bufferedWriter.write(string + "\n");
				bufferedWriter.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
