package com.crish.weather10.Response;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MainWeather implements Serializable {

	@SerializedName("temp")
	private Object temp;

	@SerializedName("feels_like")
	private Object feelsLike;

	@SerializedName("temp_min")
	private Object tempMin;

	@SerializedName("temp_max")
	private Object tempMax;

	@SerializedName("pressure")
	private int pressure;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("sea_level")
	private int seaLevel;

	@SerializedName("grnd_level")
	private int grndLevel;

	public Object getTemp(){
		return temp;
	}

	public Object getFeelsLike(){
		return feelsLike;
	}

	public Object getTempMin(){
		return tempMin;
	}

	public Object getTempMax(){
		return tempMax;
	}

	public int getPressure(){
		return pressure;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getSeaLevel(){
		return seaLevel;
	}

	public int getGrndLevel(){
		return grndLevel;
	}

	@Override
 	public String toString(){
		return 
			"MainWeather{" + 
			"temp = '" + temp + '\'' + 
			",feels_like = '" + feelsLike + '\'' + 
			",temp_min = '" + tempMin + '\'' + 
			",temp_max = '" + tempMax + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",sea_level = '" + seaLevel + '\'' + 
			",grnd_level = '" + grndLevel + '\'' + 
			"}";
		}
}