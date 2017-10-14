package com.example.de.new210;

//import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String AUTHOR_NAME = "Author name";
    public static final String QUOTE_INDEX = "Quote index";
    public static final String SEARCH_STRING = "Search String";
    public static ArrayList<String> favorite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favorite  = new ArrayList<>();
        favorite.add("f");

        

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("content", ItemListActivity.ARG_ITEM_ID);
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == R.id.action_search_main){
            Intent intent = new Intent(getApplicationContext(),mySearchActivity.class);
            startActivity(intent);
            return true;
        }

        else if(id == R.id.setting){
            return true;

        }
        return super.onOptionsItemSelected(menuItem);
    }

}

