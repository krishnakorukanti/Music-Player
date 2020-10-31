package com.crish.weather10.Response;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ResponseWeather implements Serializable {

	@SerializedName("coord")
	private CoordWeather coord;

	@SerializedName("weather")
	private List<WeatherWeather> weather;

	@SerializedName("base")
	private String base;

	@SerializedName("main")
	private MainWeather main;

	@SerializedName("visibility")
	private int visibility;

	@SerializedName("wind")
	private WindWeather wind;

	@SerializedName("clouds")
	private CloudsWeather clouds;

	@SerializedName("dt")
	private int dt;

	@SerializedName("sys")
	private SysWeather sys;

	@SerializedName("timezone")
	private int timezone;

	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("cod")
	private int cod;

	public CoordWeather getCoord(){
		return coord;
	}

	public List<WeatherWeather> getWeather(){
		return weather;
	}

	public String getBase(){
		return base;
	}

	public MainWeather getMain(){
		return main;
	}

	public int getVisibility(){
		return visibility;
	}

	public WindWeather getWind(){
		return wind;
	}

	public CloudsWeather getClouds(){
		return clouds;
	}

	public int getDt(){
		return dt;
	}

	public SysWeather getSys(){
		return sys;
	}

	public int getTimezone(){
		return timezone;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public int getCod(){
		return cod;
	}

	@Override
 	public String toString(){
		return 
			"ResponseWeather{" + 
			"coord = '" + coord + '\'' + 
			",weather = '" + weather + '\'' + 
			",base = '" + base + '\'' + 
			",main = '" + main + '\'' + 
			",visibility = '" + visibility + '\'' + 
			",wind = '" + wind + '\'' + 
			",clouds = '" + clouds + '\'' + 
			",dt = '" + dt + '\'' + 
			",sys = '" + sys + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",cod = '" + cod + '\'' + 
			"}";
		}
}