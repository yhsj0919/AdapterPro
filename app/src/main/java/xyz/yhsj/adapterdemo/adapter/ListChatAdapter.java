package xyz.yhsj.adapterdemo.adapter;

import android.content.Context;
import android.view.View;

import xyz.yhsj.adapter.BaseListViewAdapter;
import xyz.yhsj.adapterdemo.R;
import xyz.yhsj.adapterdemo.model.ChatModel;
import xyz.yhsj.helper.ViewHolderHelper;

/**
 * Created by LOVE on 2015/12/14.
 */
public class ListChatAdapter extends BaseListViewAdapter<ChatModel> {

    public ListChatAdapter(Context context) {
        super(context, R.layout.item_chat);
    }

    @Override
    protected void bindItemChildEvent(ViewHolderHelper viewHolderHelper) {
    }

    @Override
    protected void bindData(ViewHolderHelper viewHolderHelper, int position, ChatModel model) {
        if (model.mUserType == ChatModel.UserType.From) {
            viewHolderHelper.setVisibility(R.id.rl_item_chat_to, View.GONE);
            viewHolderHelper.setVisibility(R.id.rl_item_chat_from, View.VISIBLE);
            String htmlMsg = String.format(mContext.getString(R.string.color_msg_from), model.mMsg);
            viewHolderHelper.setHtml(R.id.tv_item_chat_from_msg, htmlMsg);
        } else {
            viewHolderHelper.setVisibility(R.id.rl_item_chat_from, View.GONE);
            viewHolderHelper.setVisibility(R.id.rl_item_chat_to, View.VISIBLE);
            String htmlMsg = String.format(mContext.getString(R.string.color_msg_to), model.mMsg);
            viewHolderHelper.setHtml(R.id.tv_item_chat_to_msg, htmlMsg);
        }
    }

}