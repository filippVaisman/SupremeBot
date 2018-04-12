package sample.HttpRequset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpRequest implements IHttpRequest{

    @Override
    public String request(String url) throws IOException{
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer content = new StringBuffer();
        String tmp="";
        while ((tmp =br.readLine()) != null) {
            content.append(tmp);
        }
        br.close();
        return content.toString();
    }
}
