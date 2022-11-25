package com.nganlth.bookmanager.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Edit_Sach;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_Sach.rcv_sach;

public class Adapter_TopSach extends RecyclerView.Adapter<Adapter_TopSach.TheloaiViewHolder> {
    Context context;
    SachDAO sachDAO;
    ArrayList<Sach> dsSach;
    Adapter_TopSach adapterSach;//Thiếu định dạng tiền

    public Adapter_TopSach(Context context, ArrayList<Sach> dsSach) {
        this.context = context;
        this.dsSach = dsSach;
    }

    @NonNull
    @Override
    public TheloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_sach,parent,false);
        TheloaiViewHolder holder = new TheloaiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheloaiViewHolder holder, final int position) {
        holder.tvMaSach.setText(dsSach.get(position).getMaSach());
        holder.tvTieuDe.setText(dsSach.get(position).getTieuDe());
        holder.tvTacGia.setText(dsSach.get(position).getTacGia());
        holder.tvGiaBan.setText(dsSach.get(position).getGiaBan());
    }
    @Override
    public int getItemCount() {
        return dsSach.size();
    }

    public class TheloaiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaSach;
        private TextView tvMaTheLoai;
        private TextView tvTieuDe;
        private TextView tvTacGia;
        private TextView tvGiaBan;
        private TextView tvSoLuong;
        public TheloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvMaTheLoai = itemView.findViewById(R.id.tvMaTheLoai);
            tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
            tvTacGia = itemView.findViewById(R.id.tvTacGia);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        }
    }
    public void capnhat(){
        dsSach = new ArrayList<>();
        sachDAO = new SachDAO(context);
        dsSach = sachDAO.getSach();
        adapterSach = new Adapter_TopSach(context,dsSach);
        rcv_sach.setAdapter(adapterSach);
    }
}
