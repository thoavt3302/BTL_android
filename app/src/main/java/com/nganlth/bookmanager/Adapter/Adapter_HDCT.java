package com.nganlth.bookmanager.Adapter;

import android.app.AlertDialog;
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
import androidx.recyclerview.widget.RecyclerView;

import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Edit_HDCT;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Edit_Sach;
import com.nganlth.bookmanager.DAO.HoaDonChiTietDAO;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.Model.HoaDonChiTiet;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.ChiTietHoaDon.rcvHDCT;
import static com.nganlth.bookmanager.Fragment.Fragment_Sach.rcv_sach;

public class Adapter_HDCT extends RecyclerView.Adapter<Adapter_HDCT.TheloaiViewHolder> {
    Context context;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    private ArrayList<HoaDonChiTiet> dsHDCT;
    private Adapter_HDCT adapter_hdct;//Thiếu định dạng tiền
    ArrayList<Sach> dsSach;

    public Adapter_HDCT(Context context, ArrayList<HoaDonChiTiet> dsHDCT) {
        this.context = context;
        this.dsHDCT = dsHDCT;
    }

    @NonNull
    @Override
    public TheloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_hdct,parent,false);
        TheloaiViewHolder holder = new TheloaiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheloaiViewHolder holder, final int position) {
        HoaDonChiTiet item = dsHDCT.get(position);
        SachDAO sachDAO = new SachDAO(context);
        dsSach = new ArrayList<Sach>();
        dsSach = sachDAO.getSach();
//        dsHDCT = new ArrayList<>();

        //Lấy vị trí của spinner
        int vitriTen = Integer.parseInt(item.getMaSach());
        int vitriGia = Integer.parseInt(item.getMaSach());
        holder.tvMaHDCT.setText("Mã hóa đơn CT: "+dsHDCT.get(position).getMaHDCT());
        holder.tvMaHD.setText("Mã hóa đơn: "+dsHDCT.get(position).getMaHoaDon());
        holder.tvMaSach.setText("Mã sách: "+Integer.parseInt(dsSach.get(vitriTen-1).getMaSach()));
        holder.tvSoluongHDCT.setText("Số lượng: "+item.getSoLuongHDCT());
        holder.tvGiaBanHDCT.setText("Giá: "+dsSach.get(vitriGia-1).getGiaBan());
        holder.tvThanhTienHDCT.setText("Số tiền: "+(Integer.parseInt(item.getSoLuongHDCT()))*Integer.parseInt(dsSach.get(vitriGia-1).getGiaBan())+" VNĐ");

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa hóa đơn có mã: "+dsHDCT.get(position).getMaHDCT());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
                                hoaDonChiTietDAO.delete(dsHDCT.get(position).getMaHDCT());
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
                args.putString("maHDCT",dsHDCT.get(position).getMaHDCT()+"");
                args.putString("maHoaDon",dsHDCT.get(position).getMaHoaDon()+"");
                args.putString("maSach", dsHDCT.get(position).getMaSach()+"");
                args.putString("soLuongHDCT", dsHDCT.get(position).getSoLuongHDCT()+"");

                Bottom_Sheet_Edit_HDCT bottom_sheet = new Bottom_Sheet_Edit_HDCT();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });
    }
    @Override
    public int getItemCount() {
        return dsHDCT.size();
    }

    public class TheloaiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaHDCT;
        private TextView tvMaHD;
        private TextView tvMaSach;
        private TextView tvGiaBanHDCT;
        private TextView tvThanhTienHDCT;
        private TextView tvSoluongHDCT;
        private ImageView imgDelete;
        private ImageView imgEdit;
        public TheloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHDCT = itemView.findViewById(R.id.tvMaHDCT);
            tvMaHD = itemView.findViewById(R.id.tvMaHD);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvSoluongHDCT = itemView.findViewById(R.id.tvSoluongHDCT);
            tvGiaBanHDCT = itemView.findViewById(R.id.tvGiaBanHDCT);
            tvThanhTienHDCT = itemView.findViewById(R.id.tvThanhTienHDCT);

            imgDelete = itemView.findViewById(R.id.img_delete);
            imgEdit = itemView.findViewById(R.id.img_edit);
        }
    }
    public void capnhat(){
        dsHDCT = new ArrayList<>();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
        dsHDCT = hoaDonChiTietDAO.getHDCT();
        adapter_hdct = new Adapter_HDCT(context,dsHDCT);
        rcvHDCT.setAdapter(adapter_hdct);
    }
}
