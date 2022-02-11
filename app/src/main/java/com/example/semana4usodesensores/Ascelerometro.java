 package com.example.semana4usodesensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

 public class Ascelerometro extends AppCompatActivity {
     TabLayout tabLayout;

     TabItem tab1,tab2,tab3;

    SensorManager sensorManager;
    // para representar al sensor
    Sensor sensor;
    // para determinar si algo de acerca al dispositivo
    SensorEventListener sensorEventListener;

     int whip = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascelerometro);
        tabLayout = findViewById(R.id.menu);

        tab1 = findViewById(R.id.Home);
        tab2 = findViewById(R.id.SensorProxi);
        tab3 = findViewById(R.id.SensorAcelerometro);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        final TextView sonido = (TextView)findViewById(R.id.tvSensor);
        if(sensor == null) finish();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Obtenemos la posición del tab a mostrar
                // Determinar cual fragment se presenta
                if(tab.getPosition()==0){
                    Intent siga = new Intent(Ascelerometro.this,MainActivity.class);
                    startActivity(siga);
                }
                if(tab.getPosition()==1){
                    Intent siga = new Intent(Ascelerometro.this,SensorProximidad.class);
                    startActivity(siga);
                }
                if(tab.getPosition()==2){
                    Intent siga = new Intent(Ascelerometro.this,Ascelerometro.class);
                    startActivity(siga);
                }


            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor == null) finish();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                if(x < -5 && whip == 0){
                    whip++;
                    sonido.setText("Sonido "+whip);

                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }else if(x > 5 && whip == 1) {
                    whip++;
                    sonido.setText("Sonido "+whip);

                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
                if(whip==2){
                    whip=0;
                    sound();
                    sonido.setText("Sonido "+whip);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };  // se agrega el punto y coma
        star();
    }

     private void sound(){
         MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.latigo);
         mediaPlayer.start();
     }

     private void star(){

         sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
     }

     private void stop(){
         sensorManager.unregisterListener(sensorEventListener);
     }
    /* agregar el sonido de látigo: hay que crear una carpeta dentro de la
carpeta "res"
    *  a la que llamremos "musica" alli almacenaremos el sonido.
    * ¿Cómo se hace? Clic derecho en "app" New+Android resource directory*/

     @Override
     protected void onPause() {
         stop();
         super.onPause();
     }

     @Override
     protected void onResume() {
         star();
         super.onResume();
     }
    }

