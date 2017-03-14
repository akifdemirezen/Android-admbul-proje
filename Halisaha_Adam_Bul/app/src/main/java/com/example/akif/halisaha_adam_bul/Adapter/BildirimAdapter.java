package com.example.akif.halisaha_adam_bul.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.akif.halisaha_adam_bul.AnaSayfa;
import com.example.akif.halisaha_adam_bul.Bildirimler;
import com.example.akif.halisaha_adam_bul.Entity.Ilan;
import com.example.akif.halisaha_adam_bul.Entity.IlanList;
import com.example.akif.halisaha_adam_bul.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.akif.halisaha_adam_bul.Mac_Bul.mb;

/**
 * Created by akif on 10.3.2017.
 */

public class BildirimAdapter extends BaseAdapter {


    int i=0;
    private LayoutInflater mInflater;
    private IlanList il;
    private List<Ilan> IlanListesi;
    int kacinciilan=0;
    int kacsatirdonecek=0;

    ArrayList<Integer> gosterileceksatir=new ArrayList<Integer>();


    public BildirimAdapter(Activity activity, List<Ilan> liste) {

        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        IlanListesi = liste;
    }


    @Override
    public int getCount() {

        while(kacinciilan<IlanListesi.size()){

            Ilan verilericek = (Ilan) BildirimAdapter.this.getItem(kacinciilan);

            String adp_il=verilericek.getIlanlokId().getIl();
            String adp_ilce=verilericek.getIlanlokId().getIlce();

            String bildirim_il= AnaSayfa.Ana_kullanıcı.getLocationId().getIl();
            String bildirim_ilce=AnaSayfa.Ana_kullanıcı.getLocationId().getIlce();

            if(adp_il.equalsIgnoreCase(bildirim_il) && adp_ilce.equalsIgnoreCase(bildirim_ilce)){

                gosterileceksatir.add(kacinciilan);

                kacsatirdonecek++;

            }

            kacinciilan++;



        }



        return kacsatirdonecek;
    }




    @Override
    public Object getItem(int position) {
        return IlanListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View satirview;
        satirview=mInflater.inflate(R.layout.bildirimsatir,null);
        TextView txt_isim,txt_konum,txt_tel,txt_aciklama,txt_mactarihi;
        txt_isim = (TextView)satirview.findViewById(R.id.txt_bildirim_satir_kimden);
        txt_konum = (TextView)satirview.findViewById(R.id.txt_bildirim_satir_il_ilce);
        txt_tel = (TextView)satirview.findViewById(R.id.txt_bildirim_satir_tel);
        txt_aciklama = (TextView)satirview.findViewById(R.id.txt_bildirim_satir_aciklama);
        txt_mactarihi=(TextView)satirview.findViewById(R.id.txt_bildirim_satir_tarih);




        Ilan il = (Ilan) BildirimAdapter.this.getItem(gosterileceksatir.get(position));


        txt_isim.setText("İSİM :    " + il.getIlaniverenId().getName() + "   " + il.getIlaniverenId().getSurname());
        txt_konum.setText("İL : " + il.getIlanlokId().getIl() + "   İLÇE :" + il.getIlanlokId().getIlce());
        txt_tel.setText("TEL :    " + il.getIlaniverenId().getTel());
        String uzun_aciklama = il.getAciklama();
        String[] mac_saati;
        String[] halisaha;
        String mac_bilgileri;
        mac_saati = uzun_aciklama.split(" Halısaha Adı : ");
        halisaha = mac_saati[1].split("  Maç Bilgileri : ");
        mac_bilgileri = halisaha[1];
        txt_mactarihi.setText(mac_saati[0]);
        txt_aciklama.setText("HalıSaha Adı : " + halisaha[0] + "\n\nMaç Bilgileri : " + mac_bilgileri);



        return satirview;

    }
}
