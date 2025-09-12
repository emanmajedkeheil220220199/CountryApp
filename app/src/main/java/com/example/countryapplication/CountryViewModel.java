package com.example.countryapplication;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
public class CountryViewModel  extends AndroidViewModel {
    private CountryRepository repository;
    private LiveData<List<CountryEntity>> allCountries;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        repository = new CountryRepository(application);
        allCountries = repository.getAllCountries();
    }

    public LiveData<List<CountryEntity>> getAllCountries() { return allCountries; }
    public LiveData<CountryEntity> getCountryById(int id) {
        return repository.getCountryById(id);
    }

    public void insert(CountryEntity country) { repository.insert(country); }
    public void update(CountryEntity country) { repository.update(country); }
    public void delete(CountryEntity country) { repository.delete(country); }
}
