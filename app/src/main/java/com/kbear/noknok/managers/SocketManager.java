package com.kbear.noknok.managers;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.kbear.noknok.ServerConstants;

import java.net.URISyntaxException;

/**
 * Created by allen on 3/2/15.
 */
public class SocketManager {

    private static final SocketManager INSTANCE = new SocketManager();

    private static Socket sSocket;

    private SocketManager() {
        try {
            sSocket = IO.socket(ServerConstants.BASE_SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (sSocket != null) {
            sSocket.connect();
        }
    }

    public static void init() {
    }

    public static Socket getSocket() {
        return sSocket;
    }
}
