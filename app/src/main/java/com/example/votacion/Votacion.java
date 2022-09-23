package com.example.votacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Votacion extends AppCompatActivity {

    int preSelectedIndex = -1;
    int voto = -1 ;
    Button button;
    double cant_votos;
    double porcentaje_votos;
    NumberFormat formatoPorcentaje = NumberFormat.getPercentInstance();
    DecimalFormat formato = new DecimalFormat("#");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);
        ListView listView = (ListView) findViewById(R.id.listview);
        button=findViewById(R.id.button);

        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "Vivian Valenzuela","casilla 1", R.drawable.vivian_valenzuela));
        users.add(new UserModel(false, "Omar Aizpurua","casilla 2", R.drawable.omar_aizpurua));
        users.add(new UserModel(false, "Martín Candanedo","casilla 3", R.drawable.martin_candenedo));


        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i); //changed it to model because viewers will confused about it
                Log.e("candidato", String.valueOf(i));
                model.setSelected(true);

                users.set(i, model);


                if (preSelectedIndex > -1){

                    UserModel preRecord = users.get(preSelectedIndex);
                    preRecord.setSelected(false);

                    users.set(preSelectedIndex, preRecord);

                }

                preSelectedIndex = i;

                //now update adapter so we are going to make a update method in adapter
                //now declare adapter final to access in inner method

                adapter.updateRecords(users);

                for (int j = 0; j < users.size(); j++) {
                    if (users.get(j).isSelected){
                        Log.e("candidato", users.get(j).userName);
                        voto = j;
                        break;
                    }else{
                        voto = -1;
                    }
                }
                Log.e("VOTO:", String.valueOf(voto));

            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(voto == -1){
                    Toast.makeText(Votacion.this, "Porfavor debe seleccionar una candidato",
                            Toast.LENGTH_SHORT).show();
                }else{
                    //VALIDANDO AL USUARIO NO MAS VOTOS
                   GloabalVariable.datos[(GloabalVariable.votante)][3] = "1";
                    //CANTIDAD DE VOTOS TOTALRS
                   GloabalVariable.votos_totales = GloabalVariable.votos_totales+1;
                    //SUMANDO VOTOS DEL CANDIDATO
                   cant_votos= Double.parseDouble(GloabalVariable.Votos[voto][1]);
                   cant_votos= cant_votos +1.0;
                   GloabalVariable.Votos[voto][1] = String.valueOf(formato.format(cant_votos));
                    //PORCENTAJE DE VOTOS
                    porcentaje_votos = cant_votos/GloabalVariable.votos_totales;

                    GloabalVariable.Votos[0][2] = String.valueOf(formatoPorcentaje.format(
                            Double.parseDouble( GloabalVariable.Votos[0][1])/GloabalVariable.votos_totales
                    ));
                    GloabalVariable.Votos[1][2] = String.valueOf(formatoPorcentaje.format(
                            Double.parseDouble( GloabalVariable.Votos[1][1])/GloabalVariable.votos_totales
                    ));
                    GloabalVariable.Votos[2][2] = String.valueOf(formatoPorcentaje.format(
                            Double.parseDouble( GloabalVariable.Votos[2][1])/GloabalVariable.votos_totales
                    ));

                    Intent result = new Intent(getApplicationContext(), Resultados.class);
                    startActivity(result);

                    Log.e("candidato1",GloabalVariable.Votos[0][2]);
                    Log.e("candidato2",GloabalVariable.Votos[1][2]);
                    Log.e("candidato3",GloabalVariable.Votos[2][2]);



                }


            }});
    }
}