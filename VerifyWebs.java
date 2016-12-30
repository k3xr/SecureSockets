package socketsSeguros;

import java.io.*;
import java.security.cert.*;

public class PrintCert {
	public static void main(String args[]) {
		try {
			String[] certNames = new String[4];
			certNames[0] = "01.crt";
			certNames[1] = "02.crt";
			certNames[2] = "03.crt";
			certNames[3] = "04.crt";

			CertificateFactory cf = CertificateFactory.getInstance("X509");

			InputStreamReader flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(flujo);
			System.out.print("Introducir sitio web: ");
			String website = teclado.readLine();
			
			FileWriter fw = new FileWriter("log.txt");
			BufferedWriter fs = new BufferedWriter(fw);

			for (int i = 0; i < certNames.length; i++){
				FileInputStream fr = new FileInputStream(certNames[i]);
				X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
				
				fs.append("Leido certificado: " + certNames[i] + "\n");
				fs.append("PROPIETARIO: " + c.getSubjectDN() + "\n");
				fs.append("EMISOR: " + c.getIssuerDN() + "\n");
				fs.append("VALIDEZ: " + c.getNotBefore() + " to " + c.getNotAfter() + "\n");
				fs.append("NUM. SERIE: " + c.getSerialNumber() + "\n");
				fs.append("ALGORITMOS: " + c.getSigAlgName() + "\n");
				
				if(c.getSubjectDN().getName().contains(website)){
					System.out.println("Certificado " + certNames[i] + " protege el sitio web");
					fs.append("Certificado " + certNames[i] + " protege el sitio web \n\n");
				} else {
					System.out.println("Certificado " + certNames[i] + " no protege el sitio web");
					fs.append("Certificado " + certNames[i] + " no protege el sitio web \n\n");
				}
				fr.close();
			}
			
			fs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}