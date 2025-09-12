package com.example.countryapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imgFlag;
    private TextView tvName, tvCapital, tvDescription;
    private Button btnEdit, btnDelete;

    private CountryViewModel myViewModel;
    private CountryEntity countryEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imgFlag = findViewById(R.id.imgFlag);
        tvName = findViewById(R.id.tvName);
        tvCapital = findViewById(R.id.tvCapital);
        tvDescription = findViewById(R.id.tvDescription);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        myViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        int countryId = getIntent().getIntExtra("countryId", -1);
        if (countryId != -1) {
            myViewModel.getCountryById(countryId).observe(this, country -> {
                if (country != null) {
                    countryEntity = country;
                    displayCountryDetails(country);
                }
            });
        }

        btnEdit.setOnClickListener(v -> showEditDialog());
        btnDelete.setOnClickListener(v -> showDeleteDialog());
    }

    private void displayCountryDetails(CountryEntity country) {
        imgFlag.setImageResource(country.getFlagResId());
        tvName.setText(country.getName());
        tvCapital.setText(country.getCapital());
        tvDescription.setText(country.getDescription());
    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Country");

        EditText editName = new EditText(this);
        editName.setHint("Name");
        editName.setText(countryEntity.getName());

        EditText editCapital = new EditText(this);
        editCapital.setHint("Capital");
        editCapital.setText(countryEntity.getCapital());

        EditText editDescription = new EditText(this);
        editDescription.setHint("Description");
        editDescription.setText(countryEntity.getDescription());

        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.addView(editName);
        layout.addView(editCapital);
        layout.addView(editDescription);
        builder.setView(layout);

        builder.setPositiveButton("Save", (dialog, which) -> {
            countryEntity.setName(editName.getText().toString());
            countryEntity.setCapital(editCapital.getText().toString());
            countryEntity.setDescription(editDescription.getText().toString());
            myViewModel.update(countryEntity);
            displayCountryDetails(countryEntity);
            Toast.makeText(this, "Country updated", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Country");
        builder.setMessage("Are you sure you want to delete this country?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            myViewModel.delete(countryEntity);
            Toast.makeText(this, "Country deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    }
