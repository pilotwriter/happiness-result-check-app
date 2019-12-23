package com.example.ski_review;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AdminActivity extends AppCompatActivity {
    TextView txtGood;
    TextView txtNormal;
    TextView txtBad;
    TextView txtGoodPersonel;
    TextView txtNormalPersonel;
    TextView txtBadPersonel;
    TextView txtGoodEgitim;
    TextView txtNormalEgitim;
    TextView txtBadEgitim;
    TextView txtGoodEkipman;
    TextView txtNormalEkipman;
    TextView txtBadEkipman;
    ImageView imgBack;
    ImageView reset;

    public static final String SHARED_PREFS= "sharedPrefs";


    public int loadData(String type){
        int data;
        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE); // 0 - for private mode
        data = pref.getInt(type, 0);


        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        txtGood = (TextView)findViewById(R.id.textViewGoodAdmin);
        txtNormal = (TextView)findViewById(R.id.textViewNormalAdmin);
        txtBad = (TextView)findViewById(R.id.textViewBadAdmin);

        txtGoodPersonel = (TextView)findViewById(R.id.textViewGoodPersonelAdmin);
        txtNormalPersonel = (TextView)findViewById(R.id.textViewNormalPersonelAdmin);
        txtBadPersonel = (TextView)findViewById(R.id.textViewBadPersonelAdmin);

        txtGoodEgitim = (TextView)findViewById(R.id.textViewGoodEgitimAdmin);
        txtNormalEgitim = (TextView)findViewById(R.id.textViewNormalEgitimAdmin);
        txtBadEgitim = (TextView)findViewById(R.id.textViewBadEgitimAdmin);


        txtGoodEkipman = (TextView)findViewById(R.id.textViewGoodEkipmanAdmin);
        txtNormalEkipman = (TextView)findViewById(R.id.textViewNormalEkipmanAdmin);
        txtBadEkipman = (TextView)findViewById(R.id.textViewBadEkipmanAdmin);


        imgBack = (ImageView) findViewById(R.id.imageViewBack);
        reset = (ImageView) findViewById(R.id.imageViewReset);



        txtGood.setText("Toplam = "+(loadData("good"))+"");
        txtNormal.setText("Toplam = "+(loadData("normal"))+"");
        txtBad.setText("Toplam = "+(loadData("bad"))+"");

        txtGoodPersonel.setText("Personel = " + (loadData("personel_good")+""));
        txtNormalPersonel.setText("Personel = " +(loadData("personel_normal"))+"");
        txtBadPersonel.setText("Personel = " +(loadData("personel_bad"))+"");

        txtGoodEgitim.setText("Eğitim = "+(loadData("egitim_good"))+"");
        txtNormalEgitim.setText("Eğitim = "+(loadData("egitim_normal"))+"");
        txtBadEgitim.setText("Eğitim = "+(loadData("egitim_bad"))+"");

        txtGoodEkipman.setText("Ekipman = "+(loadData("ekipman_good"))+"");
        txtNormalEkipman.setText("Ekipman = "+(loadData("ekipman_normal"))+"");
        txtBadEkipman.setText("Ekipman = "+(loadData("ekipman_bad"))+"");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("good", 0); // Storing integer
                editor.putInt("normal", 0); // Storing integer
                editor.putInt("bad", 0); // Storing integer

                editor.putInt("personel_good", 0); // Storing integer
                editor.putInt("personel_normal", 0); // Storing integer
                editor.putInt("personel_bad", 0); // Storing integer

                editor.putInt("egitim_good", 0); // Storing integer
                editor.putInt("egitim_normal", 0); // Storing integer
                editor.putInt("egitim_bad", 0); // Storing integer

                editor.putInt("ekipman_good", 0); // Storing integer
                editor.putInt("ekipman_normal", 0); // Storing integer
                editor.putInt("ekipman_bad", 0); // Storing integer
                editor.apply();
                if (!isFinishing()){
                    new AlertDialog.Builder(AdminActivity.this)
                            .setTitle("Sıfırlama")
                            .setMessage("Tüm verileriniz sıfırlanmıştır.")
                            .setCancelable(false)
                            .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                    startActivity(intent);
                                }
                            }).show();

                }


            }
        });
    }
}
