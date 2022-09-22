package com.example.votacion;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    EditText ced1,ced2,ced3;
    Button btn;
    private Toast Toat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ced1=findViewById(R.id.ced1);
        ced2=findViewById(R.id.ced2);
        ced3=findViewById(R.id.ced3);
        img=findViewById(R.id.img);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        tv6=findViewById(R.id.tv6);
        tv7=findViewById(R.id.tv7);
        btn=findViewById(R.id.btn1);

        Inicializar_datos();

        GloabalVariable.Votos[0][0]="Vivian Valenzuela";
        GloabalVariable.Votos[1][0]="Omar Aizpurua";
        GloabalVariable.Votos[2][0]="Martín Candanedo";
        GloabalVariable.Votos[0][1]="0";
        GloabalVariable.Votos[1][1]="0";
        GloabalVariable.Votos[2][1]="0";
        GloabalVariable.Votos[0][2]="0 %";
        GloabalVariable.Votos[1][2]="0 %";
        GloabalVariable.Votos[2][2]="0 %";


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String cedula_1 = ced1.getText().toString();
                String cedula_2 = ced2.getText().toString();
                String cedula_3 = ced3.getText().toString();
                String cedula_completa;
                int aux =0;

                while (cedula_1.length()<2){
                    cedula_1= "0"+cedula_1;

                }
                while (cedula_2.length()<4){
                    cedula_2= "0"+cedula_2;

                }
                while (cedula_3.length()<6){
                    cedula_3= "0"+cedula_3;

                }
                cedula_completa = cedula_1+"-"+cedula_2+"-"+cedula_3;

                if (cedula_completa.equals("12-0345-006789")){
                    aux =4 ;
                }else {

                    for (int i = 0; i < GloabalVariable.datos.length; i++) {

                        if (GloabalVariable.datos[i][0].equals(cedula_completa)) {
                            if (GloabalVariable.datos[i][3].equals("0")) {
                                GloabalVariable.votante = i ;
                                aux = 1;
                                break;
                            } else {
                                aux = 2;
                                break;
                            }

                        } else {
                            aux = 3;


                        }

                    }

                }
                switch (aux){
                    case 1:
                        Intent intent = new Intent(getApplicationContext(), Votacion.class);
                        Log.e("Index votante", String.valueOf(GloabalVariable.votante));
                        Log.e("Verificacion", GloabalVariable.datos[(GloabalVariable.votante)][0]);
                        Log.e("Verificacion", GloabalVariable.datos[(GloabalVariable.votante)][1]);
                        Log.e("Verificacion", GloabalVariable.datos[(GloabalVariable.votante)][2]);
                        Log.e("Verificacion", GloabalVariable.datos[(GloabalVariable.votante)][3]);
                        startActivity(intent);

                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"Este usuario ya voto",Toast.LENGTH_SHORT).show();

                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"Esta cedula no esta disponible",Toast.LENGTH_SHORT).show();

                        break;

                    case 4:

                        break;



                }


            }

            });


    }

    @Override
    protected void onStart() {
        super.onStart();
        ced1=findViewById(R.id.ced1);
        ced2=findViewById(R.id.ced2);
        ced3=findViewById(R.id.ced3);
        ced1.setText("");
        ced2.setText("");
        ced3.setText("");


    }



    void Inicializar_datos (){

        AssetManager am = getAssets();

        try {
            InputStream myInput;

            //  Don't forget to Change to your assets folder excel sheet
            myInput = am.open("ListaProyecto.xls");

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            /** We now need something to iterate through the cells. **/
            Iterator<Row> rowIter = mySheet.rowIterator();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    if (myCell.getRowIndex()<39){

                        if(myCell.getColumnIndex()==3){
                           GloabalVariable.datos[myCell.getRowIndex()][myCell.getColumnIndex()]= "0";

                        }else{
                            GloabalVariable.datos[myCell.getRowIndex()][myCell.getColumnIndex()]= myCell.toString();

                        }
                    }


                }
            }

            Log.e("FileUtils", "Cell Value: " +GloabalVariable.datos[0][0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}