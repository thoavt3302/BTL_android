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
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Edit_TheLoai;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_Sach.rcv_sach;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.adapterTheloai;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.rcv_phanloai;

public class Adapter_Sach extends RecyclerView.Adapter<Adapter_Sach.TheloaiViewHolder> {
    Context context;
    SachDAO sachDAO;
    ArrayList<Sach> dsSach;
    Adapter_Sach adapterSach;//Thiếu định dạng tiền

    public Adapter_Sach(Context context, ArrayList<Sach> dsSach) {
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

        holder.tvSoLuong.setText(dsSach.get(position).getSoLuong());
        holder.tvMaTheLoai.setText(dsSach.get(position).getMaTheLoai());


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+dsSach.get(position).getTieuDe());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sachDAO = new SachDAO(context);
                                sachDAO.delete(dsSach.get(position).getMaSach());
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                capnhat();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("maSach",dsSach.get(position).getMaSach());
                args.putString("tieuDe", dsSach.get(position).getTieuDe()+"");
                args.putString("tacGia", dsSach.get(position).getTacGia()+"");
                args.putString("nhaXuatBan", dsSach.get(position).getNhaXuatBan()+"");
                args.putString("giaBan", dsSach.get(position).getGiaBan()+"");
                args.putString("soLuong", dsSach.get(position).getSoLuong()+"");
                args.putString("maTheLoai",dsSach.get(position).getMaTheLoai());

                Bottom_Sheet_Edit_Sach bottom_sheet = new Bottom_Sheet_Edit_Sach();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });
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
        private ImageView imgDelete;
        private ImageView imgEdit;
        public TheloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvMaTheLoai = itemView.findViewById(R.id.tvMaTheLoai);
            tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
            tvTacGia = itemView.findViewById(R.id.tvTacGia);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgEdit = itemView.findViewById(R.id.img_edit);
        }
    }
    public void capnhat(){

        dsSach = new ArrayList<>();
        sachDAO = new SachDAO(context);
        dsSach = sachDAO.getSach();
        adapterSach = new Adapter_Sach(context,dsSach);
        rcv_sach.setAdapter(adapterSach);
    }
}
