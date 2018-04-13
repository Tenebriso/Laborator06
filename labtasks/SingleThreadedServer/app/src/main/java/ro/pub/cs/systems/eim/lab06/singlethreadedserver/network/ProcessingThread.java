package ro.pub.cs.systems.eim.lab06.singlethreadedserver.network;

import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.net.Socket;

import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Constants;
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Utilities;

/**
 * Created by student on 13.04.2018.
 */

public class ProcessingThread extends Thread {
    Socket socket;
    EditText msg;
    public ProcessingThread(Socket socket, EditText msg) {
        this.socket = socket;
        this.msg = msg;
    }

    @Override
    public void run() {
        Log.v(Constants.TAG, "Connection opened with " + socket.getInetAddress() + ":" + socket.getLocalPort());

        // TODO exercise 5c
        // simulate the fact the communication routine between the server and the client takes 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            Log.e(Constants.TAG, interruptedException.getMessage());
            if (Constants.DEBUG) {
                interruptedException.printStackTrace();
            }
        }
        try {
            PrintWriter printWriter = Utilities.getWriter(this.socket);
            printWriter.println(this.msg.getText().toString());
            this.socket.close();
            Log.v(Constants.TAG, "Connection closed");
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        }
    }
}
