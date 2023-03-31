package pt.ua.tqs.airwatch.util;

import java.io.IOException;

public interface IHttpClient {

    public String httpGet(String url) throws IOException;
    
}
