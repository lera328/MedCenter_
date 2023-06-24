package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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
        DbHelperK dbHelperK=new DbHelperK(holder.buttonGet.getContext()); /// МБ ОШИБКААААААА.
        Analis analis=analisList.get(position);
        holder.tvTitle.setText(analis.getName());
        holder.tvTime.setText(analis.getTime_result());
        holder.tvPrice.setText(analis.getPrice());
        if (dbHelperK.ColObjects(analis.getName())>0)holder.buttonGet.setText("Убрать");
        else holder.buttonGet.setText("Добавить");
        holder.buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt=(Button) v;
                if(bt.getText().equals("Добавить")) {

                    if (onCardClickListenerNew != null) {//bt.setText("Убрать");
                        dbHelperK.addNewObject(analis.getName(),Integer.valueOf(analis.getPrice()));
                        if (dbHelperK.ColObjects(analis.getName())>0)bt.setText("Убрать");
                        else bt.setText("Добавить");
                        onCardClickListenerNew.onCardClickNew(position,Integer.valueOf(analis.getPrice()),String.valueOf(bt.getText()), analis.getName());
                    }
                }
                else {
                    if (onCardClickListenerNew != null) {//bt.setText("Добавить");
                        dbHelperK.deleteRow(analis.getName());
                        if (dbHelperK.ColObjects(analis.getName())>0)bt.setText("Убрать");
                        else bt.setText("Добавить");
                        onCardClickListenerNew.onCardClickNew(position,Integer.valueOf(analis.getPrice()),String.valueOf(bt.getText()), analis.getName());
                    }
                }
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelperK dbHelperK=new DbHelperK(v.getContext());
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context,
                        R.style.BottomSheetDialogTheme);

                View bottomSheetView=LayoutInflater.from(context)
                                .inflate(R.layout.bottom_sheet_dialog,
                                ((LinearLayout)v.findViewById(R.id.btOk))
                                );
                TextView title=bottomSheetView.findViewById(R.id.tvTitle);
                title.setText(analis.getName());
                TextView opisanie=bottomSheetView.findViewById(R.id.tvOpisanie);
                opisanie.setText(analis.getDescription());
                TextView podgotovka=bottomSheetView.findViewById(R.id.tvPodgotovka);
                podgotovka.setText(analis.getPreparation());
                TextView time=bottomSheetView.findViewById(R.id.tvKDay);
                time.setText(analis.getTime_result());
                TextView bio=bottomSheetView.findViewById(R.id.tvBiomaterial);
                bio.setText(analis.getBio());
                Button bg=bottomSheetView.findViewById(R.id.buttonGet);
                bg.setText("Добавить за "+analis.getPrice()+" ₽");
                bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bg.getText()!="В корзину"){
                            dbHelperK.addNewObject(analis.getName(),Integer.valueOf(analis.getPrice()));
                            onCardClickListenerNew.onCardClickNew(position,Integer.valueOf(analis.getPrice()),
                                    "Добавить", analis.getName());
                            bg.setText("В корзину");
                        }
                        else {
                            onCardClickListenerNew.onCardClickNew(position,Integer.valueOf(analis.getPrice()),
                                    "В корзину", analis.getName());
                        }
                    }
                });
                bottomSheetView.findViewById(R.id.btClose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
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
        public ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            buttonGet = itemView.findViewById(R.id.btPlus);
            layout=itemView.findViewById(R.id.layoutc);
        }
    }
}
   ///




