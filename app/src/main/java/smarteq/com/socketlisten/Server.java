package smarteq.com.socketlisten;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by SD
 * on 24.10.2018.
 */

class Server {
    private static final int socketServerPORT = 8888;
    private MainActivity activity;
    private ServerSocket serverSocket;
    private String message = "";

    Server(MainActivity activity) {
        this.activity = activity;
        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.start();
    }

    int getPort() {
        return socketServerPORT;
    }

    void onDestroy() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
        private class SocketServerReplyThread extends Thread {

            private Socket hostThreadSocket;
            int cnt;

            SocketServerReplyThread(Socket socket, int c) {
                hostThreadSocket = socket;
                cnt = c;
            }

            @Override
            public void run() {
                OutputStream outputStream;
                String msgReply = "Hello from Server, you are #" + cnt;

                try {
                    outputStream = hostThreadSocket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.print(msgReply);
                    printStream.close();

                    message += "replayed: " + msgReply + "\n";

                    activity.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            activity.msg.setText(message);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    message += "Something wrong! " + e.toString() + "\n";
                }

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.msg.setText(message);
                    }
                });
            }

        }
    */

    String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress
                            .nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "Server running at : "
                                + inetAddress.getHostAddress();
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }
        return ip;
    }

    private class SocketServerThread extends Thread {

        int count = 0;

        @Override
        public void run() {
            try {
                // create ServerSocket using specified port
                serverSocket = new ServerSocket(socketServerPORT);

                while (true) {
                    // block the call until connection is created and return
                    // Socket object
                    Socket socket = serverSocket.accept();

                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                    final String clientSentence = inFromClient.readLine();
                    System.out.println("Received: " + clientSentence);
                    Log.wtf("Received", clientSentence);

                    count++;
                    message += "#" + count + " from "
                            + socket.getInetAddress() + ":"
                            + socket.getPort() + "\n";

                    message = clientSentence;

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.refreshSquares(clientSentence);
                        }
                    });

                    //SocketServerReplyThread socketServerReplyThread =  new SocketServerReplyThread(socket, count);
                    //socketServerReplyThread.run();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}