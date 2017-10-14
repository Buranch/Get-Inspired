package com.example.de.new210;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ItemListActivity extends ActionBarActivity {

    private static String[] motivationalQ;
    private static String[] goalsQ;
    ArrayList<String> favorites;
    private static String[] zealQ;
    SharedPreference sharedPreference;

    Activity activity;
    private static String[] passionQ;
    private static String title;
    private static String[] affirmationQ;
    private static String[] purposeQ;
    private static String[] successQ;
    private static String[] determinationQ;

    public static ArrayList<String> Favorite;
    private static String[] happinessQ;

    public static final String ARG_ITEM_ID = "favorite_list";

    private static String[] humourousQ;
    private static String[] inspirationalQ;
    private String category;
    private static String[] encouragementQ;
    public static ArrayList<String> export_solarSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Intent intent = getIntent();
        category = "Love";
        activity = getParent();
        sharedPreference = new SharedPreference();

        if(intent != null){
            title = intent.getStringExtra(MainActivity.AUTHOR_NAME);
            setTitle(title);
            category = title;
        }
        else{
            title = "Love";
            setTitle(title);
            category = title;
        }

        //createAndAddFragment();
        ListView listView = (ListView) findViewById(R.id.my_listView_item);

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
        Favorite = new ArrayList<>();
        ArrayList<String> postArrayList = new ArrayList<String>(Arrays.asList(categorySetter()));
        Collections.shuffle(postArrayList);
        export_solarSystem = postArrayList;
        MyAppAdapter myAppAdapter = new MyAppAdapter(postArrayList, ItemListActivity.this);
        listView.setAdapter(myAppAdapter);

        listView.setDividerHeight(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_view);

                view.setAnimation(fadeOut);

                Intent intent = new Intent(getApplicationContext(), QuoteDetailActivity.class);
                intent.putExtra(MainActivity.AUTHOR_NAME, title);
                intent.putExtra(MainActivity.QUOTE_INDEX, position);

                //view.setAnimation(fadeOut);

                startActivity(intent);
            }
        });
    }




    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            TextView txtTitle;
            CheckBox checkBox;

        }
        public boolean[] checkBoxState;

        public List<String> parkingList;


        public Context context;
        ArrayList<String> arraylist;


        public MyAppAdapter(List<String> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<String>();
            arraylist.addAll(parkingList);
            checkBoxState = new boolean[parkingList.size()];
            favorites = sharedPreference.getFavorites(getApplicationContext());
            if(favorites != null || favorites.size() == 0){
                for(int i = 0; i < parkingList.size(); i++ ){

                    for(int j = 0; j< favorites.size(); j++){
                        if(parkingList.get(i).equals(favorites.get(j))){
                            checkBoxState[i] = true;
                        }
                    }
                    /*
                    if(checkBoxState[i] == false){
                        if(favorites.contains(parkingList.get(i))){
                            sharedPreference.removeFavorite(getApplicationContext(), parkingList.get(i));

                        }
                    }
             */
                }

            }


        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            ViewHolder viewHolder;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_row2, null);

                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.txtTitle = (TextView) rowView.findViewById(R.id.text);
                viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
                viewHolder.checkBox.setChecked(checkBoxState[position]);
                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(((CheckBox)view).isChecked()){

                            Log.d("Favorite ", ""+parkingList.get(position));
                            Toast.makeText(getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                            checkBoxState[position]=true;
                            sharedPreference.addFavorite(getApplicationContext(), parkingList.get(position));
                            //
                            //xy8
                        }
                        else
                            checkBoxState[position]=false;
                            }
                });

                rowView.setTag(viewHolder);



            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                viewHolder.checkBox.setChecked(checkBoxState[position]);
                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(((CheckBox)view).isChecked()){
                            Log.d("Favorite ", ""+parkingList.get(position));
                            Toast.makeText(getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                            checkBoxState[position]=true;
                            sharedPreference.addFavorite(getApplicationContext(), parkingList.get(position));
                        }
                        else
                            checkBoxState[position]=false;
                    }
                });
            }
            viewHolder.txtTitle.setText(Html.fromHtml(parkingList.get(position)));
            return rowView;
        }
    }
    private String [] categorySetter(){
        if(category.equals("Inspirational")){
            return  inspirationalQ;

        }
        else if(category.equals("Determination")){
            return determinationQ;
        }
        else if(category.equals("Motivational")){
            return motivationalQ;

        }
        else if(category.equals("Goals")){

            return goalsQ;
        }
        else if(category.equals("Purpose")){
            return purposeQ;
        }
        else if(category.equals("Warren Buffet")){
            return getResources().getStringArray(R.array.warrenBuffet);
        }

        else if(category.equals("Success")){
            return successQ;
        }
        else if(category.equals("Encouragement")){
            return happinessQ;
        }
        else if(category.equals("William Shakspeare")){
            return getResources().getStringArray(R.array.william_shakespeare);
        }
        else if(category.equals("Happiness")){
            return encouragementQ;
        }
        else if(category.equals("Passion")){
            return passionQ;
        }
        else if(category.equals("Zeal")){
            return zealQ;
        }
        else if(category.equals("Humourous")){
            return humourousQ;
        }
        else if(category.equals("Affirmation")){
            return affirmationQ;
        }
        else {
            return inspirationalQ;
        }

    }
    @Override
    public void onPause(){
        super.onPause();

        category = "Love";

    }
    @Override
    public void onResume(){
        super.onResume();
        setTitle(title);
        category = "Love";
    }

}
