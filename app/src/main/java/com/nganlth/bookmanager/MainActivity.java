package com.nganlth.bookmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.nganlth.bookmanager.Fragment.Fragment_Gioithieu;
import com.nganlth.bookmanager.Fragment.Fragment_Sach;
import com.nganlth.bookmanager.Fragment.Fragment_Theloai;
import com.nganlth.bookmanager.Fragment.Fragment_Hoadon;
import com.nganlth.bookmanager.Fragment.Fragment_Thongke;
import com.nganlth.bookmanager.Fragment.Fragment_Trangchu;
import com.nganlth.bookmanager.Fragment.Fragment_User;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dr_ly;
    Toolbar tb;
    NavigationView nv;
    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));
        dr_ly = findViewById(R.id.dr_ly);
        tb = findViewById(R.id.tg_bar);
        nv = findViewById(R.id.nv_view);
        dr_ly.addDrawerListener(drawerToggle);
        drawerToggle = setupDrawableToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null){
            nv.setCheckedItem(R.id.trangchu);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Trangchu()).commit();
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                Class fragmentClass = null;
                switch (item.getItemId()){
                    case R.id.trangchu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Trangchu()).commit();
                        break;
                    case R.id.sach:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Sach()).commit();
                        break;
                    case R.id.theLoai:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Theloai()).commit();
                        break;
                    case R.id.nguoidung:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_User()).commit();
                        break;
                    case R.id.thongke:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Thongke()).commit();
                        break;
                    case R.id.hoadon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Hoadon()).commit();
                        break;
                    case R.id.gioithieu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Gioithieu()).commit();
                        break;
                    case R.id.thoat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Hoadon()).commit();
                        Toast.makeText(MainActivity.this, "Hẹn gặp lại!", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Hoadon()).commit();
                }

                item.setChecked(true);
                setTitle(item.getTitle());
                dr_ly.closeDrawers();
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawableToggle(){
        return new ActionBarDrawerToggle(MainActivity.this, dr_ly, tb,R.string.Open, R.string.Close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.dangXuat:
                finish();
                return true;
            case R.id.doiMatKhau:
                Intent i = new Intent(MainActivity.this,ChangePassActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}