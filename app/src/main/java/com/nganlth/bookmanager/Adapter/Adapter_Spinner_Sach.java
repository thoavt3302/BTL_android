package com.nganlth.bookmanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

public class Adapter_Spinner_Sach extends BaseAdapter {
    Context context;
    ArrayList<Sach> dsSach;
    SachDAO sachDAO;

    public Adapter_Spinner_Sach(Context context, ArrayList<Sach> dsSach) {
        this.context = context;
        this.dsSach = dsSach;
    }
    @Override
    public int getCount() {
        return dsSach.size();
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
        convertView = inflater.inflate(R.layout.item_spinner_sach,null);
        TextView tvSPTheLoai = convertView.findViewById(R.id.tvSPSach);
        Sach sach = dsSach.get(position);
        tvSPTheLoai.setText(sach.getTieuDe());
        return convertView;
    }
}
