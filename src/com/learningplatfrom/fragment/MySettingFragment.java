package com.learningplatfrom.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.demo1.R;
import com.learningplatform.activity.MyInformationActivity;

public class MySettingFragment extends Fragment {
	private ImageView mHeadImageView = null;
	public MySettingFragment() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.mysetting_fragment_layout, container,false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mHeadImageView = (ImageView)getView().findViewById(R.id.mysetting_headimage_imageview);
		mHeadImageView.setOnClickListener(mHeadImageViewClickListener);
	}
	private View.OnClickListener mHeadImageViewClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(getActivity().getApplicationContext(), MyInformationActivity.class);
			startActivity(intent);
		}
	};
}
