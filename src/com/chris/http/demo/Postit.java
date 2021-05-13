package com.chris.http.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class Postit {
	
	public void mymethod() throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
	     

	    try
	    {
	    	
	        //Define a postRequest request
	        HttpPost postRequest = new HttpPost("http://ptsv2.com/t/5ldkf-1620949253/post");
	         
	        //Set the API media type in http content-type header
	        postRequest.addHeader("content-type", "application/xml");
	         
	        //Set the request post body
	        StringEntity userEntity = new StringEntity("test=myvar");
	        postRequest.setEntity(userEntity);
	        postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
	          
	        //Send the request; It will immediately return the response in HttpResponse object if any
	        HttpResponse response = httpClient.execute(postRequest);
	         
	        //verify the valid error code first
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode != 200) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
	}

}
