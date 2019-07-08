package ventasappleServerTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class servidor {

    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        int i = 10;
        ServerSocket servidor;
        Socket conexion;

        DataInputStream entradaDatos;
        DataOutputStream salidaDatos;

        int dato;
        try {
            servidor = new ServerSocket(PUERTO);
            while (true) {
                conexion = servidor.accept();

                entradaDatos = new DataInputStream(conexion.getInputStream()) {
                };
                dato = Integer.parseInt(entradaDatos.readUTF());
                System.out.println("Cliente >>" + conexion.getInetAddress().getHostName() + " ," + dato);
                salidaDatos = new DataOutputStream(conexion.getOutputStream());

                if (dato > 2) {
                    salidaDatos.writeUTF("\nEl tope maximo es de 2 iphone 6 por pedido. ");
                    conexion.close();
                } else if (i == 0) {
                    salidaDatos.writeUTF("\nNo hay existencias del producto. ");
                    conexion.close();
                } else {
                    if (i > 1) {
                        i = i - dato;
                        salidaDatos.writeUTF("\nVenta completada correctamente, quedan: " + i + " Iphones 6.");
                        conexion.close();
                    } else {
                        i = i - 1;
                        salidaDatos.writeUTF("\nVenta completada correctamente, quedan: " + i + " Iphones 6.");
                    }
                }
                conexion.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
