package com.thoughtworks.shikhargupta.appetize;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shikhargupta on 02/07/15.
 */
public class RecipeFragment extends Fragment{


    Communicator communicator;
    GridView recipeGrid;
    int categoryIndex = 0;
//    String[][] recipes = {
//            {"Kadhai Paneer", "Dosa", "Dal Makhni"},
//            {"Noodles", "Manchurian", "Chilly Paneer", "Chinese Sizzler"},
//            {"Red Sauce Pasta", "White Sauce Pasta"},
//            {"Brownie", "Waffles", "Custard"},
//            {"Pizza"}
//    };
//
//    int[][] recipeImages = {
//            {R.drawable.rsz_kadhai_paneer, R.drawable.rsz_dosa, R.drawable.rsz_dal_makhani},
//            {R.drawable.rsz_noodles, R.drawable.rsz_manchurian_new,
//                    R.drawable.chilly_paneer_2_compressed, R.drawable.rsz_sizzler},
//            {R.drawable.rsz_1continental_red_sauce_pasta, R.drawable.rsz_continental_white_sauce},
//            {R.drawable.rsz_1desserts_brownie, R.drawable.rsz_desserts_waffles,
//                    R.drawable.rsz_desserts_custard},
//            {R.drawable.rsz_italian_pizza}
//    };

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
        categoryIndex = communicator.respond();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecipeAdapter recipeAdapter = new RecipeAdapter(getActivity(), categoryIndex);
        recipeGrid.setAdapter(recipeAdapter);
    }

    public interface Communicator {
        public int respond();
    }
}

class Recipe {
    int image;
    String recipeName;

    Recipe(int image, String recipeName){
        this.image = image;
        this.recipeName = recipeName;
    }
}

class RecipeAdapter extends BaseAdapter {

    String[][] recipes = {
            {"Kadhai Paneer", "Dosa", "Dal Makhni"},
            {"Noodles", "Manchurian", "Chilly Paneer", "Chinese Sizzler"},
            {"Red Sauce Pasta", "White Sauce Pasta"},
            {"Brownie", "Waffles", "Custard"},
            {"Pizza"}
    };

    int[][] recipeImages = {
            {R.drawable.rsz_kadhai_paneer, R.drawable.rsz_dosa, R.drawable.rsz_dal_makhani},
            {R.drawable.rsz_noodles, R.drawable.rsz_manchurian_new,
                    R.drawable.chilly_paneer_2_compressed, R.drawable.rsz_sizzler},
            {R.drawable.rsz_1continental_red_sauce_pasta, R.drawable.rsz_continental_white_sauce},
            {R.drawable.rsz_1desserts_brownie, R.drawable.rsz_desserts_waffles,
                    R.drawable.rsz_desserts_custard},
            {R.drawable.rsz_italian_pizza}
    };

    Context context;
    ArrayList<Recipe> recipeArrayList;
    int categoryPosition;

    RecipeAdapter(Context context, int categoryPosition){
        this.context = context;
        this.categoryPosition = categoryPosition;
        this.recipeArrayList = new ArrayList<Recipe>();
        // Add all recipes to the corresponding category
        for(int i = 0; i < recipes[categoryPosition].length; i++){
            recipeArrayList.add( new Recipe(recipeImages[categoryPosition][i],
                    recipes[categoryPosition][i]) );
        }
    }

    @Override
    public int getCount() {
        return recipeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView recipeImage;
        TextView recipeName;

        ViewHolder(View v){
            recipeImage = (ImageView) v.findViewById(R.id.recipeImage);
            recipeName  =  (TextView) v.findViewById(R.id.recipeName);
        }

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View recipeItem  = convertView;
        ViewHolder holder = null;
        if(recipeItem==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            recipeItem = inflater.inflate(R.layout.single_recipe, parent, false);
            holder = new ViewHolder(recipeItem);
            recipeItem.setTag(holder);
        }
        else{
            holder = (ViewHolder) recipeItem.getTag();
        }
        holder.recipeImage.setImageResource(recipeImages[categoryPosition][position]);
        holder.recipeName.setText(recipes[categoryPosition][position]);
        return recipeItem;
    }
}
