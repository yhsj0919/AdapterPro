package xyz.yhsj.adapterdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import xyz.yhsj.adapter.BaseRecyclerViewAdapter;
import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.model.IndexModel;
import xyz.yhsj.helper.ViewHolderHelper;

/**
 * Created by LOVE on 2015/12/14.
 */
public class RecyclerIndexAdapter extends BaseRecyclerViewAdapter<IndexModel> {
    public RecyclerIndexAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_indexview);
    }

    @Override
    public void setItemChildListener(ViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_indexview_name);
    }

    @Override
    public void bindData(ViewHolderHelper viewHolderHelper, int position, IndexModel model) {
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            viewHolderHelper.setVisibility(R.id.tv_item_indexview_catalog, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_item_indexview_catalog, model.topc);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_item_indexview_catalog, View.GONE);
        }
        viewHolderHelper.setText(R.id.tv_item_indexview_name, model.name);
    }

    public int getSectionForPosition(int position) {
        return mDatas.get(position).topc.charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mDatas.get(i).topc;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}