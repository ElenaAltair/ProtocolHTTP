import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*
По адресу https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats
находится список фактов о кошках. Наша задача - сделать запрос к этому ресурсу и отфильтровать факты,
за которые никто не проголосовал (поле upvotes). Формат каждой записи следующий
 */
public class Main {

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        factsCatsHttpClient("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
    }

    public static void factsCatsHttpClient(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("Facts about cats")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        // создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(url);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        // отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);
        // вывод полученных заголовков
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
        // добавим код для преобразования json в java
        List<FactsCats> factsCats = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<FactsCats>>() {
        });

        //Выведем список постов на экран
        //отфильтровать факты, за которые никто не проголосовал (поле upvotes)
        factsCats.stream()
                .filter(value -> value.getUpvotes() != null && Integer.parseInt(value.getUpvotes()) > 0)
                .forEach(System.out::println);
    }


}
