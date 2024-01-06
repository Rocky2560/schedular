package Scheduler.connection;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Connecting to Elastic search
 */
public class ElasticsearchConnection {
    public RestHighLevelClient client;
    public ElasticsearchConnection() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic",

                        "3l@st!c123"));


        this.client = new RestHighLevelClient(

                RestClient.builder(

                        new HttpHost("10.10.5.30", 21500, "http"))

                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

                            public HttpAsyncClientBuilder customizeHttpClient(

                                    final HttpAsyncClientBuilder httpAsyncClientBuilder) {

                                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

                            }

                        })

        );


    }
}
