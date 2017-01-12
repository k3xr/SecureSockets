import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server {

	public static void main(String[] args){
		try {
			System.setProperty("javax.net.ssl.keyStore","AlmacenSR");
			System.setProperty("javax.net.ssl.keyStorePassword","oooooo");
			System.setProperty("javax.net.ssl.trustStore","AlmacenSRTrust");

			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket sslserversocket =(SSLServerSocket)
					sslserversocketfactory.createServerSocket(9999);
			SSLSocket socket = (SSLSocket) sslserversocket.accept();
			socket.setNeedClientAuth(true);

			InputStream inStream = socket.getInputStream();

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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
