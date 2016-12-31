package simpleSSL3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client {
	public static void main(String[] args) throws IOException, CertificateEncodingException {

		System.out.print("Enter host:");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String host = br1.readLine();

		System.setProperty("javax.net.ssl.trustStore","AlmacenSRTrust");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory)
				SSLSocketFactory.getDefault();
		SSLSocket socketTCP = (SSLSocket) sslsocketfactory.createSocket(host, 9999);

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

		// OBTENCION DE LA SESION Y  DEL CERTIFICADO RECIBIDO DEL SERVIDOR Y EN EL CLIENTE

		SSLSession sesion = socketTCP.getSession();

		System.out.println("Host: "+sesion.getPeerHost());

		X509Certificate certificate = (X509Certificate)sesion.getPeerCertificates()[0];

		System.out.println("Propietario: " + certificate.getSubjectDN());
		System.out.println("Emisor: " + certificate.getIssuerDN());
		System.out.println("Numero Serie: " + certificate.getSerialNumber());
		System.out.println("to string: " + certificate.toString());

		//obtenemos la familia de cifradores negociado
		String cifradores_supported = sesion.getCipherSuite();
		System.out.println("FAMILIA_CIFRADORES NEGOCIADO: " + cifradores_supported);

		while(!(string = bufferedReader.readLine()).equals("")){
			bufferedWriter.write(string + "\n");
			bufferedWriter.flush();
			System.out.println(bufferedReader2.readLine());
		}
		socketTCP.close();
	}
}
