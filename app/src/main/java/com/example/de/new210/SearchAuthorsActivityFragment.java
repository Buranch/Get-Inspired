package com.example.de.new210;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchAuthorsActivityFragment extends Fragment {

    private static String [] bigDb;
    private static int length;
    private View v;
    private int i = 2;
    private int j = 1;
    private int one_index;
    public static TextView title;
    public static CheckBox checkBox;
    public boolean [] checkBoxState;
    private Animation slideRight;
    private Animation slideLeft;
    private TextView showIndex;
    private ArrayList<Integer> index;
    private ArrayList<Integer> picRand;
    SharedPreference sharedPreference;


    public SearchAuthorsActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bigDb = mySearchActivity.biggerDb;

        slideRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);

        slideLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
        sharedPreference = new SharedPreference();



        length = bigDb.length;

        checkBoxState = new boolean[length+1];


        index = new ArrayList<Integer>();
        picRand = new ArrayList<Integer>();
        ArrayList<Integer> picArray = new ArrayList<Integer>();
        picArray.add(R.drawable.flower);
        picArray.add(R.drawable.green);
        picArray.add(R.drawable.paris);
        picArray.add(R.drawable.river);
        picArray.add(R.drawable.city);
        picArray.add(R.drawable.aurora);
        picArray.add(R.drawable.sunrise);
        picArray.add(R.drawable.shoes);
        picArray.add(R.drawable.island);
        picArray.add(R.drawable.white_tree1);
        picArray.add(R.drawable.vivid);
        picArray.add(R.drawable.sunrise);
        picArray.add(R.drawable.luis);
        picRand = new ArrayList<Integer>();
        for (i  = 1; i< picArray.size(); i++){
            picRand.add(i);
        }
        i = 1;
        int k;
       for( k = 0; k<length-2; k++){
            index.add(k);
        }
        Collections.shuffle(index);
        Collections.shuffle(picRand);
        j = picRand.get(1);
        v = inflater.inflate(R.layout.fragment_authors, container, false);
        title = (TextView) v.findViewById(R.id.textView);
        checkBox = (CheckBox) v.findViewById(R.id.checkBox1);


        showIndex = (TextView) v.findViewById(R.id.show_index);
        final String currentQuery = SearchAuthorsActivity.currentString;
        title.setText(Html.fromHtml(currentQuery));
        showIndex.setText("1" + "/" +length);
        if(picArray.get(j) == R.drawable.vivid
                || picArray.get(j) == R.drawable.green
                || picArray.get(j) == R.drawable.paris
                || picArray.get(j) == R.drawable.aurora
                || picArray.get(j) == R.drawable.book
                || picArray.get(j) == R.drawable.city
                || picArray.get(j) == R.drawable.shoes
                || picArray.get(j) == R.drawable.sunrise){
            title.setTextColor(getResources().getColor(R.color.white));
        }

        if(picArray.get(j) == R.drawable.luis){
            title.setWidth(430);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()){
                    //Log.d("Favorite ", ""+ solarSystem[index.get(i)]);
                    //Log.d("i is: ",""+i);
                    //Log.d("Index is: i: ", ""+index.get(i));
                    Toast.makeText(getActivity().getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                    checkBoxState[i]=true;
                    if(i == 1){
                        sharedPreference.addFavorite(getActivity().getApplicationContext(), currentQuery);
                        Log.d("Toast ", "Excuted"+currentQuery);
                    }
                    else{

                        sharedPreference.addFavorite(getActivity().getApplicationContext(), bigDb[index.get(i)]);
                        Log.d("Toast ", "Excuted"+bigDb[index.get(i)]);

                    }}
                else
                    checkBoxState[i]=false;
            }
        });


        v.setBackground(getResources().getDrawable(picArray.get(j)));
        v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeLeft() {
                title.startAnimation(slideRight);
                myLooper();
                checkBox.startAnimation(slideRight);
                checkBox.setChecked(checkBoxState[i]);

            }

            public void onSwipeRight() {

                title.startAnimation(slideLeft);
                reverseLooper();
                checkBox.startAnimation(slideLeft);
                checkBox.setChecked(checkBoxState[i]);
            }
        });

        return v;
    }

    private void myLooper(){

        if(i <= length-1){

            i = i +1;
            if(i == length) i = length-1;
            //title.setText(solarSystem[i]);
            one_index = index.get(i);

            title.setText(Html.fromHtml(bigDb[one_index]));
            showIndex.setText(""+i+"/"+length);
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
            title.setText(Html.fromHtml(bigDb[one_index]));
            showIndex.setText(""+i+"/"+length);
        }
        else{
            i = 0;
            one_index = index.get(i);
            title.setText(Html.fromHtml(bigDb[one_index]));
            showIndex.setText("" + i + "/" + length);
        }
    }




}
