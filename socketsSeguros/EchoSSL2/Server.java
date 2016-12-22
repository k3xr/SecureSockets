import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server {

	public static void main(String[] args){

		try {
			System.setProperty("javax.net.ssl.keyStore","AlmacenSR");
			System.setProperty("javax.net.ssl.keyStorePassword","oooooo");
			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket sslserversocket =(SSLServerSocket)
					sslserversocketfactory.createServerSocket(9999);
			SSLSocket socket = (SSLSocket) sslserversocket.accept();

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
