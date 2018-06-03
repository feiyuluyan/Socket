import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/5/29.
 */
public class Demo{

    public static String createHttpClient(String url, String param) {
        HttpClient client = new HttpClient();
        String response = null;
        String keyword = null;
        PostMethod postMethod = new PostMethod(url);

          try {
            if (param != null)
                  keyword = new String(param.getBytes("gb2312"), "ISO-8859-1");
               } catch (UnsupportedEncodingException e1) {
  // TODO Auto-generated catch block
                  e1.printStackTrace();
         }
         NameValuePair[] data = { new NameValuePair("keyword", keyword) };
        // 将表单的值放入postMethod中
         postMethod.setRequestBody(data);
        // 以上部分是带参数抓取,我自己把它注销了．大家可以把注销消掉研究下
        try {
            int statusCode = client.executeMethod(postMethod);
            response = new String(postMethod.getResponseBodyAsString()
                    .getBytes("ISO-8859-1"), "gb2312");
            //这里要注意下 gb2312要和你抓取网页的编码要一样
            String p = response.replaceAll("//&[a-zA-Z]{1,10};", "")
                    .replaceAll("<[^>]*>", "");//去掉网页中带有html语言的标签
            System.out.println(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getPageContent(String strUrl, String strPostRequest,
                                 int maxLength) {
        // 读取结果网页
        StringBuffer buffer = new StringBuffer();
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");
        try {
            URL newUrl = new URL(strUrl);
            URLConnection hConnect =newUrl.openConnection();


            // POST方式的额外数据
            if (strPostRequest.length() > 0) {
                hConnect.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(hConnect
                        .getOutputStream());
                out.write(strPostRequest);
                out.flush();
                out.close();
            }
            // 读取内容
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    hConnect.getInputStream()));
            int ch;
            for (int length = 0; (ch = rd.read()) > -1
                    && (maxLength <= 0 || length < maxLength); length++)
                buffer.append((char) ch);
            String s = buffer.toString();
            s.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
            System.out.println(s);
            rd.close();
            return buffer.toString().trim();
        } catch (Exception e) {
             return "错误:读取网页失败！";
            //

        }
    }


    public static void main(String[] args) {
        String url = "https://www.baidu.com/";
        Demo p = new Demo();
        // 第一种方法
        String response2= p.getPageContent(url, "post", 100500);
        // p.getPageContent(url, "post", 100500);//第二种方法
        System.out.println(response2);

    }
}
