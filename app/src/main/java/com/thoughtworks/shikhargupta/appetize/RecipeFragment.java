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
    String[][] recipes = {
            {"Kadhai Paneer", "Dal Chawal", "Dal Makhni"},
            {"Noodles", "Manchurian", "Chilly Paneer", "Soy Paneer"},
            {"Red Sauce Pasta", "White Sauce Pasta"},
            {"Brownie", "Waffles", "Custard"},
            {"Pizza"}
    };
    int[][] recipeImages = {
            {R.drawable.}
    }

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
//      Log.d("chi6rag", recipiesList[0]);

        return view;
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

    Context context;
    int[] images;
    String[] recipeNames;
    ArrayList<Recipe> recipeArrayList;

    RecipeAdapter(Context context, int[] images, String[] recipeNames){
        this.context = context;
        this.recipeNames = recipeNames;
        this.images = images;
        for(int i = 0; i < recipeNames.length; i++){
            recipeArrayList.add(i, new Recipe(images[i], recipeNames[i]) );
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
        holder.recipeImage.setImageResource();
        return null;
    }
}
