import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class NasaApiReader {
    public final static String NASA_URL = "https://api.nasa.gov/planetary/apod?api_key=0XZ74NkXeffBal4xaWK8mqZD6svKDBQ2Ci3uunOd";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                                .setConnectTimeout(5000)
                                .setSocketTimeout(30000)
                                .setRedirectsEnabled(false)
                                .build()
                ).build();
        HttpGet request = new HttpGet(NASA_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpClient.execute(request);
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

        NasaAnswer answer = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
        System.out.println(answer.toString());

        String fileName = Paths.get(answer.getUrl().getPath()).getFileName().toString();
        request.setURI(answer.getUrl());
        request.setHeader(HttpHeaders.ACCEPT, ContentType.IMAGE_JPEG.getMimeType());

        response = httpClient.execute(request);

        FileOutputStream outstream = new FileOutputStream(
                new File("src" + File.separator +
                        "main" + File.separator +
                        "out" + File.separator + fileName));
        response.getEntity().writeTo(outstream);

        outstream.close();
        httpClient.close();
    }
}
