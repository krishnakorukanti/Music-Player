package com.crish.weather10.Response;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class WindWeather implements Serializable {

	@SerializedName("speed")
	private Object speed;

	@SerializedName("deg")
	private int deg;

	public Object getSpeed(){
		return speed;
	}

	public int getDeg(){
		return deg;
	}

	@Override
 	public String toString(){
		return 
			"WindWeather{" + 
			"speed = '" + speed + '\'' + 
			",deg = '" + deg + '\'' + 
			"}";
		}
}