package com.example.diariodehumor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HumorAdapter extends RecyclerView.Adapter<HumorAdapter.HumorViewHolder> {

    private List<HumorEntry> lista;

    public HumorAdapter(List<HumorEntry> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public HumorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_humor, parent, false);
        return new HumorViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull HumorViewHolder holder, int position) {
        HumorEntry entry = lista.get(position);

        holder.txtData.setText(entry.data);
        holder.txtAnotacao.setText(entry.anotacao);

        switch (entry.humor) {
            case "feliz":
                holder.imgEmoji.setImageResource(R.drawable.emoji_feliz);
                break;
            case "triste":
                holder.imgEmoji.setImageResource(R.drawable.emoji_triste);
                break;
            case "ansioso":
                holder.imgEmoji.setImageResource(R.drawable.emoji_ansioso);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class HumorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEmoji;
        TextView txtData, txtAnotacao;

        public HumorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEmoji = itemView.findViewById(R.id.imgEmoji);
            txtData = itemView.findViewById(R.id.txtData);
            txtAnotacao = itemView.findViewById(R.id.txtAnotacao);
        }
    }
}
