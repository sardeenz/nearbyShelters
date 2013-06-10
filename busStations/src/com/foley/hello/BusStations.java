package com.foley.hello;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BusStations {
	
	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	public static void main(String[] args) throws Exception {
				
		List<Double> lats = new ArrayList<Double>();
		lats.add(35.86463176760000);
		lats.add(35.86716408390000);
		//lats.add(3.0);
		
		List<Double> longs = new ArrayList<Double>();
		longs.add(-78.61908799870000);
		longs.add(-78.62057228730001);
		//longs.add(3.1);

		for (int i=0; i < lats.size(); i++) {
			double x = lats.get(i);
			double y = longs.get(i);
			busStations(x,y);
		}
		
	}
	
	private static void busStations(double x, double y) throws Exception {
		
		URI url = new URI("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+x+","+y+"&sensor=true&key=AIzaSyA5L3jpY-Jub0WVuqujTY0Hf_i02l7vWtI&rankby=distance&types=bus_station");
        
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		JSONParser parser = new JSONParser();
		
		String responseBody = null;
		try {
			responseBody = client.execute(get, responseHandler);
			Object obj = parser.parse(responseBody);
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray results = (JSONArray) jsonObject.get("results");
			JSONObject obj2=(JSONObject)results.get(0);
			System.out.println("======field \"name\"==========");
			System.out.println(obj2.get("name"));   

		} catch (Exception ex) {
			LOGGER.info("responseBody "+ "Exception: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

}
