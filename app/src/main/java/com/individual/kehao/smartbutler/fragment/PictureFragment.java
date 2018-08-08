package com.individual.kehao.smartbutler.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.adapter.GridAdapter;
import com.individual.kehao.smartbutler.entity.PicData;
import com.individual.kehao.smartbutler.utils.L;
import com.individual.kehao.smartbutler.utils.PicassoUtils;
import com.individual.kehao.smartbutler.utils.StaticClass;
import com.individual.kehao.smartbutler.view.CustomDialog;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 * Project Name: smartbutler
 * Package Name: com.individual.kehao.smartbutler.fragment
 * File    Name: BulterFragment
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/24
 * Description : In this fragment, pictures will be loaded from a specific url defined in StaticClass, in a grid view way.
 *               when touch a picture, it will be previewed in a image view showed as a Custom Dialog.
 *
 *               tip: when using PhotoView to do Zooming functions, define the view as PhotoView in layout file,
 *               then find its Id for an ImageView.
 */
public class PictureFragment extends Fragment {

    private GridView mGridView;
    private List<PicData> mList = new ArrayList<>();
    private GridAdapter mAdapter;
    private CustomDialog dialog;
    //preview pic
    private ImageView iv_img;

    private List<String>mListUrl = new ArrayList<>();
    
    private WindowManager windowManager;
    private int width, height;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mGridView = view.findViewById(R.id.mGridView);

        windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();

        dialog = new CustomDialog(getActivity(), width/2,height/2, R.layout.dialog_pic, R.style.Theme_dialog, Gravity.CENTER);

        iv_img = dialog.findViewById(R.id.photoview);
//        L.i("Json");
        RxVolley.get(StaticClass.PIC_URL, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
//                L.i("Json" + t);
                parsingJson(t);
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PicassoUtils.loadImaheView(getActivity(), mListUrl.get(i), iv_img);

                dialog.show();
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String url = json.getString("url");
                mListUrl.add(url);

                PicData data = new PicData();
                data.setImgUrl(url);
//                L.i("URL:" + url);
                mList.add(data);
            }
            mAdapter = new GridAdapter(getActivity(), mList);
            mGridView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
