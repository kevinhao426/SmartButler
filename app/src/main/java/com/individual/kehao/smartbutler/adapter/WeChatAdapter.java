package com.individual.kehao.smartbutler.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.entity.WeChatData;
import com.individual.kehao.smartbutler.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.adapter
 * File    Name: WeChatAdapter
 * Create  By:   Ke Hao
 * Create  Time: 2018/8/7
 * Description :
 */
public class WeChatAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WeChatData> mList;
    private WeChatData data;
    private int width, height;
    private WindowManager windowManager;


    public WeChatAdapter(Context mContext, List<WeChatData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.wechat_item, null);
            viewHolder.iv_img = view.findViewById(R.id.iv_img);
            viewHolder.tv_title = view.findViewById(R.id.tv_title);
            viewHolder.tv_source = view.findViewById(R.id.tv_source);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        data = mList.get(i);

        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_source.setText(data.getSource());

//        if(TextUtils.isEmpty(data.getImgUrl())){
//            PicassoUtils.loadImageViewSize(mContext,"http://i.imgur.com/DvpvklR.png", 300,200, viewHolder.iv_img);
//        }else {
//            PicassoUtils.loadImageViewSize(mContext, data.getImgUrl(), 300,200, viewHolder.iv_img);
//        }

        PicassoUtils.loadImageViewSize(mContext, data.getImgUrl(), width/3,230, viewHolder.iv_img);

        return view;
    }

    class ViewHolder {
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_source;

    }
}
