package com.thoughtworks.shikhargupta.appetize;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by shikhargupta on 02/07/15.
 */
public class RecipeFragment extends Fragment{

    Communicator communicator;
    GridView recipeGrid;
    String[][] recipes = {
            {"Kadhai Paneer", "Dosa", "Dal Makhni"},
            {"Noodles", "Manchurian", "Chilly Paneer", "Chinese Sizzler"},
            {"Red Sauce Pasta", "White Sauce Pasta"},
            {"Brownie", "Waffles", "Custard"},
            {"Pizza"}
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_fragment, container, false);

        recipeGrid = (GridView) view.findViewById(R.id.recipeGrid);
        int categoryIndex = communicator.respond();
        String[] recipiesList = recipes[categoryIndex];
        Log.d("chi6rag", recipiesList[0]);


        return view;
    }

    public interface Communicator {
        public int respond();
    }

}
