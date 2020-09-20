package com.wangjg.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangjg
 * 2020/8/12
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Reactor {
    public static void main(String[] args) throws IOException {
        SelectorThreadGroup bossGroup = new SelectorThreadGroup(1);
        SelectorThreadGroup workerGroup = new SelectorThreadGroup(3);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .bind(8888);

        System.in.read();
    }
}

class Bootstrap {
    private SelectorThreadGroup group;
    private SelectorThreadGroup childGroup;

    public Bootstrap group(SelectorThreadGroup bossGroup
            , SelectorThreadGroup workerGroup) {
        this.group = bossGroup;
        this.childGroup = workerGroup;
        return this;
    }

    public void bind(int port) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            InitiationDispatcher next = group.next();
            next.execute(() -> {
                AcceptorHandler acceptorHandler = new AcceptorHandler(serverSocketChannel, childGroup);
                Selector selector = next.getSelector();
                try {
                    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, acceptorHandler);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SelectorThreadGroup {

    private InitiationDispatcher[] selectorThreads;

    private AtomicInteger pos = new AtomicInteger(0);

    public SelectorThreadGroup(int nThread) {
        selectorThreads = new InitiationDispatcher[nThread];
        for (int i = 0; i < nThread; i++) {
            selectorThreads[i] = new InitiationDispatcher();
        }
    }

    public InitiationDispatcher next() {
        return selectorThreads[pos.getAndIncrement() % selectorThreads.length];
    }
}


interface EventHandler {
    void doHandle();
}

class AcceptorHandler implements EventHandler {

    /**
     * handle
     */
    private ServerSocketChannel serverSocketChannel;
    private SelectorThreadGroup childGroup;

    public AcceptorHandler(ServerSocketChannel serverSocketChannel, SelectorThreadGroup childGroup) {
        this.serverSocketChannel = serverSocketChannel;
        this.childGroup = childGroup;
    }

    @Override
    public void doHandle() {
        try {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            ReadHanlder readHanlder = new ReadHanlder(client);
            InitiationDispatcher next = childGroup.next();
            next.execute(() -> {
                Selector selector = next.getSelector();
                try {
                    client.register(selector, SelectionKey.OP_READ, readHanlder);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadHanlder implements EventHandler {
    /**
     * handle
     */
    private SocketChannel socketChannel;

    public ReadHanlder(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void doHandle() {
        ByteBuffer data = ByteBuffer.allocateDirect(4096);
        try {
            socketChannel.read(data);
            data.flip();
            byte[] dd = new byte[data.limit()];
            data.get(dd);
            System.out.println(new String(dd));
            data.clear();
            for (int i = 0; i < 10; i++) {
                data.put("a".getBytes());
                data.flip();
                socketChannel.write(data);
                data.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class InitiationDispatcher implements Executor {

    // SynchronousEventDemultiplexer
    private Selector selector;

    private BlockingQueue<Runnable> bq = new LinkedBlockingDeque<>();

    private Thread thread = null;

    public InitiationDispatcher() {
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        for (; ; ) {
            try {
                int nums = selector.select();
                if (nums > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        EventHandler handler = (EventHandler) key.attachment();
                        handler.doHandle();
                    }
                }
                runTasks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runTasks() {
        while (!bq.isEmpty()) {
            try {
                Runnable task = bq.take();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void execute(Runnable command) {
        if (command != null) {
            try {
                bq.put(command);
                this.selector.wakeup();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!inEventLoop()) {
            thread = new Thread(() -> {
                thread = Thread.currentThread();
                InitiationDispatcher.this.run();
            });
            thread.start();
        }
    }

    private boolean inEventLoop() {
        return thread == Thread.currentThread();
    }

    public Selector getSelector() {
        return selector;
    }
}
