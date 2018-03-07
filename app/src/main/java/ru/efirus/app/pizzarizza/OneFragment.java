package ru.efirus.app.pizzarizza;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.efirus.app.pizzarizza.MyRecyclerViewAdapter;
import ru.efirus.app.pizzarizza.Product;
import ru.efirus.app.pizzarizza.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    MyRecyclerViewAdapter rvAdapter;
    private List<Product> products;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initializeData();
        // set up the RecyclerView
        Context context = getContext();
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.rvFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        rvAdapter = new MyRecyclerViewAdapter(context, products);
        recyclerView.setAdapter(rvAdapter);

        return v;
    }


    private void initializeData(){
        products = new ArrayList<>();
        products.add(new Product("Pizza1", "pepperoni", R.string.priceItem, R.mipmap.ic_launcher_round, false));
        products.add(new Product("Pizza2", "pepperoni", R.string.priceItem, R.mipmap.ic_launcher_round, false));
        products.add(new Product("Pizza3", "pepperoni", R.string.priceItem, R.mipmap.ic_launcher_round, false));
        products.add(new Product("Pizza4", "pepperoni", R.string.priceItem, R.mipmap.ic_launcher_round, false));
        products.add(new Product("Pizza5", "pepperoni", R.string.priceItem, R.mipmap.ic_launcher_round, false));
        products.add(new Product("Pizza6", "pepperoni", R.string.priceItem, R.mipmap.ic_launcher_round, false));
    }

}
