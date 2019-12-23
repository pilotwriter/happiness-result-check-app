package com.example.ski_review;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;
    ImageView imageViewGood;
    ImageView imageViewNormal;
    ImageView imageViewBad;
    ImageView imageViewAdmin;
    ImageView imageViewTurkish;
    ImageView imageViewEnglish;

    int flagForLanguage = 0;

    public static final String SHARED_PREFS= "sharedPrefs";
    //SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    public void saveData(String type,int data){

        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(type, data); // Storing integer
        editor.apply();
    }

    public int loadData(String type){
        int data;
        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE); // 0 - for private mode
        data = pref.getInt(type, 0);


        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDialog = new Dialog(this);




        final TextView textGood = (TextView)findViewById(R.id.textViewGood);
        final TextView textNormal = (TextView)findViewById(R.id.textViewNormal);
        final TextView textBad = (TextView)findViewById(R.id.textViewBad);
        final TextView header = (TextView)findViewById(R.id.textView);

        imageViewAdmin = (ImageView)findViewById(R.id.imageViewToAdmin);
        imageViewGood = (ImageView)findViewById(R.id.imageViewGood);
        imageViewBad = (ImageView)findViewById(R.id.imageViewBad);
        imageViewNormal = (ImageView)findViewById(R.id.imageViewNormal);
        imageViewTurkish = (ImageView)findViewById(R.id.imageView);
        imageViewEnglish = (ImageView)findViewById(R.id.imageView2);

        imageViewTurkish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textGood.setText("İYİ");
                textNormal.setText("ORTA");
                textBad.setText("KÖTÜ");
                header.setText("Aldığınız hizmetten memnun musunuz?");
                imageViewTurkish.setVisibility(View.INVISIBLE);
                imageViewEnglish.setVisibility(View.VISIBLE);
                flagForLanguage = 0;
            }
        });
        imageViewEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textGood.setText("GOOD");
                textNormal.setText("NORMAL");
                textBad.setText("BAD");
                imageViewTurkish.setVisibility(View.VISIBLE);
                imageViewEnglish.setVisibility(View.INVISIBLE);
                header.setText("Are you happy the service that you take?");

                flagForLanguage = 1;
            }
        });
        imageViewGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                myDialog.setContentView(R.layout.custom_layout);

                ImageView imageClose = (ImageView)myDialog.findViewById(R.id.closeCustomLayout);
                final CheckBox personelCheckBox = (CheckBox)myDialog.findViewById(R.id.personelCheckBox);
                final CheckBox egitimCheckBox = (CheckBox)myDialog.findViewById(R.id.egitimCheckBox);
                final CheckBox ekipmanCheckBox = (CheckBox)myDialog.findViewById(R.id.ekipmanCheckBox);
                final TextView textViewPersonel = (TextView)myDialog.findViewById(R.id.textViewPersonel);
                final TextView textViewEgitim = (TextView)myDialog.findViewById(R.id.textViewEgitim);
                final TextView textViewEkipman = (TextView)myDialog.findViewById(R.id.textViewEkipman);
                Button sendTheInfo = (Button)myDialog.findViewById(R.id.sendTheInfo);
                if(flagForLanguage==1){
                    textViewPersonel.setText("Staff");
                    textViewEgitim.setText("Training");
                    textViewEkipman.setText("Equipment");
                    sendTheInfo.setText("Send");
                }
                if(flagForLanguage==0){
                    textViewPersonel.setText("Personel");
                    textViewEgitim.setText("Eğitim");
                    textViewEkipman.setText("Ekipman");
                    sendTheInfo.setText("Gönder");
                }
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();


                sendTheInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int data = loadData("good");
                        data = data+1;
                        saveData("good", data);
                        System.out.println("good"+ data);
                        if(egitimCheckBox.isChecked()){
                            data = loadData("egitim_good");
                            data = data+1;
                            saveData("egitim_good", data);
                            System.out.println("egitim_good "+ data);
                        }
                        if(personelCheckBox.isChecked()){
                            data = loadData("personel_good");
                            data = data+1;
                            saveData("personel_good", data);
                            System.out.println("personel_good "+ data);
                        }
                        if(ekipmanCheckBox.isChecked()){
                            data = loadData("ekipman_good");
                            data = data+1;
                            saveData("ekipman_good", data);
                            System.out.println("ekipman_good "+ data);
                        }
                        myDialog.dismiss();
                    }
                });

                if(!myDialog.isShowing()) {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Sayın Müşterimiz")
                                .setMessage("Fikirlerinizi Bizimle Paylaştığınız İçin Teşekkür Ederiz")
                                .setCancelable(false)
                                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                    }
                                }).show();
                    }
                }

            }
        });

        imageViewNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.custom_layout);

                ImageView imageClose = (ImageView)myDialog.findViewById(R.id.closeCustomLayout);
                final CheckBox personelCheckBox = (CheckBox)myDialog.findViewById(R.id.personelCheckBox);
                final CheckBox egitimCheckBox = (CheckBox)myDialog.findViewById(R.id.egitimCheckBox);
                final CheckBox ekipmanCheckBox = (CheckBox)myDialog.findViewById(R.id.ekipmanCheckBox);
                final TextView textViewPersonel = (TextView)myDialog.findViewById(R.id.textViewPersonel);
                final TextView textViewEgitim = (TextView)myDialog.findViewById(R.id.textViewEgitim);
                final TextView textViewEkipman = (TextView)myDialog.findViewById(R.id.textViewEkipman);
                Button sendTheInfo = (Button)myDialog.findViewById(R.id.sendTheInfo);
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                if(flagForLanguage==1){
                    textViewPersonel.setText("Staff");
                    textViewEgitim.setText("Training");
                    textViewEkipman.setText("Equipment");
                    sendTheInfo.setText("Send");
                }
                if(flagForLanguage==0){
                    textViewPersonel.setText("Personel");
                    textViewEgitim.setText("Eğitim");
                    textViewEkipman.setText("Ekipman");
                    sendTheInfo.setText("Gönder");
                }
                myDialog.show();


                sendTheInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int data = loadData("normal");
                        data = data+1;
                        saveData("normal", data);
                        System.out.println("normal"+ data);
                        if(egitimCheckBox.isChecked()){
                            data = loadData("egitim_normal");
                            data = data+1;
                            saveData("egitim_normal", data);
                            System.out.println("egitim_normal "+ data);
                        }
                        if(personelCheckBox.isChecked()){
                            data = loadData("personel_normal");
                            data = data+1;
                            saveData("personel_normal", data);
                            System.out.println("personel_normal "+ data);
                        }
                        if(ekipmanCheckBox.isChecked()){
                            data = loadData("ekipman_normal");
                            data = data+1;
                            saveData("ekipman_normal", data);
                            System.out.println("ekipman_normal "+ data);
                        }
                        myDialog.dismiss();
                    }
                });

                if(!myDialog.isShowing()) {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Sayın Müşterimiz")
                                .setMessage("Fikirlerinizi Bizimle Paylaştığınız İçin Teşekkür Ederiz")
                                .setCancelable(false)
                                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                    }
                                }).show();
                    }
                }
            }
        });

        imageViewBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.custom_layout);

                ImageView imageClose = (ImageView)myDialog.findViewById(R.id.closeCustomLayout);
                final CheckBox personelCheckBox = (CheckBox)myDialog.findViewById(R.id.personelCheckBox);
                final CheckBox egitimCheckBox = (CheckBox)myDialog.findViewById(R.id.egitimCheckBox);
                final CheckBox ekipmanCheckBox = (CheckBox)myDialog.findViewById(R.id.ekipmanCheckBox);
                final TextView textViewPersonel = (TextView)myDialog.findViewById(R.id.textViewPersonel);
                final TextView textViewEgitim = (TextView)myDialog.findViewById(R.id.textViewEgitim);
                final TextView textViewEkipman = (TextView)myDialog.findViewById(R.id.textViewEkipman);
                Button sendTheInfo = (Button)myDialog.findViewById(R.id.sendTheInfo);
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                if(flagForLanguage==1){
                    textViewPersonel.setText("Staff");
                    textViewEgitim.setText("Training");
                    textViewEkipman.setText("Equipment");
                    sendTheInfo.setText("Send");
                }
                if(flagForLanguage==0){
                    textViewPersonel.setText("Personel");
                    textViewEgitim.setText("Eğitim");
                    textViewEkipman.setText("Ekipman");
                    sendTheInfo.setText("Gönder");
                }
                myDialog.show();


                sendTheInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int data = loadData("bad");
                        data = data+1;
                        saveData("bad", data);
                        System.out.println("bad"+ data);
                        if(egitimCheckBox.isChecked()){
                            data = loadData("egitim_bad");
                            data = data+1;
                            saveData("egitim_bad", data);
                            System.out.println("egitim_bad "+ data);
                        }
                        if(personelCheckBox.isChecked()){
                            data = loadData("personel_bad");
                            data = data+1;
                            saveData("personel_bad", data);
                            System.out.println("personel_bad "+ data);
                        }
                        if(ekipmanCheckBox.isChecked()){
                            data = loadData("ekipman_bad");
                            data = data+1;
                            saveData("ekipman_bad", data);
                            System.out.println("ekipman_bad "+ data);
                        }
                        myDialog.dismiss();
                    }
                });

                if(!myDialog.isShowing()) {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Sayın Müşterimiz")
                                .setMessage("Fikirlerinizi Bizimle Paylaştığınız İçin Teşekkür Ederiz")
                                .setCancelable(false)
                                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                    }
                                }).show();
                    }
                }
            }
        });
        imageViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
