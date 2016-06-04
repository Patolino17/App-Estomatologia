package com.example.asus.alteracionesdentales;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class macrodoncia extends AppCompatActivity {

   /* @Override
    protected void onDestroy() {
        Toast.makeText(this, "destruir", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }*/
    //private Button botonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onDestroy();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macrodoncia);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(macrodoncia.this, microdoncia.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
        findViewById(R.id.button14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(macrodoncia.this, menu.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                System.exit(0);
            }
        });
        /*botonMenu = (Button) findViewById(R.id.button14);
        botonMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });*/
    }
}
