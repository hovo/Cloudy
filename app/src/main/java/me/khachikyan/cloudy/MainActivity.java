package me.khachikyan.cloudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    private static final String API_KEY = "YOUR API KEY HERE";
    private String url = "https://api.darksky.net/forecast/" + API_KEY + "/37.8267,-122.4233";

    TextView jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonResponse = (TextView) findViewById(R.id.json_response);

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
                            jsonResponse.setText(res);
                        }
                    });
                }
            }
        });

    }
}
