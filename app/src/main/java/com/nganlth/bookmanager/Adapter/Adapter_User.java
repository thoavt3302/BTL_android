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
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.DAO.UserDAO;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.Model.User;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;


import static com.nganlth.bookmanager.Fragment.Fragment_User.rcv_user;

public class Adapter_User extends RecyclerView.Adapter<Adapter_User.UserViewHolder> {
    Context context;
    UserDAO userDAO;
    ArrayList<User> dsUser;
    Adapter_User adapter_user;

    public Adapter_User(Context context, ArrayList<User> dsUser) {
        this.context = context;
        this.dsUser = dsUser;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        holder.tv_Username.setText(dsUser.get(position).getUserName());
        holder.tv_hoTen.setText(dsUser.get(position).getHoTen());
        holder.tv_soDienThoai.setText(dsUser.get(position).getSoDienThoai());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Thông Báo");
                builder1.setMessage("Bạn có chắc muốn xóa "+dsUser.get(position).getUserName());
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userDAO = new UserDAO(context);
                                userDAO.delete(dsUser.get(position).getUserName());
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
                args.putString("userName", dsUser.get(position).getUserName()+"");
                args.putString("passWord", dsUser.get(position).getPassWord()+"");
                args.putString("hoTen", dsUser.get(position).getHoTen()+"");
                args.putString("soDienThoai",dsUser.get(position).getSoDienThoai()+"");

                Bottom_Sheet_Edit_NguoiDung bottom_sheet = new Bottom_Sheet_Edit_NguoiDung();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });
    }
    @Override
    public int getItemCount() {
        return dsUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Username;
        private TextView tv_hoTen;
        private TextView tv_soDienThoai;
        private ImageView img_delete;
        private ImageView img_eidt;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Username = itemView.findViewById(R.id.tv_userName);
            tv_hoTen = itemView.findViewById(R.id.tv_hoTen);
            tv_soDienThoai = itemView.findViewById(R.id.tv_soDienThoai);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_eidt = itemView.findViewById(R.id.img_edit);
        }
    }
    public void capnhat(){
        dsUser = new ArrayList<>();
        userDAO = new UserDAO(context);
        dsUser= userDAO.getUser();
        adapter_user = new Adapter_User(context,dsUser);
        rcv_user.setAdapter(adapter_user);
    }
}
