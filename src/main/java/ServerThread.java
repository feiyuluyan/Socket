import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2018/5/30.
 */
public class ServerThread  extends  Thread{

    //和本线程相关的socket
    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket=socket;
    }
    //线程操作响应客户端请求
    public void run(){
        //获取输入流
        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        //获取输出流
        OutputStream os=null;
        PrintWriter pw=null;

        //3.获取输入流客户端性信息，
        try{
             is = socket.getInputStream();
             isr = new InputStreamReader(is);
             br = new BufferedReader(isr);
             String info = null;
             while((info=br.readLine())!=null){
                 System.out.println("我是服务端，客户端说："+info);
             }
             //4.响应客户端请求
             os = socket.getOutputStream();
             pw = new PrintWriter(os);
             pw.write("服务器已启动!欢迎你的使用！");
             pw.flush();
             socket.shutdownOutput();
             //5.关闭资源
        } catch (IOException e) {
             e.printStackTrace();
        }finally {
            //5.关闭资源
            try {
               if(pw!=null){
                pw.close();
               }
               if(os!=null){
                os.close();
               }
               if(br != null){
                    br.close();
               }
               if(is!=null){
                is.close();
               }
               if(socket!=null){
                socket.close();
               }
            } catch (IOException e) {
            e.printStackTrace();
        }

        }

    }

}
