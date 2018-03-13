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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FourActivity extends AppCompatActivity {

    final private static String TAG = "myLog";

    private BasketRecyclerViewAdapter mBasketRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private List<Basket> baskets;
    private AppDatabase db;
    private TextView priceText;
    private Basket basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        priceText = findViewById(R.id.priceBasketView);

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
        baskets = db.basketDao().getAll();
        mBasketRecyclerViewAdapter = new BasketRecyclerViewAdapter(this, baskets, new BasketRecyclerViewAdapter.BasketAdapterListener() {
            @Override
            public void ButtonViewOnClick(View v, int position) {
                switch (v.getId()){
                    case R.id.buttonMinus:
                        basket = baskets.get(position);
                        int qm = basket.getProductQuent();
                        if (qm>1) qm--;
                        basket.setProductQuent(qm);
                        db.basketDao().update(basket);
                        baskets.remove(position);
                        baskets.add(basket);
                        calcPrice();
                        mBasketRecyclerViewAdapter.notifyDataSetChanged();
                        break;
                    case R.id.buttonPlus:
                        basket = baskets.get(position);
                        int qp = basket.getProductQuent();
                        qp++;
                        basket.setProductQuent(qp);
                        db.basketDao().update(basket);
                        baskets.remove(position);
                        baskets.add(basket);
                        calcPrice();
                        mBasketRecyclerViewAdapter.notifyDataSetChanged();
                        break;
                    case R.id.buttonDel:
                        basket = baskets.get(position);
                        baskets.remove(position);
                        db.basketDao().delete(basket);
                        calcPrice();
                        mBasketRecyclerViewAdapter.notifyDataSetChanged();
                        break;
                    default:
                }

            }
        });
        mRecyclerView.setAdapter(mBasketRecyclerViewAdapter);
        calcPrice();

    }

    private void calcPrice(){
        int price = 0;
        if (baskets.size()>0){
            Basket basket;
            for (int i=0; i<baskets.size(); i++){
                basket = baskets.get(i);
                price += (basket.getProductPrice()*basket.getProductQuent());
            }
        }
        priceText.setText(String.valueOf(price+" RUB"));

    }


}
