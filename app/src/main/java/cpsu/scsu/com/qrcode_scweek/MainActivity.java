package cpsu.scsu.com.qrcode_scweek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mCountRoom1;
    private TextView mCountRoom2;
    private TextView mCountRoom3;
    private TextView mCountRoom;

    private Button btnAllRoom;
    private Button btnRoom1;
    private Button btnRoom2;
    private Button btnRoom3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountRoom = findViewById(R.id.count_room);
        mCountRoom1 = findViewById(R.id.count_room1);
        mCountRoom2 = findViewById(R.id.count_room2);
        mCountRoom3 = findViewById(R.id.count_room3);

        //callRequest();
        OkHttpClient client = new OkHttpClient();
        String url = "http://scweek61.herokuapp.com/total";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String api = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int tmp_room = api.indexOf("\"total_room1\"");
                            String room = api.substring(14, tmp_room-1);
                            mCountRoom.setText(room);

                            int total_length = room.length()+18;
                            int tmp_room1 = api.indexOf("\"total_room2\"");
                            String room1 = api.substring(total_length+11, tmp_room1-1);
                            mCountRoom1.setText(room1);

                            int total_length2 = total_length+room1.length()+18;
                            int tmp_room2 = api.indexOf("\"total_room3\"");
                            String room2 = api.substring(total_length2+8, tmp_room2-1);
                            mCountRoom2.setText(room2);

                            int total_length3 = total_length2+room2.length()+18;
                            int tmp_room3 = api.length();
                            String room3 = api.substring(total_length3+5, tmp_room3-1);
                            mCountRoom3.setText(room3);

                        }
                    });
                }
            }
        });


        btnAllRoom = findViewById(R.id.btn_all_room);
        btnAllRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnRoom1 = findViewById(R.id.btn_room1);
        btnRoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Room1Activity.class);
                startActivity(intent);
            }
        });

        btnRoom2 = findViewById(R.id.btn_room2);
        btnRoom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Room2Activity.class);
                startActivity(intent);
            }
        });

        btnRoom3 = findViewById(R.id.btn_room3);
        btnRoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Room3Activity.class);
                startActivity(intent);
            }
        });

    }

    public void callRequest(){
        OkHttpClient client = new OkHttpClient();

        String url = "http://scweek60.herokuapp.com/api/total/0";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String api = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int tmp_room = api.indexOf("\"total_room1\"");
                            String room = api.substring(15, tmp_room-2);
                            mCountRoom.setText(room);

                            int total_length = room.length()+18;
                            int tmp_room1 = api.indexOf("\"total_room2\"");
                            String room1 = api.substring(total_length+14, tmp_room1-2);
                            mCountRoom1.setText(room1);

                            int total_length2 = total_length+room1.length()+18;
                            int tmp_room2 = api.indexOf("\"total_room3\"");
                            String room2 = api.substring(total_length2+13, tmp_room2-2);
                            mCountRoom2.setText(room2);

                            int total_length3 = total_length2+room1.length()+18;
                            int tmp_room3 = api.length();
                            String room3 = api.substring(total_length3+12, tmp_room3-2);
                            mCountRoom3.setText(room3);

                        }
                    });
                }
            }
        });
    }

}
