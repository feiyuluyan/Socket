import java.io.IOException;
import java.net.*;

/**
 * Created by Administrator on 2018/5/30.
 */

/**
 * 客户端
 */
public class UDPClient {
    public static  void main(String[] args) throws IOException {
        //1.定义服务器地址端口数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8888;
        byte[] data ="用户名：hujin;密码：123456".getBytes();
        //2.创建数据包发送至服务器
        DatagramPacket packet =new DatagramPacket(data,data.length,address,port);
        //3.创建socket
        DatagramSocket socket = new DatagramSocket();
        //4.向服务器发送数据
        socket.send(packet);


        socket.close();


    }
}
