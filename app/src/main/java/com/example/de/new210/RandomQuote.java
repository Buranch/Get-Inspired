package com.example.de.new210;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by DE on 9/29/2016.
 */
public class RandomQuote extends AppCompatActivity{
    public static String [] biggerDb;
    ArrayList<Integer> index;
    private int length;
    private int i;
    TextView quoteDat;
    CheckBox randCheckbox;
    private int one_index;
    Animation slideRight;
    Animation slideLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quoterandom);
        quoteDat = (TextView) findViewById(R.id.randomquote);
        randCheckbox = (CheckBox) findViewById(R.id.randomcheckbox);
        View v = findViewById(R.id.randomcontainer);


        biggerDb = mySearchActivity.concateAll(getResources().getStringArray(R.array.warrenBuffet), getResources().getStringArray(R.array.william_shakespeare),getResources().getStringArray(R.array.zeal), getResources().getStringArray(R.array.humour), getResources().getStringArray(R.array.inspirationl), getResources().getStringArray(R.array.affirmation), getResources().getStringArray(R.array.passion), getResources().getStringArray(R.array.encouragment), getResources().getStringArray(R.array.happiness), getResources().getStringArray(R.array.motivational), getResources().getStringArray(R.array.determination), getResources().getStringArray(R.array.goals), getResources().getStringArray(R.array.purpose), getResources().getStringArray(R.array.success));
        int k;
        i = 2 ;
        index = new ArrayList<Integer>();
        length = biggerDb.length;
        for (k = 0; k < length - 2; k++) {

            index.add(k);

        }
        Collections.shuffle(index);
        quoteDat.setText(Html.fromHtml(biggerDb[index.get(1)]));
        slideRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);

        slideLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);


        v.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeLeft() {

                quoteDat.startAnimation(slideRight);
                randCheckbox.startAnimation(slideRight);
                myLooper();



            }
            public void onSwipeRight(){
                quoteDat.startAnimation(slideLeft);
                randCheckbox.startAnimation(slideLeft);
                reverseLooper();
            }
        });

    }

    private void myLooper(){

        if(i <= length-1){

            i = i +1;
            if(i == length) i = length-1;
            //title.setText(solarSystem[i]);
            one_index = index.get(i);
            quoteDat.setText(Html.fromHtml(biggerDb[one_index]));

        }
        else{
            i = 1;
            myLooper();
        }
    }

    private void reverseLooper(){
        if(i<=length-1 && i>0){
            i-=1;
            one_index = index.get(i);
            quoteDat.setText(Html.fromHtml(biggerDb[one_index]));

        }
        else{
            i = 0;
            one_index = index.get(i);
            quoteDat.setText(Html.fromHtml(biggerDb[one_index]));
        }
    }

}
