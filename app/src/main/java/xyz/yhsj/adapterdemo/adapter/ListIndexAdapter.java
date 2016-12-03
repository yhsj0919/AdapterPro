package xyz.yhsj.adapterdemo.adapter;

import android.content.Context;
import android.view.View;


import xyz.yhsj.adapter.BaseAdapterViewAdapter;
import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.model.IndexModel;
import xyz.yhsj.helper.ViewHolderHelper;

/**
 * Created by LOVE on 2015/12/14.
 */
public class ListIndexAdapter extends BaseAdapterViewAdapter<IndexModel> {

    public ListIndexAdapter(Context context) {
        super(context, R.layout.item_indexview);
    }

    @Override
    protected void bindItemChildEvent(ViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_indexview_name);
    }


    public int getSectionForPosition(int position) {
        return mData.get(position).topc.charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mData.get(i).topc;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void bindData(ViewHolderHelper viewHolderHelper, int position, IndexModel model) {
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            viewHolderHelper.setVisibility(R.id.tv_item_indexview_catalog, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_item_indexview_catalog, model.topc);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_item_indexview_catalog, View.GONE);
        }
        viewHolderHelper.setText(R.id.tv_item_indexview_name, model.name);
    }
}