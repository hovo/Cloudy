package me.khachikyan.cloudy;

/**
 * Created by hovo on 2017-05-13.
 */

public class WeatherData {
    private double temperature;
    private String location;
    private long time;
    private String summary;

    public WeatherData(String location, double temperature, long time, String summary) {
        this.location = location;
        this.temperature = temperature;
        this.time = time;
        this.summary = summary;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
