package reseau;


import graphique.Graphique;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author blaise
 */
public class ClientUDP {

    public String name;

    public ClientUDP(String name) {
        this.name = name;
    }

    public String send(String ip, int port) {
        byte[] tbyte = new byte[250];
        tbyte = this.name.getBytes();


        InetAddress a = null;
        try {
            DatagramSocket s = new DatagramSocket();
            
            a = InetAddress.getByName(ip);

            if (a != null) {
               
                //Envoi du packet
                DatagramPacket p = new DatagramPacket(tbyte, tbyte.length, a, port);
                s.send(p);
                
                //Réception du packet du serveur
                byte[] data = new byte[500];    
                DatagramPacket p2 = new DatagramPacket(data, data.length);

                s.receive(p2);
                
                
                s.close();
      
                return new String(p2.getData());
                
                //data = p2.getData();
                //return new String(data);
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientGraphique.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ClientGraphique.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Erreur";
    }
    
    public static void main(String[] args) {
        if(args.length < 3) System.out.println("Erreur ! Nombre d'arguments incorrect");
        else {
            ClientUDP client = new ClientUDP(args[2]);
            System.out.println(client.send(args[0], new Integer(args[1])));
        }
    }
    
}