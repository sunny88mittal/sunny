/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TryClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sumittal
 */
public class Server {
    
    public static void main(String args[]){
        ServerSocket sc = null;
        
        try {
            sc = new ServerSocket(5555);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        Socket clientConn = null;
        BufferedReader br = null;
        PrintWriter pr = null;
        try {
            clientConn = sc.accept();
            System.out.println("Connected with a client now");
            br = new BufferedReader(new InputStreamReader(clientConn.getInputStream()));
            pr = new PrintWriter(clientConn.getOutputStream(), true);
            
            String input;
            while((input = br.readLine()) != null){
                System.out.println("From client got:" + input);
                pr.println(input);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
            pr.close();
            clientConn.close();
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
