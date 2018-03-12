package ru.efirus.app.pizzarizza;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by efirus on 12.03.18.
 */

public class BasketRecyclerViewAdapter extends RecyclerView.Adapter<BasketRecyclerViewAdapter.ViewHolder> {

    final private static String TAG="myLog";
    private List<Basket> baskets;
    private LayoutInflater mInflater;
    private BasketAdapterListener mBasketClickListener;

    // data is passed into the constructor
    BasketRecyclerViewAdapter(Context context, List<Basket> data, BasketAdapterListener listener) {
        //this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.baskets = data;
        this.mBasketClickListener = listener;
    }

    // inflates the row layout from xml when needed
    @Override
    public BasketRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.basket, parent, false);
        return new BasketRecyclerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final BasketRecyclerViewAdapter.ViewHolder holder, int position) {
        final int pos = position;
        String s= (Integer.toString(baskets.get(position).getProductPrice())+" RUB");
        holder.basketPrice.setText(s);
        s= (Integer.toString(baskets.get(position).getBasketid()));
        holder.basketId.setText(s);
        holder.basketName.setText(baskets.get(position).getProductName());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return baskets.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView basketName;
        TextView basketPrice;
        TextView basketId;
        Button button;

        ViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.BasketCardView);
            basketName = (TextView)itemView.findViewById(R.id.basketName);
            basketPrice = (TextView)itemView.findViewById(R.id.basketPrice);
            basketId =(TextView) itemView.findViewById(R.id.basketIdView);
            button =(Button) itemView.findViewById(R.id.buttonDel);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBasketClickListener.ButtonViewOnClick(v, getAdapterPosition());
                }
            });
        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface BasketAdapterListener {
        void ButtonViewOnClick(View v, int position);
    }
}
