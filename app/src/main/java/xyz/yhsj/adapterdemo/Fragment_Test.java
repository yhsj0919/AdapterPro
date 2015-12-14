package xyz.yhsj.adapterdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment_Test extends Fragment {

    public Fragment_Test() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_test, container, false);

        init(v);

        return v;

    }

    private void init(View v) {

    }
}
