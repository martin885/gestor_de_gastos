package ar.com.sumo.gestor_de_gastos.Adapters;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import ar.com.sumo.gestor_de_gastos.fragments.GastosFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GastosFragment();
            case 1:
                return new GastosFragment();
            case 2:
                return new GastosFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Ingresos";
            case 1:
                return "Saldo";
            case 2:
                return "Gastos";
            default:
                return null;
        }
    }

}
