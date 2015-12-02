package com.learningplatfrom.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo1.R;
import com.learningplatfrom.adapter.HorizontalListViewAdapter;
import com.learningplatfrom.component.HorizontalListView;

public class ResourceFragment extends Fragment {
	private String[] mTitle ={"计算机","自动化","电子","文学","航空","技术","语言","其他"};
	private HorizontalListView mHorizontalListView = null;
	private HorizontalListViewAdapter mHorizontalListViewAdapter = null;
	public ResourceFragment() {
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.resource_fragment_layout, container,false);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mHorizontalListView = (HorizontalListView)getView().findViewById(R.id.resource_horizon_listview);
		mHorizontalListViewAdapter = new HorizontalListViewAdapter(getActivity().getApplicationContext(),mTitle);
		mHorizontalListView.setAdapter(mHorizontalListViewAdapter);
	}
}
