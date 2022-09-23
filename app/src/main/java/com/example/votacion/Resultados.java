package com.example.votacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Resultados extends AppCompatActivity {

    TextView tv_resul,tv_por_1,tv_por_2,tv_por_3;
    TextView tv_can_1,tv_can_2,tv_can_3;
    ImageView im_can1,im_can2,im_can3;
    Button btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        tv_resul=findViewById(R.id.TV_result1);
        tv_can_1=findViewById(R.id.tv_cant_);
        tv_can_2=findViewById(R.id.tv_cant_2);
        tv_can_3=findViewById(R.id.tv_cant_3);

        tv_can_1.setText(" Votos : "+GloabalVariable.Votos[0][1]);
        tv_can_2.setText(" Votos : "+GloabalVariable.Votos[1][1]);
        tv_can_3.setText(" Votos : "+GloabalVariable.Votos[2][1]);

        tv_por_1=findViewById(R.id.tv_por_);
        tv_por_2=findViewById(R.id.tv_por_2);
        tv_por_3=findViewById(R.id.tv_por_3);

        tv_por_1.setText(" "+GloabalVariable.Votos[0][2]);
        tv_por_2.setText(" "+GloabalVariable.Votos[1][2]);
        tv_por_3.setText(" "+GloabalVariable.Votos[2][2]);

        im_can1=findViewById(R.id.imageView4);
        im_can2=findViewById(R.id.imageView2);
        im_can3=findViewById(R.id.imageView3);

        btn2= findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent openMainActivity = new Intent(getApplicationContext(),MainActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);

            }});

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Graph.class);
                startActivityIfNeeded(intent, 0);


            }});



    }

    @Override
    protected void onStart() {
        super.onStart();
        tv_can_1.setText(" Votos : "+GloabalVariable.Votos[0][1]);
        tv_can_2.setText(" Votos : "+GloabalVariable.Votos[1][1]);
        tv_can_3.setText(" Votos : "+GloabalVariable.Votos[2][1]);

        tv_por_1.setText(" "+GloabalVariable.Votos[0][2]);
        tv_por_2.setText(" "+GloabalVariable.Votos[1][2]);
        tv_por_3.setText(" "+GloabalVariable.Votos[2][2]);


    }
}