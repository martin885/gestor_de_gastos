package ar.com.sumo.gestor_de_gastos;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ar.com.sumo.gestor_de_gastos.Adapters.ViewPagerAdapter;
import ar.com.sumo.gestor_de_gastos.fragments.GastosFragment;

public class MainActivity extends AppCompatActivity {

    GastosFragment gastosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = (ViewPager) findViewById(R.id.frameLayout);
        ViewPagerAdapter myPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(2);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Ingresos"));
        tabLayout.addTab(tabLayout.newTab().setText("Saldo"));
        tabLayout.addTab(tabLayout.newTab().setText("Gastos"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
//        tabLayout.getTabAt(2).select();
        tabLayout.setupWithViewPager(viewPager);

        gastosFragment = new GastosFragment();
        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
//                switch (viewPager.getCurrentItem()) {
//                    case 0:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, gastosFragment);
//                }
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(), tab.getText(), Toast.LENGTH_SHORT).show();
                String text = tab.getText().toString();
                switch (text) {
                    case "Ingresos":
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIngresos));
                        break;
                    case "Saldo":
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorSaldo));
                        break;
                    case "Gastos":
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorGastosBotones));

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date= Calendar.getInstance().getTime();
        String today = formatter.format(date);
        Log.d("FECHA", today);
    }
}
