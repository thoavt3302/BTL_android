package com.nganlth.bookmanager.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Edit_HoaDon;
import com.nganlth.bookmanager.ChiTietHoaDon;
import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.R;
import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_Hoadon.rcvHoaDon;

public class Adapter_HoaDon extends RecyclerView.Adapter<Adapter_HoaDon.TheloaiViewHolder> {
    Context context;
    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> dsHoaDon;
    Dialog dialog;
    Adapter_HoaDon adapterHoaDon;

    public Adapter_HoaDon(Context context, ArrayList<HoaDon> dsHoaDon) {
        this.context = context;
        this.dsHoaDon = dsHoaDon;
    }

    @NonNull
    @Override
    public TheloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_hoadon,parent,false);
        TheloaiViewHolder holder = new TheloaiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TheloaiViewHolder holder, final int position) {
        HoaDon item = dsHoaDon.get(position);
        holder.tvMaHoaDon.setText(dsHoaDon.get(position).getMaHoaDon());
        holder.tvTenKhachHang.setText(dsHoaDon.get(position).getTenKhachHang());
        holder.tvNgayMua.setText(dsHoaDon.get(position).getNgayMua());



        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa hóa đơn có mã:  "+dsHoaDon.get(position).getMaHoaDon());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                hoaDonDAO = new HoaDonDAO(context);
                                hoaDonDAO.delete(dsHoaDon.get(position).getMaHoaDon());
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
        holder.img_eidt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("maHoaDon",dsHoaDon.get(position).getMaHoaDon()+"");
//                args.putString("tenKhachHang",dsHoaDon.get(position).getTenKhachHang()+"");
//                args.putString("ngayMua", dsHoaDon.get(position).getNgayMua()+"");


                Bottom_Sheet_Edit_HoaDon bottom_sheet = new Bottom_Sheet_Edit_HoaDon();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

        // dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_hdct);
        TextView maHDCT = dialog.findViewById(R.id.tvMaHDCT);
        final TextView maHoaDon = dialog.findViewById(R.id.tvMaHD);
        TextView maSach = dialog.findViewById(R.id.tvMaSach);
        TextView soLuong = dialog.findViewById(R.id.tvSoluongHDCT);
        TextView giaBan = dialog.findViewById(R.id.tvGiaBanHDCT);
        TextView thanhTien = dialog.findViewById(R.id.tvThanhTienHDCT);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, ChiTietHoaDon.class);
                Bundle bundle = new Bundle();
                bundle.putString("maHoaDon",dsHoaDon.get(position).getMaHoaDon());
                myIntent.putExtras(bundle);
                context.startActivity(myIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dsHoaDon.size();
    }

    public class TheloaiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaHoaDon;
        private TextView tvTenKhachHang;
        private TextView tvNgayMua;
        private ImageView img_delete;
        private ImageView img_eidt;
        private CardView cardView;
        public TheloaiViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvMaHoaDon = itemView.findViewById(R.id.tvMaHD);
            tvTenKhachHang = itemView.findViewById(R.id.tvTenKhachHang);
            tvNgayMua = itemView.findViewById(R.id.tvNgayMua);
            cardView = itemView.findViewById(R.id.cvHoaDon);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_eidt = itemView.findViewById(R.id.img_edit);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null){
//                        listener.OnItemClick(itemView,getLayoutPosition());
//                    }
//                }
//            });
        }
    }
    public void capnhat(){
        dsHoaDon = new ArrayList<>();
        hoaDonDAO = new HoaDonDAO(context);
        dsHoaDon = hoaDonDAO.getHoaDon();
        adapterHoaDon = new Adapter_HoaDon(context,dsHoaDon);
        rcvHoaDon.setAdapter(adapterHoaDon);
    }

    // Tạo hàm onclick
    private static  Adapter_HoaDon.OnItemClickListener listener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener (Adapter_HoaDon.OnItemClickListener listener){
        this.listener = listener;
    }
}
