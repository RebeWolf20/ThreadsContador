package com.example.threadscontador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean iniciar = false;
    private int counter = 0;
    private TextView txt_contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_contador=findViewById(R.id.txt_contador);
    }

    public void Start(View view){
        if (!iniciar){
            iniciar = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(iniciar){
                        counter++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt_contador.setText(String.valueOf(counter));
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public void Stop (View view){
        iniciar = false;
    }
}