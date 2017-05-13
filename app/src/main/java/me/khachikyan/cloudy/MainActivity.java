package me.khachikyan.cloudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    private static final String API_KEY = "YOUR API KEY HERE";
    private String url = "https://api.darksky.net/forecast/" + API_KEY + "/43.6532,-79.381667?units=ca"; // Hardcoded coordinates

    private TextView summaryTv;
    private TextView temperatureTv;
    private WeatherData weatherData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summaryTv = (TextView) findViewById(R.id.summary_tv);
        temperatureTv = (TextView) findViewById(R.id.temperature_tv);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        // Async Call - Perform GET request in the background thread
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Print the stack trace
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    final String res = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Initialize weather data
                            weatherData = getWeatherDetails(res);

                            // Update the view
                            summaryTv.setText(weatherData.getSummary());
                            temperatureTv.setText(Double.toString(weatherData.getTemperature()));
                        }
                    });
                }
            }
        });

    }

    public WeatherData getWeatherDetails(String response) {
        try {
            JSONObject rootObject = new JSONObject(response);
            JSONObject currentConditions = rootObject.getJSONObject("currently");

            double temperature = currentConditions.getDouble("temperature");
            long time = currentConditions.getLong("time");
            String summary = currentConditions.getString("summary");

            weatherData = new WeatherData("test location", temperature, time, summary);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherData;
    }
}
