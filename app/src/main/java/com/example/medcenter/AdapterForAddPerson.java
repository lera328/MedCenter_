package com.example.medcenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterForAddPerson extends RecyclerView.Adapter<AdapterForAddPerson.ViewHolder>{
    private final List<String> personList;
    private final LayoutInflater inflater;

    private OnItemClickListener mListener;

    private int selectedPosition = -1;


    public interface OnItemClickListener {
        void onItemClick(View view, int position, int SelectedPosition);
    }

    public AdapterForAddPerson(Context context, List<String> list, OnItemClickListener mListener ){
    this.personList=list;
    this.inflater = LayoutInflater.from(context);
    this.mListener = mListener;
}

    @NonNull
    @Override
    public AdapterForAddPerson.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_person,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv.setText(personList.get(position));
        if (selectedPosition == position) {
            //holder.itemView.setSelected(true); //using selector drawable
            holder.tv.setBackground(ContextCompat.getDrawable(holder.tv.getContext(),R.drawable.shape_layout_select));
        } else {
            //holder.itemView.setSelected(false);
            holder.tv.setBackground(ContextCompat.getDrawable(holder.tv.getContext(),R.drawable.shape_layout));
        }

        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition);
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);
        });

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tv;
        ViewHolder(View view){
            super(view);
            tv=view.findViewById(R.id.textview);
        }
    }

    public Integer getSelectedItem(){
        return selectedPosition;
    }
}
