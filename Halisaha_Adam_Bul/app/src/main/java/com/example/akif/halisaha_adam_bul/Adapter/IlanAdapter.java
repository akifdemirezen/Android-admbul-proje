package com.example.akif.halisaha_adam_bul.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.akif.halisaha_adam_bul.Entity.Ilan;
import com.example.akif.halisaha_adam_bul.Entity.IlanList;
import com.example.akif.halisaha_adam_bul.Mac_Bul;
import com.example.akif.halisaha_adam_bul.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.akif.halisaha_adam_bul.Mac_Bul.mb;


/**
 * Created by akif on 23.2.2017.
 */

public class IlanAdapter extends BaseAdapter {

    int i=0;
    private LayoutInflater mInflater;
    private IlanList il;
    private List<Ilan> IlanListesi;
    int kacinciilan=0;
    int kacsatirdonecek=0;

    ArrayList<Integer> gosterileceksatir=new ArrayList<Integer>();


    public IlanAdapter(Activity activity, List<Ilan> liste) {

        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        IlanListesi = liste;
    }



    @Override
    public int getCount() {


        while(kacinciilan<IlanListesi.size()){

            Ilan verilericek = IlanAdapter.this.getItem(kacinciilan);

            String adp_il=verilericek.getIlanlokId().getIl();
            String adp_ilce=verilericek.getIlanlokId().getIlce();

            String secilenil=mb.getMacbul_il().toString();
            String secilenilce=mb.getMacbul_ilce().toString();

            if(adp_il.equalsIgnoreCase(secilenil) && adp_ilce.equalsIgnoreCase(secilenilce)){

                gosterileceksatir.add(kacinciilan);

                kacsatirdonecek++;

            }

            kacinciilan++;



        }



        return kacsatirdonecek;
}


    @Override
    public Ilan getItem(int position) {
        return IlanListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View satirview;
        satirview=mInflater.inflate(R.layout.ilansatir,null);
        TextView txt_isim,txt_konum,txt_tel,txt_aciklama;
        txt_isim = (TextView)satirview.findViewById(R.id.txt_satir_isim);
        txt_konum = (TextView)satirview.findViewById(R.id.txt_satir_konum);
        txt_tel = (TextView)satirview.findViewById(R.id.txt_satir_tel);
        txt_aciklama = (TextView)satirview.findViewById(R.id.txt_satir_aciklama);




           Ilan il = IlanAdapter.this.getItem(gosterileceksatir.get(position));


           txt_isim.setText("İSİM : " + il.getIlaniverenId().getName() + " " + il.getIlaniverenId().getSurname());
           txt_konum.setText("İL : " + il.getIlanlokId().getIl() + "   İLÇE :" + il.getIlanlokId().getIlce());
           txt_tel.setText("TEL : " + il.getIlaniverenId().getTel());
           String uzun_aciklama = il.getAciklama();
           String[] mac_saati;
           String[] halisaha;
           String mac_bilgileri;
           mac_saati = uzun_aciklama.split(" Halısaha Adı : ");
           halisaha = mac_saati[1].split("  Maç Bilgileri : ");
           mac_bilgileri = halisaha[1];
           txt_aciklama.setText(mac_saati[0] + "\n\nHalıSaha Adı : " + halisaha[0] + "\n\nMaç Bilgileri : " + mac_bilgileri);



     return satirview;

    }
}
