package com.example.akif.halisaha_adam_bul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.content.ContentResolver;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.akif.halisaha_adam_bul.Entity.RegisterID;
import com.example.akif.halisaha_adam_bul.Entity.User;
import com.example.akif.halisaha_adam_bul.Entity.UserSession;
import com.example.akif.halisaha_adam_bul.Remote.ApiService;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;
import android.provider.Settings.Secure;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.provider.Settings.Secure;
import static java.security.AccessController.getContext;

/**
 * Created by akif on 13.2.2017.
 */

public class KayitOl_Sayfasi extends AppCompatActivity {
    Button btn_kayditamamla;
    Spinner sp_Kayit_iller,sp_Kayit_ilceler;
    EditText et_Kayit_kullaniciadi,et_Kayit_sifre,et_Kayit_isim,et_Kayit_soyisim,et_Kayit_telefon,et_Kayit_yas;
    String app_id="bff09b26-ecde-446d-a8ff-0b83f23edeab";
    int device_type=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayitol_sayfasi);





 //EditTextler Tanımlama
        et_Kayit_kullaniciadi=(EditText)findViewById(R.id.et_Kayit_kullaniciadi);
        et_Kayit_sifre=(EditText)findViewById(R.id.et_Kayit_Sifre);
        et_Kayit_isim=(EditText)findViewById(R.id.et_Kayit_isim);
        et_Kayit_soyisim=(EditText)findViewById(R.id.et_Kayit_Soyisim);
        et_Kayit_telefon=(EditText)findViewById(R.id.et_Kayit_telefon);
        et_Kayit_yas=(EditText)findViewById(R.id.et_Kayit_Yas);
        btn_kayditamamla=(Button)findViewById(R.id.btn_Kayit_Kayditamamla);
//sp sehirler
        sp_Kayit_iller = (Spinner) findViewById(R.id.sp_Kayit_sehirler);
        ArrayAdapter adaptersehirler = ArrayAdapter.createFromResource(this,R.array.sehirdizi,android.R.layout.simple_spinner_item);
        adaptersehirler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_Kayit_iller.setAdapter(adaptersehirler);

//sp ilceler
        sp_Kayit_ilceler = (Spinner) findViewById(R.id.sp_Kayit_ilceler);

//illere tıklandığında yapılacka olaylar
        sp_Kayit_iller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_Kayit_iller.getSelectedItem().toString().equalsIgnoreCase("İstanbul")) {
                    ArrayAdapter adapteristanbulilceler = ArrayAdapter.createFromResource(KayitOl_Sayfasi.this, R.array.istanbulilcedizi, android.R.layout.simple_spinner_item);
                    adapteristanbulilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_Kayit_ilceler.setAdapter(adapteristanbulilceler);

                } else if (sp_Kayit_iller.getSelectedItem().toString().equalsIgnoreCase("Ankara")) {
                    ArrayAdapter adapterankarailceler = ArrayAdapter.createFromResource(KayitOl_Sayfasi.this, R.array.ankarailcedizi, android.R.layout.simple_spinner_item);
                    adapterankarailceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_Kayit_ilceler.setAdapter(adapterankarailceler);

                } else if (sp_Kayit_iller.getSelectedItem().toString().equalsIgnoreCase("İzmir")) {
                    ArrayAdapter adapterizmirilceler = ArrayAdapter.createFromResource(KayitOl_Sayfasi.this, R.array.izmirilcedizi, android.R.layout.simple_spinner_item);
                    adapterizmirilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_Kayit_ilceler.setAdapter(adapterizmirilceler);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_kayditamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        KayitOl_Sayfasi.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // boyle_kullanici_varmi();

                                if(kullaniciolustur()==true) {
                                    kullaniciolustur();
                                    //Kayıt işleminden sonra anasayfaya gönder
                                    Intent intent = new Intent(KayitOl_Sayfasi.this,MainActivity.class);
                                    startActivity(intent);
                                }else {

                                    Toast.makeText(KayitOl_Sayfasi.this, "Lütfen Boş Alanları Doldurunuz", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });  //runOnUiThread kapanış


            }
        }).start();  //Kaydı tamamla buton thread kapanış
            } //Kaydı Tamamla OnClick
        });

    }


    public boolean kullaniciolustur(){


        String kullaniciadi=et_Kayit_kullaniciadi.getText().toString();
        String sifre=et_Kayit_sifre.getText().toString();
        String isim=et_Kayit_isim.getText().toString();
        String soyisim=et_Kayit_soyisim.getText().toString();
        String tel=et_Kayit_telefon.getText().toString();
        String yas=et_Kayit_yas.getText().toString();
        String il=sp_Kayit_iller.getSelectedItem().toString();
        String ilce=sp_Kayit_ilceler.getSelectedItem().toString();

        final String[] register_id = new String[1];




        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {

                register_id[0] =userId;

            }
        });

        OneSignal.sendTag(il,ilce);




        if(kullaniciadi.equals("") || sifre.equals("") || isim.equals("") ||  soyisim.equals("") ||  tel.equals("") ||  yas.equals("") ||  il.equals("") ||  ilce.equals("")){
            return false;
        }else {

            //player_id parametresi gelecek
            User u = new User(kullaniciadi, sifre, isim, soyisim, tel, yas, register_id[0], il, ilce);
            Call<JSONObject> calluser = ApiService.Factory.getInstance().user_ekle(u);
            calluser.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                    Toast.makeText(KayitOl_Sayfasi.this, "BAŞARIYLA KAYIT OLDUNUZ \nLUTFEN KULLANICI ADINIZI VE ŞİFRENİZİ GİRİNİZ", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {

                }
            });






        }//else kapanış
return true;

    }

    public void boyle_kullanici_varmi(){//Kullanıcı adı varmı yokmu kontrol ediyor

        Call<UserSession> ca=ApiService.Factory.getInstance().getalluser();
        ca.enqueue(new Callback<UserSession>() {
            @Override
            public void onResponse(Call<UserSession> call, Response<UserSession> response) {

                for (int i=0;i<=response.body().getDataList().size();i++){

                     if(et_Kayit_kullaniciadi.getText().toString().equalsIgnoreCase(response.body().getDataList().get(i).getUsername())){

                         Toast.makeText(KayitOl_Sayfasi.this, "Böyle Bir Kullanıcı Adı Zaten Var", Toast.LENGTH_SHORT).show();
                        break;

                     }

                }

            }

            @Override
            public void onFailure(Call<UserSession> call, Throwable t) {


            }
        });


    }


}
