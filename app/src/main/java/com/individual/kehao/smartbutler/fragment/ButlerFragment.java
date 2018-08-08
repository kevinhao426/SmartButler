package com.individual.kehao.smartbutler.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.adapter.ChatListAdapter;
import com.individual.kehao.smartbutler.entity.ChatListData;

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
public class ButlerFragment extends Fragment implements View.OnClickListener{
    private ListView mChatListView;
    private Button btn_L, btn_R;

    private List<ChatListData>mList = new ArrayList<>();
    private ChatListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler, null);
        findView(view);
        return view;
    }

    private void findView(View view) {

        mChatListView = view.findViewById(R.id.mChatListView);
        btn_L = view.findViewById(R.id.btn_l);
        btn_L.setOnClickListener(this);
        btn_R = view.findViewById(R.id.btn_r);
        btn_R.setOnClickListener(this);

        adapter = new ChatListAdapter(getActivity(), mList);
        mChatListView.setAdapter(adapter);

        addLeftItem("左");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_l:
                addLeftItem("左");
                break;

            case R.id.btn_r:
                addRightItem("右");
                break;
        }
    }

    private void addLeftItem(String text){
        ChatListData data = new ChatListData();
        data.setType(ChatListAdapter.VALUE_L);
        data.setText(text);
        mList.add(data);
        adapter.notifyDataSetChanged();
        mChatListView.setSelection(mChatListView.getBottom());
    }

    private void addRightItem(String text){
        ChatListData data = new ChatListData();
        data.setType(ChatListAdapter.VALUE_R);
        data.setText(text);
        mList.add(data);
        adapter.notifyDataSetChanged();
        mChatListView.setSelection(mChatListView.getBottom());
    }
}
