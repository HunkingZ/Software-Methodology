package com.example.project_5;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Primary activity screen that lists available museums to display
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class MainActivity extends AppCompatActivity {

    private ListView l1;
    private TextView textView;

    public static final String museumName = "com.example.project_5.museumName";

    /**
     * Creates the instance of the list of museums
     *
     * @param savedInstanceState Data (null) of current instance
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1 = (ListView)findViewById(R.id.l1);

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("The Met Breuer");
        arrayList.add("New-York Historical Society");
        arrayList.add("Solomon R. Guggenheim Museum");
        arrayList.add("Museum of Modern Art (MoMA)");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        l1.setAdapter(arrayAdapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSecondActivity(arrayList.get(position));
            }
        });
    }

    /**
     * Calls for instance to display selected museum information (second activity)
     *
     * @param name Name of the museum
     */
    public void openSecondActivity(String name) {
        Intent activity2 = new Intent(this, SecondActivity.class);
        activity2.putExtra(museumName, name);

        startActivity(activity2);
        Toast.makeText(MainActivity.this, "Maximum of 5 tickets for each!"
                , Toast.LENGTH_SHORT).show();
    }
}