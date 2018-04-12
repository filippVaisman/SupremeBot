package sample.HttpRequset;

import java.io.IOException;


public interface IHttpRequest {
    public String request(String url) throws IOException;
}
