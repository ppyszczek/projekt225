package com.example.piotr.pyszczek;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    final Context mContext = this;

    public boolean rozwiazany = false;
    int[] pytania;
    String[] odpowiedzi;
    int[] mycase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.startTest);
        Button btn2 = (Button) findViewById(R.id.chechTest);
        Button btn3 = (Button) findViewById(R.id.secondTest);

        final Intent i = new Intent(mContext, ActivityTest.class);

        rozwiazany = getIntent().getBooleanExtra("rozwiazany", false);

        if (rozwiazany){

            int poprawne = getIntent().getIntExtra("poprawne",0);

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Wynik");
            alertDialog.setMessage("Poprawne odpowiedzi: "+poprawne+"/"+MyData.INT_NUMBER_OF_QUESTIONS);
            alertDialog.setIcon(R.drawable.ic_launcher);
            alertDialog.show();

            pytania = new int[MyData.INT_NUMBER_OF_QUESTIONS];
            odpowiedzi = new String[MyData.INT_NUMBER_OF_QUESTIONS];
            mycase = new int[MyData.INT_NUMBER_OF_QUESTIONS];

            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("pytania", getIntent().getIntArrayExtra("pytania"));
                i.putExtra("odpowiedzi", getIntent().getStringArrayExtra("odpowiedzi"));
                i.putExtra("mycase", getIntent().getIntArrayExtra("mycase"));
                i.putExtra("pokaz", true);
                startActivity(i);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.removeExtra("pokaz");
                MyData.INT_TEST_NUMBER=2;
                startActivity(i);
                finish();
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.removeExtra("pokaz");
                MyData.INT_TEST_NUMBER=1;
                startActivity(i);
                finish();
            }
        });
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return super.onCreateOptionsMenu(menu);

        }

        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.about:
                    try {
                        PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                        String packageName = packageInfo.packageName;
                        int versionNumber = packageInfo.versionCode;

                        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setTitle("About");
                        alertDialog.setMessage("Projekt na PUMA\nAutor: Piotr Pyszczek\nNr.Alb: 225\nNazwa paczki: " + packageName + "\nWersja: "+ versionNumber);
                        alertDialog.setIcon(R.drawable.ic_launcher3);
                        alertDialog.show();
                    }
                    catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.end:
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

}
