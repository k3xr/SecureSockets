import java.io.*;
import java.security.cert.*;

public class PrintCert {
	public static void main(String args[]) {
		try {
			InputStreamReader Flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(Flujo);
			System.out.print("Introducir Nombre Certificado: " );
			String NameFile = teclado.readLine();
			System.out.println("Nombre fichero:"+NameFile );
			FileInputStream fr = new FileInputStream(NameFile);

			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			System.out.println("PROPIETARIO: " + c.getSubjectDN());
			System.out.println("EMISOR: " + c.getIssuerDN());
			System.out.println("VALIDEZ: " + c.getNotBefore() + " to " + c.getNotAfter());
			System.out.println("NUM. SERIE: " + c.getSerialNumber());
			System.out.println("ALGORITMOS: " + c.getSigAlgName());
			//Imprime los campos del certificado en forma de string
			fr.close();
		} catch (Exception e) {
			e.printStackTrace( );
		}
	}//main
}//class