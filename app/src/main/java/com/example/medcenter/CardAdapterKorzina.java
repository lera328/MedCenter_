package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapterKorzina extends RecyclerView.Adapter<CardAdapterKorzina.ViewHolder> {

    private List<ObjectForKorzina> cardList;

    Context context;
    private OnCardClickListener onCardClickListener;
    public interface OnCardClickListener {
        void onCardClick(String text,boolean f, int price);
    }

    public CardAdapterKorzina(List<ObjectForKorzina> cardList, Context context, OnCardClickListener onCardClickListener) {
        this.cardList = cardList;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }





    public CardAdapterKorzina(List<ObjectForKorzina> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_korzina, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DbHelperK dbHelperK=new DbHelperK(context);
        ObjectForKorzina model = cardList.get(position);
        holder.tvTitle.setText(model.getName());
        holder.tvPrice.setText(String.valueOf(model.getPrice()));
        holder.tvKol.setText(dbHelperK.ColObjects(model.getName())+"");
        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        dbHelperK.addNewObject(model.getName(),model.getPrice());
                        int k=dbHelperK.ColObjects(model.getName());
                        holder.tvKol.setText(k +"");
                        onCardClickListener.onCardClick(model.getName(),true, model.getPrice());


            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        dbHelperK.deleteRow(model.getName());
                        int k=dbHelperK.ColObjects(model.getName());
                        holder.tvKol.setText(k+"");
                        onCardClickListener.onCardClick(model.getName(),true, model.getPrice());
            }
        });

        holder.buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelperK.deleteAllRow(model.getName());
                onCardClickListener.onCardClick(model.getName(),false, model.getPrice());
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvKol;
        public TextView tvPrice;
        public Button buttonPlus;
        public Button buttonMinus;
        public ImageButton buttonDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvKol=itemView.findViewById(R.id.tvKol);
            buttonPlus=itemView.findViewById(R.id.btPlus);
            buttonMinus=itemView.findViewById(R.id.btMinus);
            buttonDel=itemView.findViewById(R.id.btDel);
        }
    }
}
