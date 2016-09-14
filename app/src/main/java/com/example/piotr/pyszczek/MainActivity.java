package com.example.piotr.pyszczek;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
