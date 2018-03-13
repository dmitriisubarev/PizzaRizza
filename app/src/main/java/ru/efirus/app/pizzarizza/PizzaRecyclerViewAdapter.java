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
 * Created by efirus on 08.03.18.
 */

public class PizzaRecyclerViewAdapter extends RecyclerView.Adapter<PizzaRecyclerViewAdapter.ViewHolder> {
    final private static String TAG="myLog";
    private Context mContext;
    private List<Pizza> pizzas;
    private LayoutInflater mInflater;
    private PizzaAdapterListener mPizzaClickListener;

    // data is passed into the constructor
    PizzaRecyclerViewAdapter(Context context, List<Pizza> data, PizzaAdapterListener listener) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.pizzas = data;
        this.mPizzaClickListener = listener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pizza, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos = position;
        String s= (Integer.toString(pizzas.get(position).getPrice())+" RUB");
        holder.pizzaName.setText(pizzas.get(position).getName());
        holder.pizzaDescription.setText(pizzas.get(position).getDescription());
        holder.pizzaPrice.setText(s);
        holder.pizzaSize.setText(pizzas.get(position).getSize());
        Picasso.with(mContext)//передаем контекст приложения
                .load( pizzas.get(position).getImage()) //адрес изображения
                .into(holder.image); //ссылка на ImageView

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView pizzaName;
        TextView pizzaDescription;
        TextView pizzaPrice;
        TextView pizzaSize;
        ImageView image;
        Button button;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            pizzaName = (TextView)itemView.findViewById(R.id.namePizza);
            pizzaDescription = (TextView)itemView.findViewById(R.id.descriptionPizza);
            pizzaPrice = (TextView)itemView.findViewById(R.id.pricePizza);
            pizzaSize = (TextView)itemView.findViewById(R.id.sizePizza);
            image = (ImageView)itemView.findViewById(R.id.imagePizza);
            button = (Button)itemView.findViewById(R.id.toBuyPizza);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPizzaClickListener.ButtonViewOnClick(v, getAdapterPosition());
                }
            });
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface PizzaAdapterListener {
        void ButtonViewOnClick(View v, int position);
    }


}
