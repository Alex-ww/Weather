package wu.com.weather.utils;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//通过访问服务器获得省市县的数据
public class HttpUtils {

    public static void sendHttpRequest(final String address , final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(address);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5*1000);
                    urlConnection.setReadTimeout(5*1000);
                    InputStream in =urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) !=null ){
                        result.append(line);
                    }
                    if(listener != null){
                        listener.OnFinish(result.toString());             //结果回调
                    }
                } catch (IOException e) {
                    if(listener != null){
                        listener.OnError(e);
                    }
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();    // 关闭连接
                    }
                }
            }
        }).start();
    }

    public interface HttpCallBackListener{
        void OnFinish(String result);
        void OnError(Exception e);
    }

}
