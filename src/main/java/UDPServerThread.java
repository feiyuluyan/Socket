import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Administrator on 2018/5/31.
 */
public class UDPServerThread extends  Thread {

    DatagramSocket socket = null;
    public UDPServerThread(DatagramSocket socket){this.socket=socket;}

    public void run(){
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data,data.length);
        try {
                socket.receive(packet);
            String info = new String(data,0,packet.getLength());
            System.out.println("我是服务器，客户端说："+info);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            socket.close();
        }

    }
}
