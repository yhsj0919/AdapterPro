package xyz.yhsj.adapterdemo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.adapter.NormalAdapterViewAdapter;
import xyz.yhsj.adapterdemo.model.NormalModel;
import xyz.yhsj.event.OnItemChildCheckedChangeListener;
import xyz.yhsj.event.OnItemChildClickListener;
import xyz.yhsj.event.OnItemChildLongClickListener;


/**
 * Created by LOVE on 2015/12/14.
 */
public class ListViewDemoFragment extends Fragment implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, OnItemChildClickListener, OnItemChildLongClickListener, OnItemChildCheckedChangeListener {

    private ListView listView;
    private NormalAdapterViewAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        listView = (ListView) v.findViewById(R.id.lv_listview_data);
        mAdapter = new NormalAdapterViewAdapter(getActivity());
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);
        mAdapter.setOnItemChildCheckedChangeListener(this);


        List<NormalModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            list.add(new NormalModel("title:" + i, "" + i, "" + i));

        }

        mAdapter.setDatas(list);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "点击了条目 " + mAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "长按了条目 " + mAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_item_normal_delete) {
            mAdapter.removeItem(position);
        }
    }

    @Override
    public boolean onItemChildLongClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_item_normal_delete) {
            Toast.makeText(getActivity(), "长按了删除 " + mAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onItemChildCheckedChanged(ViewGroup parent, CompoundButton childView, int position, boolean isChecked) {
        // 在填充数据列表时，忽略选中状态变化
        if (!mAdapter.isIgnoreChange()) {
            mAdapter.getItem(position).selected = isChecked;
            if (isChecked) {
                Toast.makeText(getActivity(), "选中 " + mAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "取消选中 " + mAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
            }
        }
    }
}