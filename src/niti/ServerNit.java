/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import konst.Port;
import logika.Kontroler;
import view.ServerForma;

/**
 *
 * @author DusanIMilos
 */
public class ServerNit extends Thread {
    
    ServerForma forma;
    ServerSocket serverSocket;
    
    public ServerNit(ServerForma forma) {
        this.forma = forma;
    }
    
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(forma.getPort());
            Port.getInstance().setPort(forma.getPort());
            System.out.println("Server je pokrenut na portu " + forma.getPort());
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao");
                KlijentNit kn = new KlijentNit(socket);
                kn.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerForma getForma() {
        return forma;
    }

    public void setForma(ServerForma forma) {
        this.forma = forma;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
}
