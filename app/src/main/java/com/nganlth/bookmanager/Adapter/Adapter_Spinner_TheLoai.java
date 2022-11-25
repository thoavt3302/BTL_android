package com.nganlth.bookmanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

public class Adapter_Spinner_TheLoai extends BaseAdapter {
    Context context;
    ArrayList<TheLoai> dsTheLoai;
    TheLoaiDAO theLoaiDAO;

    public Adapter_Spinner_TheLoai(Context context, ArrayList<TheLoai> dsTheLoai) {
        this.context = context;
        this.dsTheLoai = dsTheLoai;
    }
    @Override
    public int getCount() {
        return dsTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_spinner_theloai,null);
        TextView tvSPTheLoai = convertView.findViewById(R.id.tvSPTheLoai);
        TheLoai theLoai = dsTheLoai.get(position);
        tvSPTheLoai.setText(theLoai.getTenTheLoai());
        return convertView;
    }
}
