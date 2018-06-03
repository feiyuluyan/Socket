import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2018/5/30.
 */
public class Demo01 {

    public static void getURL(String urlStr){
        String data = "";
        try {
            URL url= new URL(urlStr);
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is,"utf-8");

            BufferedReader br = new BufferedReader(isr);
            data =br.readLine();
            while(data!=null){
                System.out.println(data);
                data=br.readLine();

            }
            br.close();
            isr.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  void  main(String[] args){
        String url = "https://www.baidu.com";

        Demo01.getURL(url);


    }
}
