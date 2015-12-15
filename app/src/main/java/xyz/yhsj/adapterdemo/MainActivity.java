package xyz.yhsj.adapterdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import xyz.yhsj.adapterdemo.adapter.TabAdapter;
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

        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(2);
    }

    private void tabInit() {

        TabAdapter adapter = new TabAdapter(this, getSupportFragmentManager());

        tabLayoutHelper.bindTab(tabLayout, viewPager,adapter);

        tabLayoutHelper.addFragment(new Fragment_Test(), "待办");
        tabLayoutHelper.addFragment(new Fragment_Test(), "消息");
        tabLayoutHelper.addFragment(new Fragment_Test(), "联系人");
        tabLayoutHelper.addFragment(new Fragment_Test(), "我的");
        tabLayoutHelper.removeFragment("我的");

    }


}
