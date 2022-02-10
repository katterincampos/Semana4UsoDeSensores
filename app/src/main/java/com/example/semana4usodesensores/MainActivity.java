package com.example.semana4usodesensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;

    TabItem tab1,tab2,tab3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.menu);

        tab1 = findViewById(R.id.Home);
        tab2 = findViewById(R.id.SensorProxi);
        tab3 = findViewById(R.id.SensorAcelerometro);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Obtenemos la posición del tab a mostrar
                // Determinar cual fragment se presenta
                if(tab.getPosition()==0){
                    Intent siga = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(siga);
                }
                if(tab.getPosition()==1){
                    Intent siga = new Intent(MainActivity.this,SensorProximidad.class);
                    startActivity(siga);
                }
                if(tab.getPosition()==2){
                    Intent siga = new Intent(MainActivity.this,Ascelerometro.class);
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

    }


}

