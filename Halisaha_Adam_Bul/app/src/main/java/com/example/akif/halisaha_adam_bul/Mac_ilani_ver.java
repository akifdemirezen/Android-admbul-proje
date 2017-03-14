package com.example.akif.halisaha_adam_bul;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.akif.halisaha_adam_bul.Entity.Ilan;
import com.example.akif.halisaha_adam_bul.Entity.IlanList;
import com.example.akif.halisaha_adam_bul.Entity.IlanLokId;
import com.example.akif.halisaha_adam_bul.Entity.IlaniVerenId;
import com.example.akif.halisaha_adam_bul.Entity.User;
import com.example.akif.halisaha_adam_bul.Remote.ApiService;
import com.google.android.gms.common.api.Api;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by akif on 13.2.2017.
 */

public class Mac_ilani_ver extends AppCompatActivity {


    EditText et_ilanver_halisaha,et_ekstraozellik;
    Button btn_tarih_tamam,btn_macilaniver_tarih,btn_ilanver_ilanver;
    String contents,segment;
    DatePicker dp_tarih;
    Spinner sp_ilanver_saat;
    String ilanver_mac_saati;
    String tarih;
    public static ArrayList<User> bildirimgonderilecek_userlar=new ArrayList<User>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_ilaniverme_sayfasi);

        //Görsellerin tanımlanması
        btn_tarih_tamam=(Button)findViewById(R.id.btn_tarih_tamam);
        btn_tarih_tamam.setVisibility(View.INVISIBLE);
        dp_tarih=(DatePicker)findViewById(R.id.datePicker2);
        dp_tarih.setVisibility(View.INVISIBLE);
        btn_ilanver_ilanver = (Button) findViewById(R.id.btn_ilanverme_ilanver);
        btn_macilaniver_tarih=(Button)findViewById(R.id.btn_macilanver_tarih);
        et_ekstraozellik=(EditText)findViewById(R.id.et_ekstraozellik);
        et_ilanver_halisaha=(EditText)findViewById(R.id.et_ilanver_halisaha);
        sp_ilanver_saat=(Spinner) findViewById(R.id.sp_ilanver_saat);

     //sp saatler
        sp_ilanver_saat = (Spinner) findViewById(R.id.sp_ilanver_saat);
        ArrayAdapter adaptersaatler = ArrayAdapter.createFromResource(Mac_ilani_ver.this,R.array.saatdizi,android.R.layout.simple_spinner_item);
        adaptersaatler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ilanver_saat.setAdapter(adaptersaatler);

        sp_ilanver_saat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ilanver_mac_saati=sp_ilanver_saat.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



// tarih seçme alanının açılıp kapanması
        btn_macilaniver_tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_macilaniver_tarih.setVisibility(View.INVISIBLE);

                dp_tarih.setVisibility(View.VISIBLE);
                btn_tarih_tamam.setVisibility(View.VISIBLE);
                dp_tarih.bringToFront();
                btn_tarih_tamam.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        dp_tarih.setVisibility(View.INVISIBLE);
                        btn_tarih_tamam.setVisibility(View.INVISIBLE);
                        int ayin_kaci = dp_tarih.getDayOfMonth()+1;
                        int secilenay=dp_tarih.getMonth()+1;
                        int yil=dp_tarih.getYear();

                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Date date = new Date(yil, secilenay, ayin_kaci+2);
                        String dayOfWeek = simpledateformat.format(date);
                        String ay=hangi_ay(secilenay);
                        btn_macilaniver_tarih.setVisibility(View.VISIBLE);
                        tarih=String.valueOf(ayin_kaci-1)+" "+ay+" "+dayOfWeek;
                        btn_macilaniver_tarih.setText(tarih);

                    }
                });

            }
        });


        btn_ilanver_ilanver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(macilaniver()==true){
                    Toast.makeText(Mac_ilani_ver.this, "Maç İlanınız Başarıyla Oluşturuldu", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Mac_ilani_ver.this, "Lütfen Tüm Alanları Doldurunuz", Toast.LENGTH_LONG).show();
                }



            }
        }); // İlan Ver Buton Kapanışı


    }

    public boolean macilaniver() {

        IlaniVerenId a=new IlaniVerenId(String.valueOf(AnaSayfa.Ana_kullanıcı.getId()));

        IlanLokId b=new IlanLokId(String.valueOf(AnaSayfa.Ana_kullanıcı.getLocationId().getId()));

        final String ilan_aciklama = "Maç Tarihi : "+tarih+" - Maç Saati : "+ilanver_mac_saati+" Halısaha Adı : "+et_ilanver_halisaha.getText().toString()+"  Maç Bilgileri : "+et_ekstraozellik.getText().toString();


        final String[] segment = new String[1];
        final String gonderilenmesaj;


        Ilan i = new Ilan(a,b, ilan_aciklama);

        if (et_ilanver_halisaha.getText().toString().equals("") || et_ekstraozellik.getText().toString().equals("") || btn_macilaniver_tarih.getText().toString().equals("Tarih Seçiniz")) {
            return false; //Açıklama boış bırakılırsa çalıştırma
        }else {

            //İlan verilen kısım
            Call<JSONObject> call = ApiService.Factory.getInstance().ilan_ekle(i);
            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {


                    Intent intent = new Intent(Mac_ilani_ver.this, AnaSayfa.class);
                    startActivity(intent);

                    //Ilan verildiğinde bildirim gönderme kısmı
                    contents = ilan_aciklama;
                    segment[0] = AnaSayfa.Ana_kullanıcı.getLocationId().getIl() + "_" + AnaSayfa.Ana_kullanıcı.getLocationId().getIlce();
                    Ilan gonderilecek_bildirim = new Ilan(contents, segment[0]);
                    Call<Ilan> call_bildirim = ApiService.Factory.getInstance().bildirim_gonder(gonderilecek_bildirim);
                    call_bildirim.enqueue(new Callback<Ilan>() {
                        @Override
                        public void onResponse(Call<Ilan> call, Response<Ilan> response) {


                        }

                        @Override
                        public void onFailure(Call<Ilan> call, Throwable t) {

                        }
                    });


                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    Toast.makeText(Mac_ilani_ver.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });


            return true;
        }

    }

    public String hangi_ay(int a){
        String ay="";
        if(a==1)
            ay="Ocak";
        else if(a==2)
        ay="Şubat";
        else if(a==3)
            ay="Mart";
        else if(a==4)
            ay="Nisan";
        else if(a==5)
            ay="Mayıs";
        else if(a==6)
            ay="Haziran";
        else if(a==7)
            ay="Temmuz";
        else if(a==8)
            ay="Ağustos";
        else if(a==9)
            ay="Eylül";
        else if(a==10)
            ay="Ekim";
        else if(a==11)
            ay="Kasım";
        else if(a==12)
            ay="Aralık";

        return ay;
    }



}


