package com.example.project_5;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URL;
import java.util.ArrayList;

/**
 * Secondary activity screen that shows information about a museum
 * Information shown is determined by selection of primary activity screen
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class SecondActivity extends AppCompatActivity {

    private String linkToWeb;
    private final double NYC_RATE = 0.08875;
    private final double NJ_RATE = 0.06625;
    private double taxRate;

    private double ticketPrice;
    private double salesTax;
    private double totalPrice;

    private int adultPrice;
    private int seniorPrice;
    private int studentPrice;

    private int adultNum = 0;
    private int seniorNum = 0;
    private int studentNum = 0;

    TextView totalPriceText, salesTaxText, ticketPriceText;
    private String number[] = {"0", "1", "2", "3", "4", "5"};

    /**
     * Creates and displays museum information
     * Parses data based on selection in primary activity
     *
     * @param savedInstanceState Information passed by calling (primary) activity
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String museum = intent.getStringExtra(MainActivity.museumName);

        totalPriceText = findViewById(R.id.totalPriceText);
        salesTaxText = findViewById(R.id.salesTaxText);
        ticketPriceText = findViewById(R.id.ticketPriceText);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        TextView textView = findViewById(R.id.Museum_Title);
        TextView adult = findViewById(R.id.adult_price);
        TextView senior = findViewById(R.id.senior_price);
        TextView student = findViewById(R.id.student_price);
        Spinner adultSpinner = findViewById(R.id.adult_spinner);
        Spinner seniorSpinner = findViewById(R.id.senior_spinner);
        Spinner studentSpinner = findViewById(R.id.student_spinner);

        textView.setText(museum);

        ImageView imageView = findViewById(R.id.museum_image);

        switch (museum) {
            case "The Met Breuer": {
                imageView.setImageResource(R.drawable.museum_met_breuer);
                linkToWeb = "https://www.metmuseum.org/visit/audio-guide/the-met-breuer";

                taxRate = NYC_RATE;
                adultPrice = 25;
                seniorPrice = 17;
                studentPrice = 12;

                break;
            }
            case "New-York Historical Society": {
                imageView.setImageResource(R.drawable.museum_ny_historical_society);
                linkToWeb = "https://www.nyhistory.org/";

                taxRate = NYC_RATE;
                adultPrice = 22;
                seniorPrice = 17;
                studentPrice = 13;

                break;
            }
            case "Solomon R. Guggenheim Museum": {
                imageView.setImageResource(R.drawable.museum_solomon_r_guggenheim);
                linkToWeb = "https://www.guggenheim.org/";

                taxRate = NYC_RATE;
                adultPrice = 25;
                seniorPrice = 18;
                studentPrice = 18;

                break;
            }
            case "Museum of Modern Art (MoMA)": {
                imageView.setImageResource(R.drawable.museum_moma);
                linkToWeb = "https://www.moma.org/";

                taxRate = NYC_RATE;
                adultPrice = 25;
                seniorPrice = 18;
                studentPrice = 14;

                break;
            }
        }

        adult.setText("Adult $" + adultPrice);
        senior.setText("Senior $" + seniorPrice);
        student.setText("Student $" + studentPrice);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(linkToWeb);
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        adultSpinner.setAdapter(arrayAdapter);
        seniorSpinner.setAdapter(arrayAdapter);
        studentSpinner.setAdapter(arrayAdapter);

        adultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adultNum = Integer.parseInt(parent.getItemAtPosition(position).toString());
                setPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seniorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seniorNum = Integer.parseInt(parent.getItemAtPosition(position).toString());
                setPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        studentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentNum = Integer.parseInt(parent.getItemAtPosition(position).toString());
                setPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Calculates, sets, and displays ticket price, sales tax, and total price
     */
    private void setPrice() {
        ticketPrice = adultNum * adultPrice + seniorNum * seniorPrice + studentNum * studentPrice;
        salesTax = ticketPrice * taxRate;
        totalPrice =  ticketPrice + salesTax;

        String ticket = String.format("Ticket Price: $%.0f", ticketPrice);
        String tax = String.format("Sales Tax: $%.2f", salesTax);
        String total = String.format("Ticket Total: $%.2f", totalPrice);

        ticketPriceText.setText(ticket);
        salesTaxText.setText(tax);
        totalPriceText.setText(total);
    }

    /**
     * Opens the museum URL
     * Submits intent to the system and opens the website link
     *
     * @param url Link of the museum website
     */
    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}