package com.chris.http.demo;

import java.io.File;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class ClientCustomSSL {
   
   public final static void main(String[] args) throws Exception {

	   
	  //Creating an HttpHost object for proxy
	  HttpHost proxyhost = new HttpHost("localhost",8008);
	   
      //Creating an HttpHost object for target
      HttpHost targethost = new HttpHost("ptsv2.com",443);
	  
      //creating a RoutePlanner object
      HttpRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyhost);
      
      //Creating SSLContextBuilder object
      SSLContextBuilder SSLBuilder = SSLContexts.custom();
  
      //Loading the Keystore file
      File file = new File("/home/chris/jdk-16.0.1/lib/security/cacerts");
      SSLBuilder = SSLBuilder.loadTrustMaterial(file,
         "changeit".toCharArray());

      //Building the SSLContext usiong the build() method
      SSLContext sslcontext = SSLBuilder.build();
 
      //Creating SSLConnectionSocketFactory object
      SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(sslcontext, new NoopHostnameVerifier());
 
      //Creating HttpClientBuilder
      HttpClientBuilder clientbuilder = HttpClients.custom();
      clientbuilder = clientbuilder.setRoutePlanner(routePlanner);

      //Setting the SSLConnectionSocketFactory
      clientbuilder = clientbuilder.setSSLSocketFactory(sslConSocFactory);

      //Building the CloseableHttpClient
      CloseableHttpClient httpclient = clientbuilder.build();
      
      //Create a string entity
      
      StringEntity se = new StringEntity("test=myvar");
      
      //Creating the HttpPost request
      HttpPost httpPost = new HttpPost("https://ptsv2.com/t/5ldkf-1620949253/post");
      
      //Associate the entity with the httpPost object
      
      httpPost.setEntity(se);
 
      //Executing the request
      HttpResponse httpresponse = httpclient.execute(targethost,httpPost);

      //printing the status line
      System.out.println(httpresponse.getStatusLine());

      //Retrieving the HttpEntity and displaying the no.of bytes read
      HttpEntity entity = httpresponse.getEntity();
      if (entity != null) {
         System.out.println(EntityUtils.toString(entity));
      } 
   }
}
