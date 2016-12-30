package socketsSeguros;

import java.io.*;
import java.security.cert.*;

public class CompruebaRevocado {
	public static void main(String args[]) {
		try {
			InputStreamReader flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(flujo);
			System.out.print("Introducir Nombre Certificado: ");
			String nameFile = teclado.readLine();
			System.out.println("Nombre fichero:" + nameFile);
			FileInputStream fr = new FileInputStream(nameFile);

			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			fr.close();

			// Imprime los campos del certificado en forma de string
			System.out.println("PROPIETARIO: " + c.getSubjectDN());
			System.out.println("EMISOR: " + c.getIssuerDN());
			System.out.println("VALIDEZ: " + c.getNotBefore() + " to " + c.getNotAfter());
			System.out.println("NUM. SERIE: " + c.getSerialNumber());
			System.out.println("ALGORITMOS: " + c.getSigAlgName());

			// Comprueba revocacion
			InputStreamReader flujo3 = new InputStreamReader(System.in);
			BufferedReader teclado3 = new BufferedReader(flujo3);
			System.out.print("Introducir Nombre Fichero certificados revocados (*.pem): ");
			String nameFile3 = teclado3.readLine();	
			FileInputStream fr3 = new FileInputStream(nameFile3);

			X509CRL crl = (X509CRL)cf.generateCRL(fr3);
			if (crl.isRevoked(c)) {
				System.out.println("Certificado revocado");
			} else {
				System.out.println("Certificado no revocado");
			}

			fr3.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}