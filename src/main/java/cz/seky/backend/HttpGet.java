package cz.seky.backend;

import com.vaadin.flow.component.UI;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author seky
 * Trida pro zaslani http Getu pozdeji dodelam i post
 *
 * */
public class HttpGet {

    private CloseableHttpClient httpClient;

    //private Parser parser = new Parser();

    private static final Logger LOGGER = Logger.getLogger(HttpGet.class.getName());



    private static HttpGet instance;



    private HttpGet() {
    }


    public static HttpGet getInstance() {
        if (instance == null) {
            instance = new HttpGet();

        }
        return instance;
    }




    /**
     * Zavolam pozadovane URL s pripojenym providerem se zadanymi kredencemi
     * @return xml s informacemi o uzivateli
     * */
    public String callUrl(String httpRequest) {




        String uri = httpRequest;


        httpClient = HttpClientBuilder.create().build();
        org.apache.http.client.methods.HttpGet request = new org.apache.http.client.methods.HttpGet(uri);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            // System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            LOGGER.log(Level.INFO, "Get OK");
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                return result;
            } else {

                return "FAIL";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FALSE";
    }


}

