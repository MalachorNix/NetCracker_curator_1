package o26.client;

import o26.controller.Journal;
import o26.view.*;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 6666;
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            OutputStream sout = socket.getOutputStream();

            sout.flush();
            InputStream sin = socket.getInputStream();

            ObjectOutputStream out = new ObjectOutputStream(sout);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(sin);

            AbstractMenuItem menuItem = new UserItem();

            while (true) {
                menuItem.show(new Journal(), in, out);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}