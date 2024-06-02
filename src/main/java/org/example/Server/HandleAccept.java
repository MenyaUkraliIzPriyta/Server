package org.example.Server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class HandleAccept {
    protected static void handleAccept(ServerSocketChannel serverChannel, Selector selector) throws IOException {
        // Принимаем новое подключение
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Client connected: " + clientChannel.getRemoteAddress());
    }
}
