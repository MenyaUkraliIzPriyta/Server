package org.example.Server;



import org.example.CitiesPackage.CityManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {


    public static void main(String[] args) {

        try {
            // Создаем селектор
            Selector selector = Selector.open();
            new CityManager().loadCollectionFromFile();
            // Создаем серверный канал и регистрируем его в селекторе на прослушивание
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(12346));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);


            while (true) {
                // Ожидаем готовности каналов для чтения или записи
                selector.select();

                // Получаем ключи, готовые для обработки
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    // Обрабатываем ключ в зависимости от его типа
                    if (key.isAcceptable()) {
                        HandleAccept.handleAccept(serverChannel, selector);
                    } else if (key.isReadable()) {
                        HandleRead.handleRead(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
