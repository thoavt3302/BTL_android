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

import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Edit_NguoiDung;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Edit_TheLoai;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.adapterTheloai;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.rcv_phanloai;

public class Adapter_Theloai extends RecyclerView.Adapter<Adapter_Theloai.TheloaiViewHolder> {
    Context context;
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> dsTheLoai;

    public Adapter_Theloai(Context context, ArrayList<TheLoai> dsTheLoai) {
        this.context = context;
        this.dsTheLoai = dsTheLoai;
    }

    @NonNull
    @Override
    public TheloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_theloai,parent,false);
        TheloaiViewHolder holder = new TheloaiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheloaiViewHolder holder, final int position) {
        holder.tv_maTheLoai.setText(dsTheLoai.get(position).getMaTheLoai());
        holder.tv_tenTheLoai.setText(dsTheLoai.get(position).getTenTheLoai());
        holder.tv_moTa.setText(dsTheLoai.get(position).getMoTa());
        holder.tv_viTri.setText(dsTheLoai.get(position).getViTri());


        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+dsTheLoai.get(position).getTenTheLoai());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                theLoaiDAO = new TheLoaiDAO(context);
                                theLoaiDAO.delete(dsTheLoai.get(position).getMaTheLoai());
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
                args.putString("maTheLoai",dsTheLoai.get(position).getMaTheLoai());
                args.putString("tenTheLoai", dsTheLoai.get(position).getTenTheLoai()+"");
                args.putString("moTa", dsTheLoai.get(position).getMoTa()+"");
                args.putString("viTri", dsTheLoai.get(position).getViTri()+"");

                Bottom_Sheet_Edit_TheLoai bottom_sheet = new Bottom_Sheet_Edit_TheLoai();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });
    }
    @Override
    public int getItemCount() {
        return dsTheLoai.size();
    }

    public class TheloaiViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_maTheLoai;
        private TextView tv_tenTheLoai;
        private TextView tv_moTa;
        private TextView tv_viTri;
        private ImageView img_delete;
        private ImageView img_eidt;
        public TheloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maTheLoai = itemView.findViewById(R.id.tv_maTheLoai);
            tv_tenTheLoai = itemView.findViewById(R.id.tv_tenTheLoai);
            tv_moTa = itemView.findViewById(R.id.tv_moTa);
            tv_viTri = itemView.findViewById(R.id.tv_viTri);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_eidt = itemView.findViewById(R.id.img_edit);
        }
    }
    public void capnhat(){
        dsTheLoai = new ArrayList<>();
        theLoaiDAO = new TheLoaiDAO(context);
        dsTheLoai= theLoaiDAO.getTheLoai();
        adapterTheloai = new Adapter_Theloai(context,dsTheLoai);
        rcv_phanloai.setAdapter(adapterTheloai);
    }
}
