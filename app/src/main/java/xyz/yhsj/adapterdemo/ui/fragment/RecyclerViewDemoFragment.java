package xyz.yhsj.adapterdemo.ui.fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.adapter.NormalRecyclerViewAdapter;
import xyz.yhsj.adapterdemo.model.NormalModel;
import xyz.yhsj.event.OnItemChildCheckedChangeListener;
import xyz.yhsj.event.OnItemChildClickListener;
import xyz.yhsj.event.OnItemChildLongClickListener;
import xyz.yhsj.event.OnItemClickListener;
import xyz.yhsj.event.OnItemLongClickListener;


/**
 * Created by LOVE on 2015/12/14.
 */
public class RecyclerViewDemoFragment extends Fragment implements OnItemClickListener,
        OnItemLongClickListener, OnItemChildClickListener, OnItemChildLongClickListener, OnItemChildCheckedChangeListener {

    private RecyclerView recyclerView;
    private NormalRecyclerViewAdapter mAdapter;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_recyclerview_data);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback());

        mItemTouchHelper.attachToRecyclerView(recyclerView);

        mAdapter = new NormalRecyclerViewAdapter(recyclerView);

        mAdapter.setItemTouchHelper(mItemTouchHelper);

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);
        mAdapter.setOnItemChildCheckedChangeListener(this);


        List<NormalModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new NormalModel("title:" + i, "" + i, "" + i));

        }

        mAdapter.setData(list);


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

    @Override
    public void onItemClick(ViewGroup parent, View itemView, int position) {
        Toast.makeText(getActivity(), "点击了条目 " + mAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View itemView, int position) {
        Toast.makeText(getActivity(), "长按了条目 " + mAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * 该类参考：https://github.com/iPaulPro/Android-ItemTouchHelper-Demo
     */
    private class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
        public static final float ALPHA_FULL = 1.0f;

        @Override
        public boolean isLongPressDragEnabled() {
//          return true;
            return false;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
            if (source.getItemViewType() != target.getItemViewType()) {
                return false;
            }

            mAdapter.moveItem(source.getAdapterPosition(), target.getAdapterPosition());

//            for (NormalModel normalModel : mAdapter.getDatas()) {
//                Log.i(">>>>>>>>>", normalModel.title);
//            }

            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mAdapter.removeItem(viewHolder.getAdapterPosition());
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                View itemView = viewHolder.itemView;
                float alpha = ALPHA_FULL - Math.abs(dX) / (float) itemView.getWidth();
                ViewCompat.setAlpha(viewHolder.itemView, alpha);
            }
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setSelected(true);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            ViewCompat.setAlpha(viewHolder.itemView, ALPHA_FULL);
            viewHolder.itemView.setSelected(false);
        }
    }
}