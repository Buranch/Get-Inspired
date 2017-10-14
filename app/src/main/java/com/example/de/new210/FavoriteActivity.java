package com.example.de.new210;

import android.content.Context;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.BaseAdapter;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


import java.util.List;

/**
 * Created by DE on 8/29/2016.
 */
public class FavoriteActivity extends ActionBarActivity {
    public static final String ARG_ITEM_ID = "favorite_list";

    SharedPreference sharedPreference;
    ArrayList<String> favorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(getApplicationContext());

        if (favorites.isEmpty() || favorites.size() == 0) {
            setContentView(R.layout.no_favorite);
        } else {

            setContentView(R.layout.favorite_list);
            ListView listView = (ListView) findViewById(R.id.my_listView_item_favorite);

            MyAppAdapter myAppAdapter = new MyAppAdapter(favorites, FavoriteActivity.this);
            listView.setAdapter(myAppAdapter);
            listView.setDividerHeight(0);


        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == R.id.clear_all){
            setContentView(R.layout.no_favorite);
            SharedPreference.clearFavorite(getApplicationContext());
           // getApplicationContext().getSharedPreferences(ARG_ITEM_ID, 0).edit().clear().apply();
        }

        return super.onOptionsItemSelected(menuItem);
    }

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            TextView txtTitle;
        }
        public List<String> parkingList;
        public Context context;
        ArrayList<String> arraylist;
        public MyAppAdapter(List<String> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<String>();
            arraylist.addAll(parkingList);
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
                rowView = inflater.inflate(R.layout.list_row_favorite, null);

                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.txtTitle = (TextView) rowView.findViewById(R.id.text_favorite);

                rowView.setTag(viewHolder);



            } else {
                viewHolder = (ViewHolder) convertView.getTag();

            }

            viewHolder.txtTitle.setText(Html.fromHtml(parkingList.get(position)));
            return rowView;


        }


    }

}



