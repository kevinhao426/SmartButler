package com.individual.kehao.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.entity.PicData;
import com.individual.kehao.smartbutler.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.adapter
 * File    Name: GridAdapter
 * Create  By:   Ke Hao
 * Create  Time: 2018/8/8
 * Description : adapter for pic fragment
 */
public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private List<PicData> mList;
    private LayoutInflater inflater;
    private PicData data;
    private WindowManager windowManager;
    private int width;

    public GridAdapter(Context mContext, List<PicData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //get screen width
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
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

        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.pic_item, null);
            viewHolder.imageView = view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        data = mList.get(i);
        String url = data.getImgUrl();

        PicassoUtils.loadImageViewSize(mContext, url, width/2, 400, viewHolder.imageView);

        return view;
    }

    class ViewHolder{
        private ImageView imageView;
    }
}
