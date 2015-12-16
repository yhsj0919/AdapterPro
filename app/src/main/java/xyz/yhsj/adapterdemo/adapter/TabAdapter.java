package xyz.yhsj.adapterdemo.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;

import xyz.yhsj.adapter.BaseTabLayoutAdapter;
import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.helper.ViewHolderHelper;

/**
 * Created by LOVE on 2015/12/14.
 */
public class TabAdapter extends BaseTabLayoutAdapter<String> {

    public TabAdapter(Context context, FragmentManager fm) {
        super(context, fm, R.layout.tab_item);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }

    @Override
    protected void bindData(ViewHolderHelper viewHolderHelper, int position, String model) {

        viewHolderHelper.setText(R.id.text, model);
        viewHolderHelper.setText(R.id.text1, position+"");

    }
}
