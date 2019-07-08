package ventasappleClienteTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cliente {

    private final static int PUERTO = 12345;
    private final static String IP = "localhost";

    public static void main(String[] args) {

        Socket conexion;
        DataInputStream datosEntrada;
        DataOutputStream datosSalida;
        Scanner teclado = new Scanner(System.in);

        

        while (true) {
            try {
                conexion = new Socket(IP, PUERTO);
                datosSalida = new DataOutputStream(conexion.getOutputStream());
                
                
                datosSalida.writeUTF(teclado.nextLine());

                datosEntrada = new DataInputStream(conexion.getInputStream());
                System.out.println("Server>>" + datosEntrada.readUTF());
                conexion.close();
            } catch (IOException ex) {
                Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

