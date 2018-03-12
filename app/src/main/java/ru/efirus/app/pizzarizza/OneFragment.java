package ru.efirus.app.pizzarizza;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    private static final String TAG = "myLogs";
    Context context;
    //1 Пользовательский интерфейс
    private PizzaRecyclerViewAdapter mPizzaRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private List<Pizza> pizzas;

    //2 Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pizzas = new ArrayList<>();

        // настройка RecyclerView
        context = getContext();
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        mRecyclerView = v.findViewById(R.id.rvFragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mPizzaRecyclerViewAdapter = new PizzaRecyclerViewAdapter(context, pizzas, new PizzaRecyclerViewAdapter.PizzaAdapterListener() {
            @Override
            public void ButtonViewOnClick(View v, int position) {
                // action on click
                Log.d(TAG, "press button");
                AppDatabase db = App.getInstance().getDatabase();
                List<Basket> mListBasket = db.basketDao().getAll();

                Basket basket = new Basket();
                if (mListBasket.size() > 0){
                    basket.setBasketid(mListBasket.get(mListBasket.size()-1).getBasketid() + 1);
                }
                else
                    basket.setBasketid(0);
                basket.setProductName(pizzas.get(position).getName());
                basket.setProductPrice(pizzas.get(position).getPrice());
                basket.setProductQuent(1);
                db.basketDao().insert(basket);
            }
        });

        mRecyclerView.setAdapter(mPizzaRecyclerViewAdapter);

        //3 Инициализация бд firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("products").child("pizza");


        //4 Создаем слушателя бд
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Pizza pizza = dataSnapshot.getValue(Pizza.class);
                    pizzas.add(pizza);
                    mPizzaRecyclerViewAdapter.notifyItemChanged(pizzas.size()-1);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Log.d(TAG,"onChildChanged");

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d(TAG,"onChildRemoved");
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    Log.d(TAG,"onChildMoved");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, "database error");
                }
            };
            //5 Устанавливаем слушателя базы данных
            mDatabaseReference.addChildEventListener(mChildEventListener);

        return v;
    }

    @Override
    public void onPause() {
        if (mChildEventListener != null){
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
        super.onPause();
    }


    //6 Удаляем слушателя базы данных
    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        if (mChildEventListener != null){
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
        super.onDestroy();
    }
}
