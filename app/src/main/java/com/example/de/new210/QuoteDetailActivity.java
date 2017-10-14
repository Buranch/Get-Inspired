package com.example.de.new210;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class QuoteDetailActivity extends ActionBarActivity {
    private String author;
    public static int index_first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_detail);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.show();

        addFragments();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == R.id.add_note){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            final String sendQuote = AuthorsActivity.toBeSent.getText().toString();
            intent.putExtra(Intent.EXTRA_TEXT, sendQuote);
            startActivity(intent);
            return true;
        }

        else if(id == R.id.setting){
            return true;

        }
        return super.onOptionsItemSelected(menuItem);
    }
    private void addFragments(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Intent intent = getIntent();
        index_first =intent.getIntExtra(MainActivity.QUOTE_INDEX, 0);
        author = intent.getStringExtra(MainActivity.AUTHOR_NAME);
        AuthorsActivity authorsActivity = new AuthorsActivity();
        setTitle(author);

        fragmentTransaction.add(R.id.note_container_quote, authorsActivity, "NOTE_VIEW_FRAGMENT");
        fragmentTransaction.commit();

    }

}
