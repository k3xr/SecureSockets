import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
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

				long numRcv = input.readLong();

				// create server buffer
				byte[] serverBuffer = new byte[(int)numRcv];
				
				int numBytesRead = 0;
				long acumBytesRead = 0;
				FileOutputStream fileReq = new FileOutputStream("received_cert.crt");
				
				do{
					numBytesRead = input.read(serverBuffer);
					acumBytesRead += numBytesRead;
					fileReq.write(serverBuffer, 0, numBytesRead);
					
					System.out.println("Receiving File numBytesRead " + numBytesRead);
					System.out.println("Receiving File acumBytesRead " + acumBytesRead);
					System.out.println("Receiving File...");
				} while (acumBytesRead < numRcv);
				
				System.out.println("File received");
				
				fileReq.close();
				
			}		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
