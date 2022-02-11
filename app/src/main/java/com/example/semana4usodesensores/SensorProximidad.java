package com.example.semana4usodesensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SensorProximidad extends AppCompatActivity {
    TabLayout tabLayout;

    TabItem tab1, tab2, tab3;

    TextView texto;

    SensorManager sensorManager;
    // para representar al sensor
    Sensor sensor;
    // para determinar si algo de acerca al dispositivo
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_proximidad);

        tabLayout = findViewById(R.id.menu);

        tab1 = findViewById(R.id.Home);
        tab2 = findViewById(R.id.SensorProxi);
        tab3 = findViewById(R.id.SensorAcelerometro);
        texto = findViewById(R.id.tvSensor);



//codigo de el tab
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Obtenemos la posición del tab a mostrar
                // Determinar cual fragment se presenta
                if(tab.getPosition()==0){
                    Intent siga = new Intent(SensorProximidad.this,MainActivity.class);
                    startActivity(siga);
                }
                if(tab.getPosition()==1){
                    Intent siga = new Intent(SensorProximidad.this,SensorProximidad.class);
                    startActivity(siga);
                }
                if(tab.getPosition()==2){
                    Intent siga = new Intent(SensorProximidad.this,Ascelerometro.class);
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

        // 2.- Aplicando el servicio
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // El tipo de sensor que se utiliza
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        // Verificar si el dispositivo tiene este tipo de sensor.
        // si no lo tiene hayq e terminar la acción
        if (sensor == null) finish();
        sensorEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.values[0] < sensor.getMaximumRange()) {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    texto.setText("CAMBIANDO A COLOR ROJO");
                } else {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                    texto.setText("SENSOR DE PROXIMIDAD");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }

    public void start() {
        sensorManager.registerListener(sensorEventListener, sensor, 2000 * 1000);
    }

    public void stop() {
        sensorManager.unregisterListener(sensorEventListener);
    }
    {

    }
}
