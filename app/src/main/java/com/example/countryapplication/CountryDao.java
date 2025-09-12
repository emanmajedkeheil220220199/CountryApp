package com.example.countryapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CountryDao {
    @Insert
    void insert(CountryEntity country);

    @Update
    void update(CountryEntity country);

    @Delete
    void delete(CountryEntity country);
    @Query("SELECT * FROM Country_tbl WHERE id = :countryId LIMIT 1")
    LiveData<CountryEntity> getCountryById(int countryId);

    @Query("SELECT * FROM Country_tbl ORDER BY name ASC")
    LiveData<List<CountryEntity>> getAllCountries();
}
