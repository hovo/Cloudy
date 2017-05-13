package me.khachikyan.cloudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "YOUR API KEY HERE"; // SECRET
        String url = "https://api.darksky.net/forecast/" + apiKey + "/37.8267,-122.4233";


    }
}
