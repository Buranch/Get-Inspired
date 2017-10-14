package com.example.de.new210;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DE on 8/10/2016.
 */
public class ItemListFragment extends ListFragment {

    private static  String[] solarSystem;
    private static  LinkedList<String> export_solarSystem;

    private String category;
    private static String[] inspirationalQ;
    private static String[] motivationalQ;
    private static String[] goalsQ;
    private static String[] zealQ;
    private static String[] humourousQ;
    private static String[] passionQ;
    private static String[] affirmationQ;
    private static String[] purposeQ;
    private static String[] successQ;
    private static String[] determinationQ;
    private static String[] encouragementQ;
    private static String[] happinessQ;

    public static List<String> list;
    public ItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        humourousQ = getResources().getStringArray(R.array.humour);
        inspirationalQ = getResources().getStringArray(R.array.inspirationl);
        encouragementQ = getResources().getStringArray(R.array.encouragment);
        affirmationQ = getResources().getStringArray(R.array.affirmation);
        passionQ = getResources().getStringArray(R.array.passion);
        happinessQ = getResources().getStringArray(R.array.happiness);
        determinationQ = getResources().getStringArray(R.array.determination);
        motivationalQ = getResources().getStringArray(R.array.motivational);
        goalsQ = getResources().getStringArray(R.array.goals);
        purposeQ = getResources().getStringArray(R.array.purpose);
        successQ = getResources().getStringArray(R.array.success);
        zealQ = getResources().getStringArray(R.array.zeal);

        category = (String)getActivity().getTitle();


        categorySetter();

        //Search code
        /*
        String[] androidStrings = getResources().getStringArray(R.array.inspirationl);

        int j=1;
        for(String s:androidStrings){
            int i = s.indexOf("Napoleon Hill");

            if(i >= 0){
                j+=1;
            }

        }

        */
        //List<String> qouteList = Arrays.asList(category);
        //Collections.shuffle(qouteList);


        LinkedList <String> links =
                new LinkedList< String> ( Arrays.asList(solarSystem));
        Collections.shuffle(links);
        export_solarSystem = links;
        solarSystem = links.toArray(new String[links.size()]);


        for (int index = 0; index < solarSystem.length; index++){

            Spannable me = (Spannable) Html.fromHtml(solarSystem[index]);
            String you = me.toString();

            solarSystem[index] = you;

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.fragment_item, solarSystem);
        getListView().setDividerHeight(0);

        Animation animFadein = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fade_in);
        getListView().setAnimation(animFadein);


        setListAdapter(adapter);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_view);

        v.setAnimation(fadeOut);
        Intent intent = new Intent(getActivity(), QuoteDetailActivity.class);

        intent.putExtra(MainActivity.AUTHOR_NAME, getActivity().getTitle());
        intent.putExtra(MainActivity.QUOTE_INDEX, position);


        startActivity(intent);

    }
    private void categorySetter(){
        if(category.equals("Inspirational")){
            solarSystem = inspirationalQ;

        }
        else if(category.equals("Determination")){
            solarSystem= determinationQ;
        }
        else if(category.equals("Motivational")){
            solarSystem = motivationalQ;

        }
        else if(category.equals("Goals")){

            solarSystem = goalsQ;
        }
        else if(category.equals("Purpose")){
            solarSystem = purposeQ;
        }

        else if(category.equals("Success")){
            solarSystem = successQ;
        }
        else if(category.equals("Encouragement")){
            solarSystem = happinessQ;
        }
        else if(category.equals("William Shakspeare")){
            solarSystem = getResources().getStringArray(R.array.william_shakespeare);
        }
        else if(category.equals("Warren Buffet")){
            solarSystem =  getResources().getStringArray(R.array.warrenBuffet);
        }

        else if(category.equals("Happiness")){
            solarSystem = encouragementQ;
        }
        else if(category.equals("Passion")){
            solarSystem = passionQ;
        }
        else if(category.equals("Zeal")){
            solarSystem = zealQ;
        }
        else if(category.equals("Humourous")){
            solarSystem = humourousQ;
        }
        else if(category.equals("Affirmation")){
            solarSystem = affirmationQ;
        }
        else {
            solarSystem = inspirationalQ;
        }

    }

    }








