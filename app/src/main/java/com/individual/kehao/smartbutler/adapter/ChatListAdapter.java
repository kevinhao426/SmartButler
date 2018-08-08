package com.individual.kehao.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.entity.ChatListData;

import java.util.List;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.adapter
 * File    Name: CharListAdapter
 * Create  By:   Ke Hao
 * Create  Time: 2018/8/6
 * Description : chart adapter
 */
public class ChatListAdapter extends BaseAdapter {

    public static final int VALUE_L = 1;
    public static final int VALUE_R = 2;

    private Context mContext;
    private LayoutInflater inflater;
    private ChatListData data;
    private List<ChatListData> mList;

    public ChatListAdapter(Context mContext, List<ChatListData> mList) {
        this.mContext = mContext;
        this.mList = mList;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderLeftText viewHolderLeftText = null;
        ViewHolderRightText viewHolderRightText = null;

        int type = getItemViewType(i);
        if (view == null) {
            switch (type) {
                case VALUE_L:
                    viewHolderLeftText = new ViewHolderLeftText();
                    view = inflater.inflate(R.layout.left_item, null);
                    viewHolderLeftText.tv_L_text = view.findViewById(R.id.tv_L_text);
                    view.setTag(viewHolderLeftText);
                    break;

                case VALUE_R:
                    viewHolderRightText = new ViewHolderRightText();
                    view = inflater.inflate(R.layout.right_item, null);
                    viewHolderRightText.tv_R_text = view.findViewById(R.id.tv_R_text);
                    view.setTag(viewHolderRightText);
                    break;
            }
        } else {
            switch (type) {
                case VALUE_L:
                    viewHolderLeftText = (ViewHolderLeftText) view.getTag();
                    break;

                case VALUE_R:
                    viewHolderRightText = (ViewHolderRightText) view.getTag();
                    break;
            }
        }

        ChatListData data = mList.get(i);

        switch (type) {
            case VALUE_L:
                viewHolderLeftText.tv_L_text.setText(data.getText());
                break;

            case VALUE_R:
                viewHolderRightText.tv_R_text.setText(data.getText());
                break;
        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        ChatListData data = mList.get(position);
        int type = data.getType();
        return type;
    }

    @Override
    public int getViewTypeCount() {

        return 3;
        //mList.size() + 1;
    }

    class ViewHolderLeftText {
        private TextView tv_L_text;
    }

    class ViewHolderRightText {
        private TextView tv_R_text;
    }
}
