package pt.ua.tqs.homework.connection;

import java.io.IOException;

public interface IHttpClient {

    public String httpGet(String url) throws IOException;
    
}
