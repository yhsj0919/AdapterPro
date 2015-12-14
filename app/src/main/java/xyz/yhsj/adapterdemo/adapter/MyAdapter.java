package xyz.yhsj.adapterdemo.adapter;

import android.support.v7.widget.RecyclerView;

import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.model.User;
import xyz.yhsj.adapter.BaseRecyclerViewAdapter;
import xyz.yhsj.viewholder.ViewHolderHelper;

/**
 * Created by LOVE on 2015/12/11.
 */
public class MyAdapter extends BaseRecyclerViewAdapter<User> {

    public MyAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_user);
    }

    @Override
    protected void bindData(ViewHolderHelper viewHolderHelper, int position, User model) {
        viewHolderHelper.setText(R.id.name,  model.getName());
        viewHolderHelper.setText(R.id.age,  model.getAge());
    }
}
