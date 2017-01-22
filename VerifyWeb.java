import java.io.*;
import java.security.cert.*;

public class PrintCert {
	public static void main(String args[]) {
		try {
			InputStreamReader Flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(Flujo);
			System.out.print("Introducir Nombre Certificado: ");
			String nameFile = teclado.readLine();
			FileInputStream fr = new FileInputStream(nameFile);

			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			
			InputStreamReader Flujo2 = new InputStreamReader(System.in);
			BufferedReader teclado2 = new BufferedReader(Flujo2);
			System.out.print("Introducir sitio web: ");
			String website = teclado2.readLine();
			
			if(c.getSubjectDN().getName().contains(website)){
				System.out.println("Certificado recibido protege el sitio web");
			} else {
				System.out.println("Certificado recibido no protege el sitio web");
			}

			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}