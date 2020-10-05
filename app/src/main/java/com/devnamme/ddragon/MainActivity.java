package com.devnamme.ddragon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    boolean hasLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DataDragon.dir = this.getExternalFilesDir(null).getPath();

        new Util.Download(this, DataDragon.url + "/api/versions.json", "api", "versions.json", new Runnable() {
            @Override
            public void run() {
                try {
                    TextView v = findViewById(R.id.ddragon_version);

                    JSONArray vList = Util.readJSONArrayFile(new File(DataDragon.dir + "/api/versions.json"));

                    DataDragon.version = vList.getString(0);

                    v.setText("DataDragon v" + DataDragon.version);

                    hasLoaded = true;
                } catch(Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();
    }

    public void switchLoadingScreenUser(View v) {
        if(!hasLoaded) {
            Toast.makeText(this, "DataDragon is still loading...", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, LoadingScreenUser.class);

        startActivity(intent);
    }
}
