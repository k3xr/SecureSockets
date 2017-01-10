package simpleSSL4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

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

			//OBTENEMOS TODAS LA FAMILIAS DE CIFRADORES

			String [] cifradores= sslserversocket.getSupportedCipherSuites();
			for (int i=0; i<cifradores.length; i++){
				System.out.println("support:  " + cifradores[i]);
			}

			System.out.print("¿selecionar familia?: (s/n)" );
			Scanner sc1 = new Scanner(System.in);
			String str1 = sc1.nextLine();
			String s = "s";

			if (s.equals(str1)) {
				System.out.print("Familia Cifradores:" );
				Scanner sc = new Scanner(System.in);
				String familia_cifradores_str = sc.nextLine();
				int familia_cifradores = Integer.parseInt(familia_cifradores_str);

				String [] mycifrador = {cifradores[familia_cifradores]};
				sslserversocket.setEnabledCipherSuites(mycifrador);

				//OBTENEMOS LA OPCION SELECCIONADA
				String [] cifradores_enable = sslserversocket.getEnabledCipherSuites(); 
				for (int i = 0; i < cifradores_enable.length; i++) {
					System.out.println("enable: " + cifradores_enable[i]);
				}
				sc.close();
			}//if

			else {
				System.out.println("Familia Negociada en la Sesion \n");
			}
			sc1.close();

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
