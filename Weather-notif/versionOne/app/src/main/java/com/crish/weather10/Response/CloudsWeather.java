package com.crish.weather10.Response;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CloudsWeather implements Serializable {

	@SerializedName("all")
	private int all;

	public int getAll(){
		return all;
	}

	@Override
 	public String toString(){
		return 
			"CloudsWeather{" + 
			"all = '" + all + '\'' + 
			"}";
		}
}