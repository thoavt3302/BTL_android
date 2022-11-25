package com.nganlth.bookmanager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.nganlth.bookmanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_Trangchu extends Fragment {
    TextView tvHelllo, tvTime, tvDay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trangchu,container,false);
        tvHelllo = v.findViewById(R.id.tvHello);
        tvTime = v.findViewById(R.id.tvTime);
        tvDay = v.findViewById(R.id.tvDay);
        ImageSlider slider = v.findViewById(R.id.imgSlider);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        tvDay.setText(dateTime);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm:ss a");
        String dateTime2 = simpleDateFormat2.format(calendar.getTime());
        tvTime.setText(dateTime2);

        Intent i = getActivity().getIntent();
        Bundle bundle1 = i.getExtras();
        String username = bundle1.getString("username");

        if (Calendar.HOUR>12){
            tvHelllo.setText("Hello "+username+" chào buổi chiều!");
        }
        else {
            tvHelllo.setText("Hello "+username+" chào buổi sáng!");
        }

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img6,"", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img2,"", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img4,"", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img1,"", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.img5,"", ScaleTypes.CENTER_CROP));

        slider.setImageList(slideModels,ScaleTypes.CENTER_CROP);
        return v;
    }
}
