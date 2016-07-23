/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TryClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sumittal
 */
public class Client {
    
    public static void main(String args[]){
        
        Socket clientSocket = null;
        BufferedReader  br = null;
        PrintWriter pr = null;
        BufferedReader clientInputReader = null;
        try {
            clientSocket = new Socket("localhost", 5555);
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            pr = new PrintWriter(clientSocket.getOutputStream(), true);
            
            clientInputReader = new BufferedReader(new InputStreamReader(System.in));
            String clientInput;
            while((clientInput = clientInputReader.readLine()) != null){
                System.out.println("To server:" + clientInput);
                pr.println(clientInput);
                System.out.println("From server:" + br.readLine());
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
