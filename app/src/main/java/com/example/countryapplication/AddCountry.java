package com.example.countryapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddCountry extends AppCompatActivity {
    private EditText etName, etCapital, etDescription;
    private Spinner spinnerFlag;
    private Button btnSave;
    private CountryViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);
        etName = findViewById(R.id.etName);
        etCapital = findViewById(R.id.etCapital);
        etDescription = findViewById(R.id.etDescription);
        spinnerFlag = findViewById(R.id.spinnerFlag);
        btnSave = findViewById(R.id.btnSave);

        myViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        String[] flagNames = {"USA", "UK", "France"};
        int[] flagResIds = {R.drawable.flag, R.drawable.eygept, R.drawable.pal};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, flagNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFlag.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String capital = etCapital.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            int flagPos = spinnerFlag.getSelectedItemPosition();

            if(name.isEmpty() || capital.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int flagResId = flagResIds[flagPos];
            CountryEntity country = new CountryEntity(name, capital, description, flagResId);
            myViewModel.insert(country);
            Toast.makeText(this, "Country added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}