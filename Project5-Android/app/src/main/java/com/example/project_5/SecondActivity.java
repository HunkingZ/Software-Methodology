package com.example.project_5;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private double ticketPrice = 0;
    private double salesTax = 0;
    private double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String museum = intent.getStringExtra(MainActivity.museumName);

        TextView textView = findViewById(R.id.Museum_Title);
        TextView adultPrice = findViewById(R.id.adult_price);
        TextView seniorPrice = findViewById(R.id.senior_price);
        TextView studentPrice = findViewById(R.id.student_price);
        Spinner adultSpinner = findViewById(R.id.adult_spinner);
        Spinner seniorSpinner = findViewById(R.id.senior_spinner);
        Spinner studentSpinner = findViewById(R.id.student_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.TicketNumbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adultSpinner.setAdapter(adapter);
        seniorSpinner.setAdapter(adapter);
        studentSpinner.setAdapter(adapter);
        adultSpinner.setOnItemSelectedListener(this);
        seniorSpinner.setOnItemSelectedListener(this);
        studentSpinner.setOnItemSelectedListener(this);
        textView.setText(museum);

        ImageView imageView = findViewById(R.id.museum_image);
        switch (museum) {
            case "The Met Breuer": {
                imageView.setImageResource(R.drawable.museum_met_breuer);
                adultPrice.setText("Adult $25");
                seniorPrice.setText("Senior $17");
                studentPrice.setText("Student $12");
                break;
            }
            case "New-York Historical Society": {
                imageView.setImageResource(R.drawable.museum_ny_historical_society);
                adultPrice.setText("Adult $22");
                seniorPrice.setText("Senior $17");
                studentPrice.setText("Student $13");
                break;
            }
            case "Solomon R. Guggenheim Museum": {
                adultPrice.setText("Adult $25");
                seniorPrice.setText("Senior $18");
                studentPrice.setText("Student $18");
                imageView.setImageResource(R.drawable.museum_solomon_r_guggenheim);
                break;
            }
            case "Museum of Modern Art (MoMA)": {
                adultPrice.setText("Adult $25");
                seniorPrice.setText("Senior $18");
                studentPrice.setText("Student $14");
                imageView.setImageResource(R.drawable.museum_moma);
                break;
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String num = parent.getItemAtPosition(position).toString();

        //Toast.makeText(parent.getContext(), num, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}