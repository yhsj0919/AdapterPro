package xyz.yhsj.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import xyz.yhsj.helper.ViewHolderHelper;
import xyz.yhsj.viewholder.TabLayoutViewHolder;

/**
 * TabLayout适配器
 *
 * Created by LOVE on 2015/12/14.
 */
public abstract class BaseTabLayoutAdapter<T> extends FragmentStatePagerAdapter {

    private Context mContext;

    protected final int mItemLayoutId;

    private List<Fragment> mFragments = new ArrayList<>();

    protected List<T> mDatas;


    public BaseTabLayoutAdapter(Context context, FragmentManager fm, int mItemLayoutId) {
        super(fm);
        this.mContext = context;
        this.mItemLayoutId = mItemLayoutId;
        mDatas = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, T newData) {
        mFragments.add(fragment);
        mDatas.add(newData);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position).toString();
    }

    public View getTabView(int position) {

        final TabLayoutViewHolder viewHolder = TabLayoutViewHolder.dequeueReusableAdapterViewHolder(mContext, mItemLayoutId);
        viewHolder.getViewHolderHelper().setPosition(position);

        return bindData(viewHolder.getViewHolderHelper(), position, mDatas.get(position));
    }


    /**
     * 填充item数据
     *
     * @param viewHolderHelper
     * @param position
     * @param model
     */
    protected abstract View bindData(ViewHolderHelper viewHolderHelper, int position, T model);


}
