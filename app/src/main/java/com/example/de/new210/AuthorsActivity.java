package com.example.de.new210;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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


public class AuthorsActivity extends Fragment {
    public static TextView toBeSent;

    private TextView title;
    private CheckBox checkBox;

    private TextView showIndex;
    private Animation slideRight;
    private Animation slideLeft;
    int removedFavoriteIndex;
    private View v;
    private ArrayList<Integer> index;
    private int length;
    private int i = 2;
    ArrayList<String> favorites;
    private String category;
    SharedPreference sharedPreference;
    private int one_index;
    private static String[] solarSystem;
   /*
    private static String[] humourousQ;
    private static String[] inspirationalQ;
    private static String[] motivationalQ;
    private static String[] goalsQ;
    private static String[] zealQ;
    private static String[] purposeQ;
    private static String[] successQ;
    private static String[] passionQ;
    private static String[] affirmationQ;
    private static String[] encouragementQ;
    private static String[] happinessQ;
    private static String[] determinationQ;
*/

   public boolean [] checkBoxState;



    public AuthorsActivity() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_authors, container, false);

        category = (String) getActivity().getTitle();
        /*
        encouragementQ = getResources().getStringArray(R.array.encouragment);
        happinessQ = getResources().getStringArray(R.array.happiness);
        affirmationQ = getResources().getStringArray(R.array.affirmation);
        passionQ = getResources().getStringArray(R.array.passion);
        inspirationalQ = getResources().getStringArray(R.array.inspirationl);
        determinationQ = getResources().getStringArray(R.array.determination);
        motivationalQ = getResources().getStringArray(R.array.motivational);
        goalsQ = getResources().getStringArray(R.array.goals);
        purposeQ = getResources().getStringArray(R.array.purpose);
       successQ = getResources().getStringArray(R.array.success);
        zealQ = getResources().getStringArray(R.array.zeal);
        humourousQ = getResources().getStringArray(R.array.humour);
        */
        title = (TextView) v.findViewById(R.id.textView);
        checkBox = (CheckBox) v.findViewById(R.id.checkBox1);
        categorySetter();
        sharedPreference = new SharedPreference();
        //v = inflater.inflate(R.layout.fragment_authors, container, false);
        showIndex = (TextView) v.findViewById(R.id.show_index);

        length = solarSystem.length;
/*
        int total = length + purposeQ.length + goalsQ.length + successQ.length + determinationQ.length + motivationalQ.length;

  */
        index = new ArrayList<Integer>(length);

        for(i = 0; i<length+1; i++){

            index.add(i);
        }
        checkBoxState = new boolean[solarSystem.length+1];
        Animation animFadein = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fade_in);
        int favorite_index = QuoteDetailActivity.index_first;
        //String reserved = Html.fromHtml(ItemListFragment.solarSystem[favorite_index]).toString();
        //Log.d("Category's ", ""+length);
        //title.setText(ItemListFragment.solarSystem[favorite_index]);
        Log.d("Favorite_index", ""+favorite_index);
        //String [] imported = ItemListActivity.export_solarSystem.toArray(new String[ItemListFragment.export_solarSystem.size()]);
        final String test = ItemListActivity.export_solarSystem.get(favorite_index);
        title.setText(Html.fromHtml(test));
        showIndex.setText("1" + "/" + length);
        Collections.shuffle(index);
        favorites = sharedPreference.getFavorites(getActivity().getApplicationContext());
        Log.d("Favorites size", ""+favorites.size());
    /*
        if (favorites != null || favorites.size() == 0) {
            for(int m = 0; m < length; m++ ) {
                for (int j = 0; j < favorites.size(); j++) {
                    if (solarSystem[m].equals(favorites.get(j))) {
                        sharedFav.
                    }
                }
            }
        }
        */
            i = 1;

//checkBox.setChecked(checkBoxState[i]);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()){
                    //Log.d("Favorite ", ""+ solarSystem[index.get(i)]);
                    //Log.d("Index is: i: ", ""+index.get(i));

                    Toast.makeText(getActivity().getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                    checkBoxState[i]=true;
                    if(i == 1){
                        sharedPreference.addFavorite(getActivity().getApplicationContext(), test);
                        //Log.d("Toast ", "Excuted"+test);
                    }
                    else{
                        sharedPreference.addFavorite(getActivity().getApplicationContext(), solarSystem[index.get(i)]);
                        //Log.d("Toast ", "Excuted"+solarSystem[index.get(i)]);
                    }
                }
                else if(!((CheckBox)view).isChecked()){
                    Toast.makeText(getActivity().getApplicationContext(), "Removed from Favorite", Toast.LENGTH_SHORT).show();
                    Log.d("Unchecked ", "excuted");
                    Log.d("Favorite size", ""+favorites.size());
                    sharedPreference.removeFavorite(getActivity(), solarSystem[one_index]);
                    //favorites.remove(removedFavoriteIndex);
                    checkBoxState[i]=false;
                    Log.d("Favorites size", ""+favorites.size());
                }
            }
        });

        //myLooper();
        /*
        Log.d("Size of All Quotes: ", "" + total);
        Log.d("Size of Quotes: ", ""+length);
        Log.d("Favorite Index: ", ""+favorite_index);
        */

        //backgroundChanger();
        title.startAnimation(animFadein);
        toBeSent = title;


        slideRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);

        slideLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);

        v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeLeft() {

                title.startAnimation(slideRight);
                checkBox.startAnimation(slideRight);
                myLooper();

                checkBox.setChecked(checkBoxState[i]);



            }
            public void onSwipeRight(){
                title.startAnimation(slideLeft);
                checkBox.startAnimation(slideLeft);
                reverseLooper();
                checkBox.setChecked(checkBoxState[i]);
            }
        });

        //title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        //message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));

        // Inflate the layout for this fragment
        return v;
    }

    private void myLooper(){

        if(i <= length-1){

            i = i +1;
            if(i == length) i = length-1;
            //title.setText(solarSystem[i]);
            one_index = index.get(i);

            for(int j = 0; j< favorites.size(); j++){
                if(solarSystem[one_index].equals(favorites.get(j))){
                    Toast.makeText(getActivity(), "Found equal", Toast.LENGTH_SHORT);
                    Log.d("Favorite to right ", "found"+solarSystem[one_index]);
                    Log.d("Index i is: ",""+i);
                    checkBoxState[i] = true;
                    removedFavoriteIndex = j;
                    Log.d("Removed ", ""+favorites.size());

                    Log.d("Removed favorite Index", ""+removedFavoriteIndex);

                }
            }
            Log.d("MyLooperFavoriteSize ", ""+favorites.size());
            title.setText(Html.fromHtml(solarSystem[one_index]));
            showIndex.setText(""+i+"/"+length);

        }
        else{
            i = 1;
            myLooper();

        }
    }
    private void categorySetter(){
        if(category.equals("Inspirational")){
            solarSystem = getResources().getStringArray(R.array.inspirationl);
            v.setBackground(getResources().getDrawable(R.drawable.luis));
            title.setWidth(430);


        }
        else if(category.equals("Determination")){
            v.setBackground(null);
            //v.setBackground(getResources().getDrawable(R.drawable.vivid));
            title.setTextColor(getResources().getColor(R.color.white));
            solarSystem= getResources().getStringArray(R.array.determination);
        }
        else if(category.equals("Motivational")){
            v.setBackground(getResources().getDrawable(R.drawable.city));
            title.setTextColor(getResources().getColor(R.color.white));
            solarSystem = getResources().getStringArray(R.array.motivational);

        }
        else if(category.equals("Goals")){
            v.setBackground(getResources().getDrawable(R.drawable.river));
            solarSystem = getResources().getStringArray(R.array.goals);
        }
        else if(category.equals("Purpose")){
            v.setBackground(getResources().getDrawable(R.drawable.white_tree1));
            solarSystem = getResources().getStringArray(R.array.purpose);
        }

        else if(category.equals("Success")){
            v.setBackground(getResources().getDrawable(R.drawable.green));
            title.setTextColor(getResources().getColor(R.color.white));
            solarSystem = getResources().getStringArray(R.array.success);
        }
        else if(category.equals("Encouragement")){
            v.setBackground(getResources().getDrawable(R.drawable.shoes));
            title.setTextColor(getResources().getColor(R.color.white));
            solarSystem = getResources().getStringArray(R.array.encouragment);
        }

        else if(category.equals("Happiness")){
            v.setBackground(getResources().getDrawable(R.drawable.sunrise));
            title.setTextColor(getResources().getColor(R.color.white));
            solarSystem = getResources().getStringArray(R.array.happiness);
        }

        else if(category.equals("Passion")){
            solarSystem = getResources().getStringArray(R.array.passion);
            v.setBackground(getResources().getDrawable(R.drawable.simon));
        }
        else if(category.equals("Affirmation")){
            solarSystem = getResources().getStringArray(R.array.affirmation);
            v.setBackground(getResources().getDrawable(R.drawable.book));
            title.setTextColor(getResources().getColor(R.color.white));
        }
        else if(category.equals("Zeal")){
            solarSystem = getResources().getStringArray(R.array.zeal);
            v.setBackground(getResources().getDrawable(R.drawable.book));
            title.setTextColor(getResources().getColor(R.color.white));
        }
        else if(category.equals("Humourous")){
            solarSystem = getResources().getStringArray(R.array.humour);
            v.setBackground(getResources().getDrawable(R.drawable.clock));
        }

        else {
            solarSystem = getResources().getStringArray(R.array.inspirationl);
            v.setBackground(getResources().getDrawable(R.drawable.flower));
        }

    }

    private void reverseLooper(){
        if(i<=length-1 && i>0){
            i-=1;
            one_index = index.get(i);
            for(int j = 0; j< favorites.size(); j++){
                if(solarSystem[one_index].equals(favorites.get(j))){
                    Toast.makeText(getActivity(), "Found equal", Toast.LENGTH_SHORT);
                    Log.d("Favorite ", "found"+solarSystem[one_index]);
                    Log.d("Index i is: ",""+i);
                    if(!checkBoxState[i]){

                        checkBoxState[i] = true;
                    }

                }
            }
            title.setText(Html.fromHtml(solarSystem[one_index]));
            showIndex.setText(""+i+"/"+length);
        }
        else{
            i = 0;
            one_index = index.get(i);
            title.setText(Html.fromHtml(solarSystem[one_index]));
            showIndex.setText(""+i+"/"+length);
        }
    }

}



