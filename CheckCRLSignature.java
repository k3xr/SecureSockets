import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Set;

public class VerificarFirmaCRL {
	public static void main(String args[]) {
		try {
			// Comprueba revocacion
			InputStreamReader flujo3 = new InputStreamReader(System.in);
			BufferedReader teclado3 = new BufferedReader(flujo3);
			System.out.print("Introducir Nombre Fichero certificados revocados (*.pem): ");
			String nameFile3 = teclado3.readLine();	
			FileInputStream fr3 = new FileInputStream(nameFile3);

			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509CRL crl = (X509CRL)cf.generateCRL(fr3);
			fr3.close();

			// Comprueba que crl.pem esta firmado por CA
			InputStreamReader flujo2 = new InputStreamReader(System.in);
			BufferedReader teclado2 = new BufferedReader(flujo2);
			System.out.print("Introducir Nombre Certificado CA (*.pem): " );
			String nameFile2 = teclado2.readLine();
			FileInputStream fr2 = new FileInputStream(nameFile2);

			CertificateFactory cf2 = CertificateFactory.getInstance("X509");
			X509Certificate c2 = (X509Certificate) cf2.generateCertificate(fr2);
			fr2.close();

			PublicKey publicKeyCA = c2.getPublicKey();

			// Si no verifica da excepción
			crl.verify(publicKeyCA);
			System.out.println("VERIFICADA FIRMA CA EN " + nameFile3);

			String infoCRL = "";

			infoCRL += "\nTipo: " + crl.getType();
			infoCRL += "\nVersion: " + crl.getVersion();
			infoCRL += "\nEmisor: " + crl.getIssuerDN();
			infoCRL += "\nFecha creación: " + crl.getThisUpdate();
			infoCRL += "\nFecha proxima actualización: " + crl.getNextUpdate();

			Set<? extends X509CRLEntry> revokedCertificates = crl.getRevokedCertificates();

			for (X509CRLEntry entry: revokedCertificates) {
				System.out.println("\nCertificado con num. serie = " + entry.getSerialNumber() + " revocado en: " + entry.getRevocationDate());
			}

			System.out.println(infoCRL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
