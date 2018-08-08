package com.individual.kehao.smartbutler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.adapter.WeChatAdapter;
import com.individual.kehao.smartbutler.entity.WeChatData;
import com.individual.kehao.smartbutler.ui.WebviewActivity;
import com.individual.kehao.smartbutler.utils.L;
import com.individual.kehao.smartbutler.utils.StaticClass;
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
 * Description :
 */
public class WechatFragment extends Fragment {

    private ListView mListView;
    private List<WeChatData> mList = new ArrayList<>();

    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, null);

        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView = view.findViewById(R.id.mListView);

        String url = "http://v.juhe.cn/weixin/query?key=" + StaticClass.WECHAT_APP_KEY; //+ "?ps==100"
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
//                Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
//                L.i("json:" + t);
                parsingJson(t);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                L.i("pos" + i);
                Intent intent = new Intent(getActivity(), WebviewActivity.class);

                intent.putExtra("title", mListTitle.get(i));
                intent.putExtra("url", mListUrl.get(i));
//                intent.putExtra("firstImg", mListUrl.get(i));

                startActivity(intent);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                WeChatData data = new WeChatData();

                String title = json.getString("title");
                String url = json.getString("url");
                data.setTitle(title);
                data.setSource(json.getString("source"));
                data.setImgUrl(json.getString("firstImg"));

                mList.add(data);

                mListTitle.add(title);
                mListUrl.add(url);
            }

            WeChatAdapter adapter = new WeChatAdapter(getActivity(), mList);
            mListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
