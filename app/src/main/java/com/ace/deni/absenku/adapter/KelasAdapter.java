package com.ace.deni.absenku.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ace.deni.absenku.R;
import com.ace.deni.absenku.model.Kelas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deni Supriyatna on 10/01/2019.
 */
public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.KelasHolder> {

    public interface OnItemClickListener{
        public void OnItemClick(Kelas item);
    }

    class KelasHolder extends RecyclerView.ViewHolder{
        public TextView textIdKelas, textDescKelas;

        public KelasHolder(@NonNull View itemView){
            super(itemView);
            textIdKelas = itemView.findViewById(R.id.list_id_kelas);
            textDescKelas = itemView.findViewById(R.id.list_desc_kelas);

        }

        public void bind(final Kelas item, final OnItemClickListener listener){
            textIdKelas.setText(item.getNamaKelas());
            textDescKelas.setText(item.getDescKelas());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.OnItemClick(item);
                    }
                }
            });
        }
    }

    private List<Kelas> list = new ArrayList<>();
    private OnItemClickListener listener;

    public KelasAdapter(){

    }

    public KelasAdapter(List<Kelas>list){
        this.list = list;
    }

    public KelasAdapter(List<Kelas> list, OnItemClickListener listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public KelasHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.kelas_item_list, viewGroup, false);
        KelasHolder holder = new KelasHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KelasHolder kelasHolder, int i) {
        Kelas item = list.get(i);
        kelasHolder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
