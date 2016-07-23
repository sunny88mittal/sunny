/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketProgramming;

import java.io.*;
import java.net.*;

//To run this programm first run the application ReverserServletApp
public class Reverse {
    public static void main(String[] args) throws Exception {

        String stringToReverse = URLEncoder.encode("sunny", "UTF-8");

        //URL url = new URL("http://localhost:9080/ReverseServletApp/ReverseServlet");
        URL url = new URL("http", "inx178777", 6010, "/coreservices/DomainService");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(
                                         connection.getOutputStream());
        out.write("string=" + stringToReverse);
        out.close();

        BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();
    }
}
