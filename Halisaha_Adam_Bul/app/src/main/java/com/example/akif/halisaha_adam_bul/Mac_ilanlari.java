package com.example.akif.halisaha_adam_bul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.akif.halisaha_adam_bul.Adapter.IlanAdapter;
import com.example.akif.halisaha_adam_bul.Entity.Ilan;
import com.example.akif.halisaha_adam_bul.Entity.IlanList;
import com.example.akif.halisaha_adam_bul.Remote.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by akif on 14.2.2017.
 */

public class Mac_ilanlari extends AppCompatActivity {
     ListView lst;
     Button btn_macilanlari_anasayfa,btn_macilanlari_ilanver;

     TextView txt_ilanisim,txt_ilansoyisim,txt_ilantel,txt_ilanil,txt_ilanilce,txt_ilanaciklama;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_ilanlari);

        // Görsellerin tanımlanması
        lst=(ListView)findViewById(R.id.lst);
        btn_macilanlari_anasayfa=(Button)findViewById(R.id.btn_macilanlari_anasayfa);
        btn_macilanlari_ilanver=(Button)findViewById(R.id.btn_macilanlari_ilanver);



         ilan_goster();


        btn_macilanlari_anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Anasayfaya dön
                Intent intent=new Intent(Mac_ilanlari.this,AnaSayfa.class);
                startActivity(intent);
            }
        }); // Anasayfaya dön butonu kapanışı


        btn_macilanlari_ilanver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Maç ilanı verme sayfasına git
                Intent intent=new Intent(Mac_ilanlari.this,Mac_ilani_ver.class);
                startActivity(intent);
            }
        });  //  İlan Verme sayfasına geri dönme butonu


    }

    public boolean ilan_goster() {


       Call<IlanList> ca = ApiService.Factory.getInstance().ilan_getir();
        ca.enqueue(new Callback<IlanList>() {
            @Override
            public void onResponse(Call<IlanList> call, Response<IlanList> response) {



                IlanAdapter ilanadaptor=new IlanAdapter(Mac_ilanlari.this,response.body().getIlanlar());
                lst.setAdapter(ilanadaptor);




            }


            @Override
            public void onFailure(Call<IlanList> call, Throwable t) {

            }
        });
        return true;
    }


}
