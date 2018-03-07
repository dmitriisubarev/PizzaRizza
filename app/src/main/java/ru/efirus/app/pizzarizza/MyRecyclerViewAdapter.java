package ru.efirus.app.pizzarizza;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by efirus on 07.03.18.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Product> products;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Product> data) {
        this.mInflater = LayoutInflater.from(context);
        this.products = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s= (Integer.toString(products.get(position).price)+" р");
        holder.productName.setText(products.get(position).name);
        holder.productDescription.setText(products.get(position).describe);
        holder.productPrice.setText(s);
        holder.productImage.setImageResource(products.get(position).image);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return products.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView productName;
        TextView productDescription;
        TextView productPrice;
        ImageView productImage;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            productName = (TextView)itemView.findViewById(R.id.textView);
            productDescription = (TextView)itemView.findViewById(R.id.textView2);
            productPrice = (TextView)itemView.findViewById(R.id.textView3);
            productImage = (ImageView)itemView.findViewById(R.id.imageView);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
