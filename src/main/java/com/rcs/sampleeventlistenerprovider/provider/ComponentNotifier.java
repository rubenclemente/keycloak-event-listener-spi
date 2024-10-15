package com.rcs.sampleeventlistenerprovider.provider;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ComponentNotifier {

    private static final Logger LOGGER = Logger.getLogger(ComponentNotifier.class.getName());
    private static final String COMPONENT_URL = "http://wildfly:8080/person-component/resources/ping";

    public void notifyComponent() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(COMPONENT_URL);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String result = EntityUtils.toString(entity);
                        LOGGER.info("------------------------------------------------------------");
                        LOGGER.info("Result from COMPONENT: " + result);
                        LOGGER.info("------------------------------------------------------------");
                    }
                } else {
                    LOGGER.warning("Received non-OK response: " + statusCode);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to notify component", e);
        }
    }
}
