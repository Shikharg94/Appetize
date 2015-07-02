package com.thoughtworks.shikhargupta.appetize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class RecipeActivity extends ActionBarActivity implements RecipeFragment.Communicator{

    int categoryIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        categoryIndex = intent.getIntExtra("categoryIndex", 0);
    }

    @Override
    public int respond() {
        return categoryIndex;
    }
}
