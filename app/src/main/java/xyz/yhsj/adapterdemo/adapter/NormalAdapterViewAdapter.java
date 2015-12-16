package xyz.yhsj.adapterdemo.adapter;

import android.content.Context;

import xyz.yhsj.adapter.BaseListViewAdapter;
import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.model.NormalModel;
import xyz.yhsj.helper.ViewHolderHelper;


/**
 * Created by LOVE on 2015/12/14.
 */
public class NormalAdapterViewAdapter extends BaseListViewAdapter<NormalModel> {
    private boolean mIsIgnoreChange = false;

    public NormalAdapterViewAdapter(Context context) {
        super(context, R.layout.item_normal);
    }

    @Override
    protected void setItemChildListener(ViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
        viewHolderHelper.setItemChildCheckedChangeListener(R.id.cb_item_normal_status);
    }

    @Override
    public void bindData(ViewHolderHelper viewHolderHelper, int position, NormalModel model) {
        viewHolderHelper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
        // 在设置值的过程中忽略选中状态变化
        mIsIgnoreChange = true;
        viewHolderHelper.setChecked(R.id.cb_item_normal_status, model.selected);
        mIsIgnoreChange = false;
    }

    public boolean isIgnoreChange() {
        return mIsIgnoreChange;
    }
}