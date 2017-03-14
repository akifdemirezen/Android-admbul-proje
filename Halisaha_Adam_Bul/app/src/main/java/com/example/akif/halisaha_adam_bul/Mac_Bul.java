package com.example.akif.halisaha_adam_bul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import javax.crypto.Mac;

/**
 * Created by akif on 14.2.2017.
 */

public class Mac_Bul extends AppCompatActivity {

    public static Mac_Bul mb;
    String macbul_il,macbul_ilce;

    public String getMacbul_il() {
        return macbul_il;
    }

    public void setMacbul_il(String macbul_il) {
        this.macbul_il = macbul_il;
    }

    public String getMacbul_ilce() {
        return macbul_ilce;
    }

    public void setMacbul_ilce(String macbul_ilce) {
        this.macbul_ilce = macbul_ilce;
    }

    Spinner sp_macbul_il,sp_macbul_ilce;
    Button btn_macbul_ilangoster,btn_macbulma_anasayfayadon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_bulma_sayfasi);

    // Görsellerin Tanımlanması

    btn_macbul_ilangoster=(Button)findViewById(R.id.btn_macbul_ilangoster);
    btn_macbulma_anasayfayadon=(Button)findViewById(R.id.btn_macbulma_anasayfadon);
        //sp sehirler
        sp_macbul_il = (Spinner) findViewById(R.id.spn_macbul_il);
        ArrayAdapter adaptersehirler = ArrayAdapter.createFromResource(this,R.array.sehirdizi,android.R.layout.simple_spinner_item);
        adaptersehirler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_macbul_il.setAdapter(adaptersehirler);

//sp ilceler
        sp_macbul_ilce = (Spinner) findViewById(R.id.spn_macbul_ilce);

        //illere tıklandığında yapılacka olaylar
        sp_macbul_il.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_macbul_il.getSelectedItem().toString().equalsIgnoreCase("İstanbul")) {
                    ArrayAdapter adapteristanbulilceler = ArrayAdapter.createFromResource(Mac_Bul.this, R.array.istanbulilcedizi, android.R.layout.simple_spinner_item);
                    adapteristanbulilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_macbul_ilce.setAdapter(adapteristanbulilceler);


                sp_macbul_ilce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        macbul_il=sp_macbul_il.getSelectedItem().toString();
                        macbul_ilce=sp_macbul_ilce.getSelectedItem().toString();
                        mb=new Mac_Bul(macbul_il,macbul_ilce);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




                } else if (sp_macbul_il.getSelectedItem().toString().equalsIgnoreCase("Ankara")) {
                    ArrayAdapter adapterankarailceler = ArrayAdapter.createFromResource(Mac_Bul.this, R.array.ankarailcedizi, android.R.layout.simple_spinner_item);
                    adapterankarailceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_macbul_ilce.setAdapter(adapterankarailceler);


                    sp_macbul_ilce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            macbul_il=sp_macbul_il.getSelectedItem().toString();
                            macbul_ilce=sp_macbul_ilce.getSelectedItem().toString();
                            mb=new Mac_Bul(macbul_il,macbul_ilce);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else if (sp_macbul_il.getSelectedItem().toString().equalsIgnoreCase("İzmir")) {
                    ArrayAdapter adapterizmirilceler = ArrayAdapter.createFromResource(Mac_Bul.this, R.array.izmirilcedizi, android.R.layout.simple_spinner_item);
                    adapterizmirilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_macbul_ilce.setAdapter(adapterizmirilceler);


                    sp_macbul_ilce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            macbul_il=sp_macbul_il.getSelectedItem().toString();
                            macbul_ilce=sp_macbul_ilce.getSelectedItem().toString();
                            mb=new Mac_Bul(macbul_il,macbul_ilce);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_macbul_ilangoster.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                //Şehirleri filtreledikten sonra maç ilanları sayfasına götür
                 Intent intent=new Intent(Mac_Bul.this,Mac_ilanlari.class);
                 startActivity(intent);
             }
         });

        btn_macbulma_anasayfayadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Şehirleri filtreledikten sonra maç ilanları sayfasına götür
                Intent intent=new Intent(Mac_Bul.this,AnaSayfa.class);
                startActivity(intent);
            }
        });


    }

    public Mac_Bul(){

        this.macbul_il="";
        this.macbul_ilce="";
    }
    public Mac_Bul(String il,String ilce) {

        this.macbul_il=il;
        this.macbul_ilce=ilce;

    }
}
