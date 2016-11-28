package socketJavaExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketUDP {
	public static void main(String[] args) throws IOException {
		
		System.out.print("Enter something:");
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		String mensaje = br.readLine();
		int puerto = 9999;
		try {
			while(true){
				DatagramSocket socketCliente = new DatagramSocket();
				InetAddress direccionHostServidor = InetAddress.getByName("10.190.134.1");
				DatagramPacket paqueteEnvio = new DatagramPacket(mensaje.getBytes(), mensaje.length(), direccionHostServidor, puerto);
				socketCliente.send(paqueteEnvio);
				
				DatagramPacket paqueteRecibido = new DatagramPacket(new byte[512], 512);
				socketCliente.receive(paqueteRecibido);
				
				System.out.write(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
				
				socketCliente.close();
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}