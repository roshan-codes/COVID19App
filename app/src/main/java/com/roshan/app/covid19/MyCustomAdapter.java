package com.roshan.app.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;



    public MyCustomAdapter(Context context, List<CountryModel> countryModelsList) {
        super(context,R.layout.list_custom_item,countryModelsList);

        this.context = context;
        this.countryModelList = countryModelsList;
        this.countryModelListFiltered = countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView countryName = view.findViewById(R.id.countryName);
        ImageView imageView = view.findViewById(R.id.imageflag);

        countryName.setText(countryModelListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();


        }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;

                } else {
                    List<CountryModel> resultModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();
                    for (CountryModel itemsModel : countryModelList) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultModel.add(itemsModel);

                        }
                        filterResults.count = resultModel.size();
                        filterResults.values = resultModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModelListFiltered = (List<CountryModel>) results.values;
                AffectedCountries.countryModelList = (List<CountryModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}

