package xyz.yhsj.adapterdemo.adapter;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;

import xyz.yhsj.adapter.BaseRecyclerViewAdapter;
import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.model.NormalModel;
import xyz.yhsj.helper.ViewHolderHelper;


/**
 * Created by LOVE on 2015/12/14.
 */
public class NormalRecyclerViewAdapter extends BaseRecyclerViewAdapter<NormalModel> {
    private ItemTouchHelper mItemTouchHelper;
    private boolean mIsIgnoreChange = true;

    public NormalRecyclerViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_normal, R.layout.item_normal2);
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        mItemTouchHelper = itemTouchHelper;
    }

    @Override
    public void bindItemChildEvent(final ViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
        viewHolderHelper.setItemChildCheckedChangeListener(R.id.cb_item_normal_status);
        viewHolderHelper.getView(R.id.iv_item_normal_avator).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mItemTouchHelper.startDrag(viewHolderHelper.getRecyclerViewHolder());
                }
                return false;
            }
        });
    }

    @Override
    public void bindData(ViewHolderHelper viewHolderHelper, int position, NormalModel model) {

        viewHolderHelper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);

        // 在设置值的过程中忽略选中状态变化
        mIsIgnoreChange = true;
        viewHolderHelper.setChecked(R.id.cb_item_normal_status, model.selected);
        mIsIgnoreChange = false;
    }


    @Override
    protected int bindType(int position, NormalModel model) {

        return (position % 2);
    }

    public boolean isIgnoreChange() {
        return mIsIgnoreChange;
    }
}