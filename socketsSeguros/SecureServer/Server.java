import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args){

		int port = 9999;
		ServerSocket socketTCP;
		try {
			socketTCP = new ServerSocket(port);
			while(true){
				Socket socket_sr = socketTCP.accept();

				OutputStream outStream = socket_sr.getOutputStream();
				InputStream inStream = socket_sr.getInputStream();

				DataOutputStream output = new DataOutputStream(outStream);
				DataInputStream input = new DataInputStream(inStream);

				int numRcv = input.readInt();
				System.out.print(numRcv);

				byte[] msgReceived = new byte[numRcv];
				int readBytes = input.read(msgReceived);
				System.out.print(new String(msgReceived + " leidos " + readBytes + "bytes"));
			}		

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
