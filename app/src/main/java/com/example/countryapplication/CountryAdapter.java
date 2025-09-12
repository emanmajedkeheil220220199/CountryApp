package com.example.countryapplication;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<CountryEntity> countries = new ArrayList<>();
    private Context context;

    public CountryAdapter(Context context) {
        this.context = context;
    }

    public void setCountries(List<CountryEntity> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryEntity country = countries.get(position);
        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());
        holder.flag.setImageResource(country.getFlagResId());

        holder.itemView.setOnClickListener(v -> {
     Intent intent = new Intent(context, DetailsActivity.class);
             intent.putExtra("countryId", country.getId());
           context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return countries.size(); }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView flag;
        TextView name, capital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.imgFlag);
            name = itemView.findViewById(R.id.tvName);
            capital = itemView.findViewById(R.id.tvCapital);
        }
    }
}
