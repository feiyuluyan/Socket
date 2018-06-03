import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/5/30.
 */
public class Server {

    public static  void  main(String[] args){
        try {
            //1.创建服务器socket
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("#####服务器即将启动！等待客户端连接####");
            Socket socket = null;
            int Count = 0;
            //循环监听等待客户端
            while(true){
                //2.调用accept方法监听客户端请求
                socket=serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                //启动线程
                serverThread.start();
                Count++;
                System.out.println("客户端的数量："+Count);
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("客户端地址为："+inetAddress.getHostAddress());

            }
            //3.获取输入流客户端性信息，

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
