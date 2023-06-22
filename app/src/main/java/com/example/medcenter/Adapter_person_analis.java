package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_person_analis extends RecyclerView.Adapter<Adapter_person_analis.ViewHolder>{
    private final List<Integer>pos_list;

    private OnItemClickListener1 mListener1;
    private final LayoutInflater inflater;

    public interface OnItemClickListener1 {
        void onItemClick(View view, int position);
    }

    public Adapter_person_analis(Context context, List<Integer> position ){

    this.inflater = LayoutInflater.from(context);
    this.pos_list=position;
}

    @NonNull
    @Override
    public Adapter_person_analis.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_pacient_analis,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        DbHelperK dbHelperK=new DbHelperK(inflater.getContext());
        List<String> spinnerPacientList = new ArrayList<>();
        for (CardPacient pacient : dbHelperK.getPersonList()) {
            if (pacient.getPol().contains("Женский"))
                spinnerPacientList.add("\uD83D\uDC69 \t"+pacient.getSecondName() + " " + pacient.getName());
            else spinnerPacientList.add("\uD83D\uDC68 \t"+pacient.getSecondName() + " " + pacient.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.spinner.getContext(), android.R.layout.simple_spinner_item, spinnerPacientList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);
        holder.spinner.setSelection(pos_list.get(position));
       if (holder.linearLayout.getChildCount() == 0) {
           for (ObjectForKorzina object : dbHelperK.getKorzina()) {
               String chbText = object.getName() + "\t" + object.getPrice()+"₽";
               CheckBox checkBox = new CheckBox(holder.linearLayout.getContext());
               checkBox.setText(chbText);
               holder.linearLayout.addView(checkBox);
           }
       }

    }

    @Override
    public int getItemCount() {
        return pos_list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        Spinner spinner;
        LinearLayout linearLayout;
        ViewHolder(View view){
            super(view);
            spinner=view.findViewById(R.id.spinner4);
            linearLayout=view.findViewById(R.id.linear);
        }
    }


}
