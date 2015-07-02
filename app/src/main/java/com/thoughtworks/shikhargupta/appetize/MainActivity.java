package com.thoughtworks.shikhargupta.appetize;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    int[] images = {
            R.drawable.rsz_indian_1_compressed,
            R.drawable.rsz_chilly_paneer_compressed,
            R.drawable.rsz_continental_compressed,
            R.drawable.rsz_dessert_compressed,
            R.drawable.rsz_italian_compressed
    };

    String[] cuisineStrings;

    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        cuisineStrings = res.getStringArray(R.array.Cuisines);

        listview = (ListView) findViewById(R.id.listView);
        CuisineAdapter cuisineAdapter = new CuisineAdapter(this, cuisineStrings, images);
        listview.setAdapter(cuisineAdapter );
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("categoryIndex", position);
        startActivity(intent);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}

class CuisineAdapter extends ArrayAdapter<String>{


    Context context;
    int[] images;
    String[] cuisineName;
    public CuisineAdapter(Context context, String[] cuisineName, int[] images) {
        super(context, R.layout.list_item, R.id.listText, cuisineName);
        this.context = context;
        this.images = images;
        this.cuisineName = cuisineName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_item, parent, false);
        ImageView listImage = (ImageView) row.findViewById(R.id.listImage);
        TextView listText = (TextView) row.findViewById(R.id.listText);
        listImage.setImageResource(images[position]);
        listText.setText(cuisineName[position]);
        return row;
    }
}