package com.example.countryapplication;
import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CountryRepository {
    private CountryDao countryDao;
    private LiveData<List<CountryEntity>> allCountries;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public CountryRepository(Application application) {
        CountryDatabase db = CountryDatabase.getInstance(application);
        countryDao = db.countryDao();
        allCountries = countryDao.getAllCountries();
    }

    public LiveData<List<CountryEntity>> getAllCountries() { return allCountries; }

    public LiveData<CountryEntity> getCountryById(int id) {
        return countryDao.getCountryById(id);
    }

    public void insert(CountryEntity country) {
        executor.execute(() -> countryDao.insert(country));
    }

    public void update(CountryEntity country) {
        executor.execute(() -> countryDao.update(country));
    }

    public void delete(CountryEntity country) {
        executor.execute(() -> countryDao.delete(country));
    }
}
