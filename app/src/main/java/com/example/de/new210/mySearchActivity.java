package com.example.de.new210;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.Locale;

/**
 * Created by DE on 8/10/2016.
 */
public class mySearchActivity extends ActionBarActivity {

    private ListView listView;
    ArrayList<String> favorites;
    private MyAppAdapter myAppAdapter;
    private ArrayList<String> postArrayList;

    public static String [] biggerDb;
    SharedPreference sharedPreference;
    public mySearchActivity (){

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_search);
        listView= (ListView) findViewById(R.id.my_listView);
        //String [] myString = new String[]{"Hey Biruk","Hey Henok", "Hey Hezkiel", "Hey Tolle","Hey Biruk","Hey Henok", "Hey Hezkiel", "Hey Tolle","Hey Biruk","Hey Henok", "Hey Hezkiel", "Hey Tolle"};
        biggerDb = concateAll(getResources().getStringArray(R.array.zeal), getResources().getStringArray(R.array.humour) ,getResources().getStringArray(R.array.inspirationl),getResources().getStringArray(R.array.affirmation),getResources().getStringArray(R.array.passion),getResources().getStringArray(R.array.encouragment),getResources().getStringArray(R.array.happiness),getResources().getStringArray(R.array.motivational), getResources().getStringArray(R.array.determination), getResources().getStringArray(R.array.goals), getResources().getStringArray(R.array.purpose), getResources().getStringArray(R.array.success));
        Collections.shuffle(Arrays.asList(biggerDb));
        sharedPreference = new SharedPreference();
        postArrayList = new ArrayList<String>(Arrays.asList(biggerDb));
        Collections.shuffle(postArrayList);
        myAppAdapter=new MyAppAdapter(postArrayList, mySearchActivity.this);
        listView.setAdapter(myAppAdapter);

        //animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        //fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_search);
        //listView.setAnimation(animFadein);
        listView.setDividerHeight(0);
        myAppAdapter.getArraylist();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id) {

                //view.setAnimation(fadeOut);
                Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_view);
                view.setAnimation(fadeOut);

                Intent intent = new Intent(getApplicationContext(), SearchAuthorsActivity.class);
                List<String> arrayList;
                arrayList = myAppAdapter.getArraylist();
                String test = arrayList.get(position);
                intent.putExtra(MainActivity.SEARCH_STRING, test);

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

        public List<String> getArraylist() {
            return parkingList;
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
                viewHolder.txtTitle.setBackground(getResources().getDrawable(R.drawable.inspired_red));
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
                            checkBoxState[position]=true;
                            Toast.makeText(getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();

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

        public void filter(String charText) {

            charText = charText.toLowerCase(Locale.getDefault());
            parkingList.clear();

            if (charText.length() == 0) {
                parkingList.addAll(arraylist);

            } else {
                for (String postDetail : arraylist) {
                    if (charText.length() != 0 && postDetail.toLowerCase().contains(charText)) {
                        parkingList.add(postDetail);
                    }

                    else if (charText.length() != 0 && postDetail.toLowerCase().contains(charText)) {
                        parkingList.add(postDetail);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                myAppAdapter.filter(searchQuery.trim());

                listView.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static <String> String[] concateAll(String[] first, String[]... rest){
        int totalLength = first.length;
        for(String[] array : rest){
            totalLength+= array.length;
        }
        String[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for(String[] array :rest){
            System.arraycopy(array,0, result, offset, array.length);
            offset+= array.length;

        }
        return result;


    }


}
