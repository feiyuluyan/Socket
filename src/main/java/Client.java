import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2018/5/30.
 */
public class Client {

    public static  void main(String[] args){
        try {
            //1.创建客户端socket
            Socket socket = new Socket("localhost",8888);
            System.out.println("启动客户端！");
            //2.获取输出流，向服务器发送信息
            OutputStream os = socket.getOutputStream();//获取输出流字节
            PrintWriter pw = new PrintWriter(os);//将输出流打包为打印流
            pw.write("用户名：hujin;密码：123456");
            pw.flush();
            //关闭输出流
            socket.shutdownOutput();
            //3.获取服务端输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info=br.readLine())!=null){
                System.out.println("我是客户端，服务端说："+info);
            }


            //4.关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
