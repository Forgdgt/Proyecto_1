package com.example.votacion;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


public class Graph extends AppCompatActivity {

    TextView tv_titulo;
    Button btn4;
    GraphView grafica;

    String [] xaxisdata = {"Vivian Valenzuela","Omar Aizpurua","Martín Candanedo"};
    Double [] yaxisdata = { Double.parseDouble(GloabalVariable.Votos[0][1]),
            Double.parseDouble(GloabalVariable.Votos[1][1]),
            Double.parseDouble(GloabalVariable.Votos[2][1])};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        tv_titulo= findViewById(R.id.tv_titulo);
        btn4= findViewById(R.id.btn4);
        grafica =findViewById(R.id.grafica);


        grafica.getGridLabelRenderer().setHorizontalAxisTitle("Cantidad de votos");

        grafica.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        grafica.getGridLabelRenderer().setVerticalLabelsVisible(false);
        grafica.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

        //Datos
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(1,Double.parseDouble(GloabalVariable.Votos[0][1])),
                new DataPoint(2,Double.parseDouble(GloabalVariable.Votos[1][1])) ,
                new DataPoint(3,Double.parseDouble(GloabalVariable.Votos[2][1])) ,
        });


        //Estilos

        series.setSpacing(20);
        series.setDataWidth(0.8);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.BLACK);
        series.setValuesOnTopSize(45);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/2, (int) Math.abs(data.getY()*255/3), 100);
            }
        });


        grafica.addSeries(series);

        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent openMainActivity = new Intent(getApplicationContext(),MainActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);

            }});




    }



}