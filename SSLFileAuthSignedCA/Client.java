import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client {
	public static void main(String[] args) throws IOException {

		System.out.print("Enter host:");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String host = br1.readLine();

		System.setProperty("javax.net.ssl.trustStore","AlmacenCLTrust");
		System.setProperty("javax.net.ssl.keyStore","AlmacenCL");
		System.setProperty("javax.net.ssl.keyStorePassword","oooooo");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory)
				SSLSocketFactory.getDefault();
		SSLSocket socketTCP = (SSLSocket) sslsocketfactory.createSocket(host, 9999);

		OutputStream outStream = socketTCP.getOutputStream();

		DataOutputStream output = new DataOutputStream(outStream);

		File certFile = new File("SR.csr");
		long size = certFile.length();

		// send file length
		output.writeLong(size);

		byte[] clientBuffer = new byte[(int)size];

		// read file
		FileInputStream fileReq = new FileInputStream("SR.csr");
		int messageBytes = fileReq.read(clientBuffer);

		// send file
		output.write(clientBuffer, 0, messageBytes);

		fileReq.close();

		socketTCP.close();

	}
}
