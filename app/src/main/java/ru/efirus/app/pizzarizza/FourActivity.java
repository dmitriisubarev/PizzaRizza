package ru.efirus.app.pizzarizza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FourActivity extends AppCompatActivity {

    final private static String TAG = "myLog";

    private BasketRecyclerViewAdapter mBasketRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private List<Basket> baskets;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        baskets = new ArrayList<>();
        db = App.getInstance().getDatabase();
        baskets = db.basketDao().getAll();

        // настройка RecyclerView
        mRecyclerView = this.findViewById(R.id.rvBasket);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (db==null) db = App.getInstance().getDatabase();
        Log.d(TAG,"baskets size start = "+baskets.size());
        baskets = db.basketDao().getAll();
        mBasketRecyclerViewAdapter = new BasketRecyclerViewAdapter(this, baskets, new BasketRecyclerViewAdapter.BasketAdapterListener() {
            @Override
            public void ButtonViewOnClick(View v, int position) {
                Basket basket = baskets.get(position);
                baskets.remove(position);
                db.basketDao().delete(basket);
                mBasketRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView.setAdapter(mBasketRecyclerViewAdapter);
        Log.d(TAG,"baskets size = "+baskets.size());
    }


}
