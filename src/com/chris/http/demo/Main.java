package com.chris.http.demo;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.http.client.ClientProtocolException;



public class Main {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		Postit postit = new Postit();
		postit.mymethod();

	}
}
