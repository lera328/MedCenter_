package com.example.medcenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardBanerAdapter extends RecyclerView.Adapter<CardBanerAdapter.ViewHolder> {

    private List<cardBanerModel> cardList;
    private Context context;

    public CardBanerAdapter(List<cardBanerModel> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_baner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final cardBanerModel card = cardList.get(position);

        holder.tvftitle.setText(card.getTitle1());
        holder.tvstitle.setText(card.getTitle2());
        holder.tvprice.setText(String.valueOf(card.getCost()));
        holder.cardView.setBackground(card.getImage());

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvftitle, tvstitle, tvprice;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvftitle = itemView.findViewById(R.id.tvftitle);
            tvstitle = itemView.findViewById(R.id.tvstitle);
            tvprice = itemView.findViewById(R.id.tvprice);

            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
