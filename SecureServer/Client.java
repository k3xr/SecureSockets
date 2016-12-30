import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {

		int port = 9999;

		System.out.print("Enter host:");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String host = br1.readLine();
		System.out.print("Enter something:");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		String msg = br2.readLine();
		int msgLength = msg.length();		

		Socket socketTCP = new Socket(host, port);

		OutputStream outStream = socketTCP.getOutputStream();
		InputStream inStream = socketTCP.getInputStream();

		DataOutputStream output = new DataOutputStream(outStream);
		DataInputStream input = new DataInputStream(inStream);
		
		File certFile = new File("01.crt");
		long size = certFile.getTotalSpace();
		
		output.writeInt(msgLength);

		byte[] messageBytes = new byte[msgLength];
		messageBytes = msg.getBytes();

		output.write(messageBytes, 0, msgLength);

		socketTCP.close();

	}
}
