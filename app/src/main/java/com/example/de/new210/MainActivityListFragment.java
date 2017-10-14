package com.example.de.new210;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    public String[] list;
    private String[] list2;
    public MainActivityListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        list2 = getResources().getStringArray(R.array.contents);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_row, list2);
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
        Intent intent = new Intent(getActivity(), ItemListActivity.class);


        intent.putExtra(MainActivity.AUTHOR_NAME , list2[position]);

        startActivity(intent);

    }

}
