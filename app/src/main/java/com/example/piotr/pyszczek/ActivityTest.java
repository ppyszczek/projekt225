package com.example.piotr.pyszczek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityTest extends ActionBarActivity {

    ImageView img;
    TextView txt;
    Button b1;
    Button b2;
    Button b3;
    int[] pytania;
    String[] odpowiedzi;
    int[] mycase;
    int poprawne = 0;

    Button next;
    Button prev;

    TextView p1;
    TextView p2;
    TextView p3;
    TextView w1;
    TextView w2;
    TextView w3;

    boolean pokaz = false;
    private int counter;
    private List<Integer> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        img = (ImageView) findViewById(R.id.imageTest);
        txt = (TextView) findViewById(R.id.txt1);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        p1 = (TextView) findViewById(R.id.p1);
        p2 = (TextView) findViewById(R.id.p2);
        p3 = (TextView) findViewById(R.id.p3);
        w1 = (TextView) findViewById(R.id.w1);
        w2 = (TextView) findViewById(R.id.w2);
        w3 = (TextView) findViewById(R.id.w3);

        counter = 0;
        questions = new ArrayList<Integer>(MyData.INT_NUMBER_OF_QUESTIONS);
        pytania = new int[MyData.INT_NUMBER_OF_QUESTIONS];
        odpowiedzi = new String[MyData.INT_NUMBER_OF_QUESTIONS];
        mycase = new int[MyData.INT_NUMBER_OF_QUESTIONS];

        pokaz = getIntent().getBooleanExtra("pokaz", false);

        if (pokaz){

            pytania = getIntent().getIntArrayExtra("pytania");
            odpowiedzi = getIntent().getStringArrayExtra("odpowiedzi");
            mycase = getIntent().getIntArrayExtra("mycase");

            b1.setClickable(false);
            b2.setClickable(false);
            b3.setClickable(false);

            reload();

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (counter < MyData.INT_NUMBER_OF_QUESTIONS-1)
                        counter++;
                    reload();
                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (counter > 0) {
                        counter--;
                        reload();
                    }
                }
            });
        }
        else {
            for (int i = 0; i < MyData.INT_NUMBER_OF_QUESTIONS; i++) {
                questions.add(i);
            }
            getQuestion();
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveAnswer(1);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveAnswer(2);
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveAnswer(3);
                }
            });
        }

    }

    public void reload(){
        MyData.prepareQuestion(pytania[counter]);
        editLayoutTest();

        if(counter==0)
            prev.setVisibility(View.INVISIBLE);
        else
            prev.setVisibility(View.VISIBLE);
        if(counter==4)
            next.setVisibility(View.INVISIBLE);
        else
            next.setVisibility(View.VISIBLE);

        if (b1.getText().equals(MyData.answers[0])){
            p1.setVisibility(View.VISIBLE);
            p2.setVisibility(View.INVISIBLE);
            p3.setVisibility(View.INVISIBLE);
        }
        else if (b2.getText().equals(MyData.answers[0])){
            p1.setVisibility(View.INVISIBLE);
            p2.setVisibility(View.VISIBLE);
            p3.setVisibility(View.INVISIBLE);
        }
        else if (b3.getText().equals(MyData.answers[0])){
            p1.setVisibility(View.INVISIBLE);
            p2.setVisibility(View.INVISIBLE);
            p3.setVisibility(View.VISIBLE);
        }

        if (b1.getText().equals(odpowiedzi[counter])){
            w1.setVisibility(View.VISIBLE);
            w2.setVisibility(View.INVISIBLE);
            w3.setVisibility(View.INVISIBLE);
        }
        else if (b2.getText().equals(odpowiedzi[counter])){
            w1.setVisibility(View.INVISIBLE);
            w2.setVisibility(View.VISIBLE);
            w3.setVisibility(View.INVISIBLE);
        }
        else if (b3.getText().equals(odpowiedzi[counter])){
            w1.setVisibility(View.INVISIBLE);
            w2.setVisibility(View.INVISIBLE);
            w3.setVisibility(View.VISIBLE);
        }
    }

    public void saveAnswer(int inButtonNumber) {
        if (inButtonNumber == 1){
            odpowiedzi[counter] = b1.getText().toString();
            if(b1.getText().toString().equals(MyData.answers[0]))
                poprawne++;
        }
        else if (inButtonNumber ==2){
            odpowiedzi[counter] = b2.getText().toString();
            if(b2.getText().toString().equals(MyData.answers[0]))
                poprawne++;
        }
        else if (inButtonNumber == 3) {
            odpowiedzi[counter] = b3.getText().toString();
            if(b3.getText().toString().equals(MyData.answers[0]))
                poprawne++;
        }
        counter++;

        if(counter < 5){
            getQuestion();
        }
        else{
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("rozwiazany", true);
            i.putExtra("odpowiedzi", odpowiedzi);
            i.putExtra("mycase", mycase);
            i.putExtra("pytania", pytania);
            i.putExtra("poprawne", poprawne);
            startActivity(i);
            finish();

        }
    }

    public void getQuestion(){
        int rand = (int)Math.floor( Math.random()*(questions.size()-1));
        MyData.prepareQuestion(questions.get(rand));
        pytania[counter]= questions.get(rand);
        questions.remove(rand);

        editLayout();
    }

    public void editLayoutTest(){
        img.setImageResource(MyData.resourceId);
        txt.setText(MyData.description);

        int rand_quest = mycase[counter];

        switch(rand_quest){
            case 0:
                b1.setText(MyData.answers[0]);
                b2.setText(MyData.answers[1]);
                b3.setText(MyData.answers[2]);
                break;
            case 1:
                b1.setText(MyData.answers[1]);
                b2.setText(MyData.answers[0]);
                b3.setText(MyData.answers[2]);
                break;
            case 2:
                b1.setText(MyData.answers[2]);
                b2.setText(MyData.answers[0]);
                b3.setText(MyData.answers[1]);
                break;
            case 3:
                b1.setText(MyData.answers[0]);
                b2.setText(MyData.answers[2]);
                b3.setText(MyData.answers[1]);
                break;
            case 4:
                b1.setText(MyData.answers[1]);
                b2.setText(MyData.answers[0]);
                b3.setText(MyData.answers[2]);
                break;
            default:
                break;
        }


    }

    public void editLayout(){
        img.setImageResource(MyData.resourceId);
        txt.setText(MyData.description);

        int rand_quest = (int)Math.floor(Math.random()*4) ;

        mycase[counter] = rand_quest;

        switch(rand_quest){
            case 0:
                b1.setText(MyData.answers[0]);
                b2.setText(MyData.answers[1]);
                b3.setText(MyData.answers[2]);
                break;
            case 1:
                b1.setText(MyData.answers[1]);
                b2.setText(MyData.answers[2]);
                b3.setText(MyData.answers[0]);
                break;
            case 2:
                b1.setText(MyData.answers[2]);
                b2.setText(MyData.answers[0]);
                b3.setText(MyData.answers[1]);
                break;
            case 3:
                b1.setText(MyData.answers[2]);
                b2.setText(MyData.answers[1]);
                b3.setText(MyData.answers[0]);
                break;
            case 4:
                b1.setText(MyData.answers[0]);
                b2.setText(MyData.answers[2]);
                b3.setText(MyData.answers[1]);
                break;
            default:
                break;
        }


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