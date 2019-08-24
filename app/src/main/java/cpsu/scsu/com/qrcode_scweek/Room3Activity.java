package cpsu.scsu.com.qrcode_scweek;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Room3Activity extends AppCompatActivity {

    private Button mBtnScan;

    private Button btnAllRoom;
    private Button btnRoom1;
    private Button btnRoom2;
    private Button btnRoom3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);

        mBtnScan = findViewById(R.id.scan_btn);
        final Activity activity = this;
        mBtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("SCAN");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

        btnAllRoom = findViewById(R.id.btn_all_room);
        btnAllRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnRoom1 = findViewById(R.id.btn_room1);
        btnRoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room3Activity.this, Room1Activity.class);
                startActivity(intent);
            }
        });

        btnRoom2 = findViewById(R.id.btn_room2);
        btnRoom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room3Activity.this, Room2Activity.class);
                startActivity(intent);
            }
        });

        btnRoom3 = findViewById(R.id.btn_room3);
        btnRoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room3Activity.this, Room3Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                OkHttpClient client = new OkHttpClient();
                String url = "http://scweek61.herokuapp.com/inroom/"+result.getContents()+"/3";

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

                    }
                });
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
