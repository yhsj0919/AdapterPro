package xyz.yhsj.adapterdemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.adapter.TabAdapter;
import xyz.yhsj.adapterdemo.ui.fragment.Fragment_Test;
import xyz.yhsj.adapterdemo.ui.fragment.GridViewDemoFragment;
import xyz.yhsj.adapterdemo.ui.fragment.ListViewDemoFragment;
import xyz.yhsj.adapterdemo.ui.fragment.RecyclerViewDemoFragment;
import xyz.yhsj.helper.TabLayoutHelper;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TabLayoutHelper<String> tabLayoutHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayoutHelper = new TabLayoutHelper<String>();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabInit();

    }

    private void tabInit() {

        TabAdapter adapter = new TabAdapter(this, getSupportFragmentManager());

        tabLayoutHelper.bindTab(tabLayout, viewPager, adapter);

        tabLayoutHelper.addFragment(new GridViewDemoFragment(), "GridView");
        tabLayoutHelper.addFragment(new ListViewDemoFragment(), "ListView");
        tabLayoutHelper.addFragment(new RecyclerViewDemoFragment(), "RecyclerView");

        viewPager.setOffscreenPageLimit(2);
    }


}
