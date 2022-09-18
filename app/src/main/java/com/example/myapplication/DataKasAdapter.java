package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DataKasAdapter extends RecyclerView.Adapter<DataKasAdapter.ViewHolder> {

    private final ArrayList<DataKas> listKas;

    public DataKasAdapter(ArrayList<DataKas> listKas) {
        this.listKas    = listKas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView txt_nominal;
        private final TextView txt_keterangan;
        private final TextView txt_tanggal;
        private final ImageView img_status;

        public ViewHolder(final View view){
            super(view);
            txt_nominal = view.findViewById(R.id.tvNominal);
            txt_keterangan = view.findViewById(R.id.tvKeterangan);
            txt_tanggal = view.findViewById(R.id.tvTanggal);
            img_status = view.findViewById(R.id.imageViewStatus);
        }
    }

    @NonNull
    @Override
    public DataKasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kas_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat formatRp = NumberFormat.getInstance(Locale.GERMANY);

        String nominal = listKas.get(position).getNominal();
        String keterangan = listKas.get(position).getKeterangan();
        String tanggal = listKas.get(position).getTanggal();
        String tipe_kas = listKas.get(position).getTipe_kas();

        String formatRp_nominal = "Rp. "+formatRp.format(Double.parseDouble(nominal));
        holder.txt_nominal.setText(formatRp_nominal);
        holder.txt_keterangan.setText(keterangan);
        holder.txt_tanggal.setText(tanggal);

        if(tipe_kas.equals("Pengeluaran")){
            holder.img_status.setImageResource(R.drawable.ic_baseline_arrow_forward_24);
        }else{
            holder.img_status.setImageResource(R.drawable.ic_baseline_arrow_back_24);
        }
    }

    @Override
    public int getItemCount() {
        return listKas.size();
    }
}
