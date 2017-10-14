package com.example.de.new210;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SharedPreference {

	public static final String PREFS_NAME = "PRODUCT_APP";
	public static final String FAVORITES = "Product_Favorite";
	public SharedPreference() {
		super();
	}

	// This four methods are used for maintaining favorites.
	public void saveFavorites(Context context, List<String> favorites) {
		SharedPreferences settings;
		Editor editor;

		settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		editor = settings.edit();

		Gson gson = new Gson();
		String jsonFavorites = gson.toJson(favorites);

		editor.putString(FAVORITES, jsonFavorites);

		editor.commit();
	}
	public static void clearFavorite(Context context){
		SharedPreferences settings;
		settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		settings.edit().clear().commit();

	}

	public void addFavorite(Context context, String string) {
		List<String> favorites = getFavorites(context);
		if (favorites == null)
			favorites = new ArrayList<String>();
		favorites.add(string);
		saveFavorites(context, favorites);
	}
	public void removeFavorite(Context context, String string) {
		ArrayList<String> favorites = getFavorites(context);
		if (favorites != null) {
			favorites.remove(string);
			saveFavorites(context, favorites);
		}
	}

	public ArrayList<String> getFavorites(Context context) {
		SharedPreferences settings;
		List<String> favorites;
		ArrayList<String> empty;
		empty = new ArrayList<String>();

		settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);

		if (settings.contains(FAVORITES)) {
			String jsonFavorites = settings.getString(FAVORITES, null);
			Gson gson = new Gson();
			String[] favoriteItems = gson.fromJson(jsonFavorites, String[].class);

			favorites = Arrays.asList(favoriteItems);
			favorites = new ArrayList<String>(favorites);
		} else

			return empty;

		return (ArrayList<String>) favorites;
	}
}
