package com.crish.weather10.Response;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CoordWeather implements Serializable {

	@SerializedName("lon")
	private Object lon;

	@SerializedName("lat")
	private Object lat;

	public Object getLon(){
		return lon;
	}

	public Object getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"CoordWeather{" + 
			"lon = '" + lon + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}