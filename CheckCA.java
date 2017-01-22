import java.io.*;
import java.security.PublicKey;
import java.security.cert.*;

public class CheckCA {
	public static void main(String args[]) {
		try {
			InputStreamReader Flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(Flujo);
			System.out.print("Introducir Nombre Certificado: ");
			String nameFile = teclado.readLine();
			System.out.println("Nombre fichero: " + nameFile);
			FileInputStream fr = new FileInputStream(nameFile);

			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			System.out.println("PROPIETARIO: " + c.getSubjectDN());
			System.out.println("EMISOR: " + c.getIssuerDN());
			System.out.println("VALIDEZ: " + c.getNotBefore() + " to " + c.getNotAfter());
			System.out.println("NUM. SERIE: " + c.getSerialNumber());
			System.out.println("ALGORITMOS: " + c.getSigAlgName());

			// Certificado CA
			InputStreamReader Flujo3 = new InputStreamReader(System.in);
			BufferedReader teclado3 = new BufferedReader(Flujo3);
			System.out.print("Introducir Nombre Certificado CA (*.pem): " );
			String nameFile3 = teclado3.readLine();
			System.out.println("Nombre fichero CA: " + nameFile3);
			FileInputStream fr3 = new FileInputStream(nameFile3);

			CertificateFactory cf3 = CertificateFactory.getInstance("X509");
			X509Certificate c3 = (X509Certificate) cf3.generateCertificate(fr3);

			PublicKey publicKeyCA = c3.getPublicKey();

			fr3.close();

			// Si no verifica da excepción
			c.verify(publicKeyCA);
			System.out.println("VERIFICADA FIRMA CA EN CERTIFICADO");

			fr.close();
		} catch (Exception e) {
			e.printStackTrace( );
		}
	}//main
}//class