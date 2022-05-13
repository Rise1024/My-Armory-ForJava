package com.io;

/**
 * @author zds
 * @Description
 * @createTime 2022/3/25 15:21
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步非阻塞模式：NIO  三大核心组件：buffer（缓冲区）、channel(通道)、selector(选择器)
 * 1、在NIO中所有的读写操作都是基于缓冲区完成，除了boolean类型，每一种java基本类型都有对应的缓冲区对象（bytebuffer\intbuffer）
 * 2、基于channel读写双向
 * 3、selector是多路复用器，通过不断轮训selector的Channel,筛选出发生读写的channel,进行io操作
 * 一个线程筛选出真正发生读写的连接，大大减少系统开销
 * NIO selector多路复用
 *
 * 多路复用IO模式，通过一个线程就可以管理多个socket，只有当socket真正有读写事件发生才会占用资源来进行实际的读写操作。
 * 因为，多路复用IO比较适合连接数比较多的情况
 * IO多路复用：
 * select, poll和epoll
 * select：
 * 把包含客户端连接（文件描述符）的数组发给操作系统， 让操作系统去遍历，确定哪个文件描述符可以读写， 然后告诉我们去处理
 * (select 调用需要传入 fd 数组，需要拷贝一份到内核，高并发场景下这样的拷贝消耗的资源是惊人的)
 * poll：去掉了select中监控文件描述符数量的限制
 * epoll:
 * 1. 内核中保存一份文件描述符集合，无需用户每次都重新传入，只需告诉内核修改的部分即可。无需在复制，在集合上添加修改
 * 2. 内核不再通过轮询的方式找到就绪的文件描述符，而是通过异步 IO 事件唤醒。无需轮训，io唤醒存在读写的连接
 * 3. 内核仅会将有 IO 事件的文件描述符返回给用户，用户也无需遍历整个文件描述符集合。无需二次遍历
 *
 * reactor线程模型
 * 因此发送端为了将多个发给接收端的包更有效的发给对方，使用了优化方法（Nagle 算法）
 * 将多次间隔较小且数据量小的数据，合并成一个大的数据块，然后进行封包。
 * 这样虽然提高了效率，但是接收端就难于分辨出完整的数据包了，因为面向流的通信是无消息保护边界的。
 *　　TCP 无消息保护边界，需要在接收端处理消息边界问题，也就是我们所说的粘包、拆包问题。
 *
 *
 * tcp的机制：滑动窗口适应系统，超时重传机制，累计ACK
 *
 * tcp滑动窗口机制？
 * tcp 粘包、拆包问题?
 * 常见三次握手、四次挥手？
 *
 *
 *
 * 之前的tcp是stop-wait的模式，在发送数据方在发送数据之后会启动定时器，但是如果数据或者ACK丢失，那么定时器到期之后，收不到ACK就认为发送出现状况，要进行重传。
 * 这种通信效率是非常低的
 *  引入滑动窗口
 *
 *  发送并且已经确认ack
 *  Sent and Acknowledged：这些数据表示已经发送成功并已经被确认的数据，比如图中的前31个bytes，这些数据其实的位置是在窗口之外了，因为窗口内顺序最低的被确认之后，要移除窗口，实际上是窗口进行合拢，同时打开接收新的带发送的数据。
 *  发送但是还没有确认ack
 * Send But Not Yet Acknowledged：这部分数据称为发送但没有被确认，数据被发送出去，没有收到接收端的ACK，认为并没有完成发送，这个属于窗口内的数据。
 *
 * Not Sent，Recipient Ready to Receive：这部分是尽快发送的数据，这部分数据已经被加载到缓存中，也就是窗口中了，等待发送，其实这个窗口是完全有接收方告知的，接收方告知还是能够接受这些包，所以发送方需要尽快的发送这些包。
 *
 * Not Sent，Recipient Not Ready to Receive： 这些数据属于未发送，同时接收端也不允许发送的，因为这些数据已经超出了发送端所接收的范围。
 *
 *
 * tcp里面的几个概念：
 *
 * SYN: (同步序列编号,Synchronize Sequence Numbers)
 * ACK: (确认编号,Acknowledgement Number)
 * FIN: (结束标志,FINish)
 * TCP三次握手(创建 OPEN)
 *
 * 客户端发起一个和服务创建TCP链接的请求，这里是SYN(J)
 * 服务端接受到客户端的创建请求后，返回两个信息： SYN(K) + ACK(J+1)
 * 客户端在接受到服务端的ACK信息校验成功后(J与J+1)，返回一个信息：ACK(K+1)
 * 服务端这时接受到客户端的ACK信息校验成功后(K与K+1)，不再返回信息，后面进入数据通讯阶段
 * c->s SYN (seq=a)
 * s->c SYN ACK (seq=b(seq是随机的) ack=a+1)
 * c->s ACK（seq=a/b  ack=b+1）
 *
 * 数据通讯
 *
 * 客户端/服务端 read/write数据包
 * TCP四次握手(关闭 finish)
 *
 * 客户端发起关闭请求，发送一个信息：FIN(M)
 * 服务端接受到信息后，首先返回ACK(M+1),表明自己已经收到消息。
 * 服务端在准备好关闭之前，最后发送给客户端一个 FIN(N)消息，询问客户端是否准备好关闭了
 * 客户端接受到服务端发送的消息后，返回一个确认信息: ACK(N+1)
 * 最后，服务端和客户端在双方都得到确认时，各自关闭或者回收对应的TCP链接。
 *
 *
 *
 */
public class NioReactorServer {
    LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
    /**
     * 业务线程池
     */
    ExecutorService businessThread = Executors.newCachedThreadPool();

    abstract class ReactorThread extends Thread {

        volatile boolean running = false;

        Selector selector;

        public ReactorThread() throws IOException {
            this.selector = Selector.open();
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Runnable task;
                    while ((task = taskQueue.poll()) != null) {
                        task.run();
                    }

                    int select = selector.select(1000);//最多等待1s

                    if (select == 0) {
                        continue;
                    }
                    System.out.println("-----触发了事件-----");
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keys = selectionKeys.iterator();
                    SelectableChannel chanel = null;
                    while (keys.hasNext()) {

                        SelectionKey key = keys.next();
                        keys.remove();

                        int readyOps = key.readyOps();//准备就绪的事件
                        System.out.println("readyOps: " + readyOps);
                        if (readyOps == SelectionKey.OP_ACCEPT || readyOps == SelectionKey.OP_READ) {
                            try {
                                chanel = (SelectableChannel) key.attachment();
                                /**非阻塞模式**/
                                chanel.configureBlocking(false);

                                /**调用真实的方法**/
                                handler(chanel);

                                if (!chanel.isOpen()) {
                                    // 如果关闭了,就取消这个KEY的订阅
                                    chanel.close();
                                }

                            } catch (Exception e) {
                                chanel.close();
                            }
                        }

                    }

                    selector.selectNow();

                } catch (Exception e) {
                }
            }
        }

        public void doStart() {
            if (!running) {
                running = true;
                start();
            }
        }

        /**
         * 需要实现这个方法[selector接收到事件后，真正执行的方法]
         *
         * @param channel
         * @throws Exception
         */
        abstract void handler(SelectableChannel channel) throws Exception;

        /**
         * 为什么register要以任务提交的形式，让reactor线程去处理？
         * 因为线程在执行channel注册到selector的过程中，会和调用selector.select()方法的线程争用同一把锁
         * 而select()方法实在eventLoop中通过while循环调用的，争抢的可能性很高，为了让register能更快的执行，就放到同一个线程来处理
         *
         * @param channel
         * @return
         * @throws ExecutionException
         * @throws InterruptedException
         */
        public SelectionKey register(SelectableChannel channel) throws ExecutionException, InterruptedException {
            FutureTask<SelectionKey> futureTask = new FutureTask<SelectionKey>(new Callable<SelectionKey>() {
                @Override
                public SelectionKey call() throws Exception {
                    System.out.println("-------call-------");
                    return channel.register(selector, 0, channel);
                }
            });
            taskQueue.add(futureTask);
            return futureTask.get();
        }
    }

    /**
     * 主线程只用于接受客户端连接事件处理----->[accept处理reactor线程 (accept线程)]
     **/
    private ReactorThread[] mainReactorThread = new ReactorThread[1];

    /**
     * 子线程只用于客户端的数据处理------>[io处理reactor线程  (I/O线程)]
     **/
    private ReactorThread[] subReactorThread = new ReactorThread[8];

    private ServerSocketChannel serverSocketChannel;

    /**
     * 两个线程组初始化
     *
     * @throws IOException
     */
    public void ThreadGroupInit() throws IOException {
        AtomicInteger count = new AtomicInteger(1);
        for (int i = 0; i < mainReactorThread.length; i++) {
            mainReactorThread[i] = new ReactorThread() {
                @Override
                void handler(SelectableChannel channel) throws Exception {
                    // 只做请求分发，不做具体的数据读取
                    ServerSocketChannel socketChannel = (ServerSocketChannel) channel;
                    // 返回一个包含新进来的连接SocketChannel，因为前面设置的非阻塞模式，这里会立即返回。
                    SocketChannel clientChannel = socketChannel.accept();// mainReactor 轮询accept

                    if (clientChannel == null) {
                        return;
                    }

                    // 收到连接建立的通知之后，分发给I/O线程继续去读取数据
                    int index = count.getAndIncrement() % subReactorThread.length;
                    ReactorThread workThreadGroup = subReactorThread[index];
                    workThreadGroup.doStart();

                    // 非阻塞模式
                    clientChannel.configureBlocking(false);
                    System.out.println("----------");
                    SelectionKey selectionKey = workThreadGroup.register(clientChannel);
                    System.out.println("!!!");
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    System.out.println("收到新连接 : " + clientChannel.getRemoteAddress());
                }
            };
        }

        for (int i = 0; i < subReactorThread.length; i++) {
            subReactorThread[i] = new ReactorThread() {
                @Override
                void handler(SelectableChannel channel) throws Exception {
                    SocketChannel socketChannel = (SocketChannel) channel;

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //上面设置了这里是非阻塞的
                    while (socketChannel.isOpen() && socketChannel.read(buffer) != -1) {
                        // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                        if (buffer.position() > 0) break;
                    }
                    // 如果没数据了, 则不继续后面的处理
                    if (buffer.position() == 0) return;

                    buffer.flip();
                    byte[] content = new byte[buffer.limit()];
                    buffer.get(content);
                    System.out.println("收到数据,来自：" + socketChannel.getRemoteAddress() + " 的数据：" + new String(content));

                    // TODO 业务操作 数据库 接口调用等等
                    businessThread.submit(() -> {

                    });

                    // 响应结果 200
                    String response = "HTTP/1.1 200 OK Hello World"
                            +"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World"+"Hello World";
                    ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
                    while (responseBuffer.hasRemaining()) {
                        socketChannel.write(responseBuffer);
                    }

                }
            };
        }
    }

    /**
     * 初始化channel,并且绑定一个eventLoop线程
     *
     * @throws IOException IO异常
     */
    private void initAndRegister() throws Exception {
        // 1、 创建ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 2、 将serverSocketChannel注册到selector
        int index = new Random().nextInt(mainReactorThread.length);
        ReactorThread reactorThread = mainReactorThread[index];
        reactorThread.doStart();

        SelectionKey selectionKey = reactorThread.register(serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
    }

    /**
     * bind端口
     *
     * @param port
     * @throws IOException
     */
    public void bind(int port) throws IOException {
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("启动完成，端口8081");
    }

    public static void main(String[] args) throws Exception {
        NioReactorServer server = new NioReactorServer();
        server.ThreadGroupInit();
        server.initAndRegister();
        server.bind(8081);
    }

}

