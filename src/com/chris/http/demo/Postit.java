package com.chris.http.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

public class Postit {
	
	public void mymethod() throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
	     

	        //Creating an HttpHost object for proxy
	        HttpHost proxyhost = new HttpHost("127.0.0.1",8008);

	        //Creating an HttpHost object for target
	        HttpHost targethost = new HttpHost("google.com");
	   
	        //creating a RoutePlanner object
	        HttpRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyhost);

	        //Setting the route planner to the HttpClientBuilder object
	        HttpClientBuilder clientBuilder = HttpClients.custom();
	        clientBuilder = clientBuilder.setRoutePlanner(routePlanner);

	        //Building a CloseableHttpClient
	        CloseableHttpClient httpclient = clientBuilder.build();
	        
	        HttpPost postRequest = new HttpPost("http://ptsv2.com/t/5ldkf-1620949253/post");
	        
	        
	        //Set the request post body
	        StringEntity userEntity = new StringEntity("test=myvar");
	        postRequest.setEntity(userEntity);
	        postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
	          
	        //Send the request; It will immediately return the response in HttpResponse object if any
	        HttpResponse httpresponse = httpClient.execute(postRequest);

	        //Printing the status line
	        System.out.println(httpresponse.getStatusLine());

	        //Printing all the headers of the response
	        Header[] headers = httpresponse.getAllHeaders();
	   
	        for (int i = 0; i < headers.length; i++) {
	           System.out.println(headers[i]);
	        }
	        
	        //Printing the body of the response
	        HttpEntity entity = httpresponse.getEntity();

	        if (entity != null) {
	           System.out.println(EntityUtils.toString(entity));
	        }
	}	   

}
