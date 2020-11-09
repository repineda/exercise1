package com.mcs.androidactivity1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import java.time.Year;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {
    Button pickDate;
    TextView age;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    ImageButton saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pickDate = findViewById(R.id.btn_birthday);
        age = findViewById(R.id.tv_age_profile);
        pickDate.setBackgroundColor(Color.WHITE);
        saveBtn = findViewById(R.id.btn_save);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        calendar = Calendar.getInstance();
                        int currYear = calendar.get(Calendar.YEAR);
                        int currDay = calendar.get(Calendar.DAY_OF_MONTH);
                        int currMonth = calendar.get(Calendar.MONTH);
                        int temp = currYear - year;
                        if(currMonth >= month && currDay >= dayOfMonth)
                        {
                            //do nothing
                        }
                        else
                        {
                            temp--;
                        }
                        age.setText(Integer.toString(temp));

                    }
                },1990, 0,0);
                datePickerDialog.show();
            }
        });
        CountryCurrencyButton button = (CountryCurrencyButton) findViewById(R.id.btn_country);
        button.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(ProfileActivity.this,
                            String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this,
                            String.format("name: %s\ncurrencySymbol: %s", country.getName(), country.getCurrency().getSymbol())
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });
    }

}