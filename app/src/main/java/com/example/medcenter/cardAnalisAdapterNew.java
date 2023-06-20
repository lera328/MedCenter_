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

public class cardAnalisAdapterNew extends RecyclerView.Adapter<cardAnalisAdapterNew.ViewHolder> {

    private List<Analis> analisList;
    Context context;
    private cardAnalisAdapterNew.OnCardClickListener onCardClickListenerNew;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_analis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Analis analis=analisList.get(position);
        holder.tvTitle.setText(analis.getName());
        holder.tvTime.setText(analis.getTime_result());
        holder.tvPrice.setText(analis.getPrice());
        holder.buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt=(Button) v;
                DbHelperK dbHelperK=new DbHelperK(v.getContext());
                if(bt.getText().equals("Добавить")) {

                    if (onCardClickListenerNew != null) {bt.setText("Убрать");
                        dbHelperK.addNewInsult(analis.getName(),Integer.valueOf(analis.getPrice()));

                        onCardClickListenerNew.onCardClickNew(position,Integer.valueOf(analis.getPrice()),String.valueOf(bt.getText()), analis.getName());
                    }
                }
                else {
                    if (onCardClickListenerNew != null) {bt.setText("Добавить");
                        dbHelperK.deleteRow(analis.getName());
                        onCardClickListenerNew.onCardClickNew(position,Integer.valueOf(analis.getPrice()),String.valueOf(bt.getText()), analis.getName());
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return analisList.size();
    }

    public interface OnCardClickListener{


        void onCardClickNew(int position, int cost, String text, String name);
    }

    public cardAnalisAdapterNew(List<Analis> analisList, Context context, OnCardClickListener onCardClickListener) {
        this.analisList = analisList;
        this.context = context;
        this.onCardClickListenerNew = onCardClickListener;
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
            buttonGet = itemView.findViewById(R.id.btPlus);
        }
    }
}
   ///




