package com.example.countryapplication;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Country_tbl")
public class CountryEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String capital;
    private String description;
    private int flagResId;
    public CountryEntity(String name, String capital, String description, int flagResId) {
        this.name = name;
        this.capital = capital;
        this.description = description;
        this.flagResId = flagResId;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getCapital() { return capital; }
    public String getDescription() { return description; }
    public int getFlagResId() { return flagResId; }

    public void setFlagResId(int flagResId) {
        this.flagResId = flagResId;
    }

    public void setName(String name) { this.name = name; }
    public void setCapital(String capital) { this.capital = capital; }
    public void setDescription(String description) { this.description = description; }

}
