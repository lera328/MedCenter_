package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAnalisAdapter extends RecyclerView.Adapter<CardAnalisAdapter.ViewHolder> {

    private List<cardAnalisModel> cardList;

    Context context;
    private OnCardClickListener onCardClickListener;
    public interface OnCardClickListener {
        void onCardClick(int position,int cost, String text);
    }

    public CardAnalisAdapter(List<cardAnalisModel> cardList, Context context, OnCardClickListener onCardClickListener) {
        this.cardList = cardList;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }





    public CardAnalisAdapter(List<cardAnalisModel> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_analis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        cardAnalisModel model = cardList.get(position);
        holder.tvTitle.setText(model.getTitle());
        holder.tvTime.setText(String.valueOf(model.getTime()));
        holder.tvPrice.setText(String.valueOf(model.getCost()));
        holder.buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button bt=(Button) v;
                if(bt.getText().equals("Добавить")) {

                    if (onCardClickListener != null) {bt.setText("Убрать");
                    onCardClickListener.onCardClick(position,Integer.valueOf(model.price),String.valueOf(bt.getText()));}}
                else {
                    if (onCardClickListener != null) {bt.setText("Добавить");
                    onCardClickListener.onCardClick(position,Integer.valueOf(model.price),String.valueOf(bt.getText()));}}

            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvTime;
        public TextView tvPrice;
        public Button buttonGet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            buttonGet = itemView.findViewById(R.id.buttonGet);
        }
    }
}
