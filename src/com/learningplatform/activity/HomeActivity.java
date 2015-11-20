package com.learningplatform.activity;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.demo1.R;
public class HomeActivity extends FragmentActivity implements LinearLayout.OnClickListener{
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private Fragment[] mFragments;
	private ImageButton mResourceButton = null;
	private ImageButton mFriendsButton = null;
	private ImageButton mDynamicButton = null;
	private ImageButton mSchoolButton = null;
	private ImageButton mMySettingButton = null;
	private List<ImageButton> mListButton = null;
	private LinearLayout mResourceLayout = null;
	private LinearLayout mDynamicLayout = null;
	private LinearLayout mFriendsLayout = null;
	private LinearLayout mSchoolLayout = null;
	private LinearLayout mMysettingLayout = null;
	public static final int[] mClickedButtonResource = {
		R.drawable.home_resource_clicked,R.drawable.home_dynamic_clicked,R.drawable.home_friends_clicked,
		R.drawable.home_school_clicked,R.drawable.home_mysetting_clicked
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		initView();
		initFragment();
	}
	public void initView(){
		mResourceButton = (ImageButton)this.findViewById(R.id.home_resource_button);
		mDynamicButton = (ImageButton)this.findViewById(R.id.home_dynamic_button);
		mFriendsButton = (ImageButton)this.findViewById(R.id.home_friends_button);
		mSchoolButton = (ImageButton)this.findViewById(R.id.home_school_button);
		mMySettingButton = (ImageButton)this.findViewById(R.id.home_mysetting_button);
		mResourceLayout = (LinearLayout)this.findViewById(R.id.home_resource_buttonlayout);
		mDynamicLayout = (LinearLayout)this.findViewById(R.id.home_dynamic_buttonlayout);
		mFriendsLayout = (LinearLayout)this.findViewById(R.id.home_friends_buttonlayout);
		mSchoolLayout = (LinearLayout)this.findViewById(R.id.home_school_buttonlayout);
		mMysettingLayout = (LinearLayout)this.findViewById(R.id.home_mysetting_buttonlayout);
		mResourceLayout.setOnClickListener(this);
		mDynamicLayout.setOnClickListener(this);
		mFriendsLayout.setOnClickListener(this);
		mSchoolLayout.setOnClickListener(this);
		mMysettingLayout.setOnClickListener(this);
		mListButton = new ArrayList<ImageButton>();
		mListButton.add(mResourceButton);
		mListButton.add(mDynamicButton);
		mListButton.add(mFriendsButton);
		mListButton.add(mSchoolButton);
		mListButton.add(mMySettingButton);
	}
	public void initFragment(){
		mFragments = new Fragment[5];
		fragmentManager = getSupportFragmentManager();
		mFragments[0] = fragmentManager.findFragmentById(R.id.fragement_resource);
		mFragments[1] = fragmentManager.findFragmentById(R.id.fragement_dynamic);
		mFragments[2] = fragmentManager.findFragmentById(R.id.fragement_friends);
		mFragments[3] = fragmentManager.findFragmentById(R.id.fragement_school);
		mFragments[4] = fragmentManager.findFragmentById(R.id.fragement_mysetting);
		fragmentTransaction = fragmentManager.beginTransaction()
				.hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2])
				.hide(mFragments[3]).hide(mFragments[4]);
		fragmentTransaction.show(mFragments[0]).commit();
	}
	@Override
	public void onClick(View view) {
		fragmentTransaction = fragmentManager.beginTransaction()
				.hide(mFragments[0]).hide(mFragments[1])
				.hide(mFragments[2]).hide(mFragments[3])
				.hide(mFragments[4]);
		switch (view.getId()) {
		case R.id.home_resource_buttonlayout:
			resetButtonBackgruondResource(0);
			fragmentTransaction.show(mFragments[0]).commit();
			break;
		case R.id.home_dynamic_buttonlayout:
			resetButtonBackgruondResource(1);
			fragmentTransaction.show(mFragments[1]).commit();
			break;
		case R.id.home_friends_buttonlayout:
			resetButtonBackgruondResource(2);
			fragmentTransaction.show(mFragments[2]).commit();
			break;
		case R.id.home_school_buttonlayout:
			resetButtonBackgruondResource(3);
			fragmentTransaction.show(mFragments[3]).commit();
			break;
		case R.id.home_mysetting_buttonlayout:
			resetButtonBackgruondResource(4);
			fragmentTransaction.show(mFragments[4]).commit();
			break;
		default:
			break;
		}
	}
	public void resetButtonBackgruondResource(int position){
		mResourceButton.setBackgroundResource(R.drawable.home_resource_normal);
		mDynamicButton.setBackgroundResource(R.drawable.home_dynamic_normal);
		mFriendsButton.setBackgroundResource(R.drawable.home_friends_normal);
		mSchoolButton.setBackgroundResource(R.drawable.home_school_normal);
		mMySettingButton.setBackgroundResource(R.drawable.home_mysetting_normal);
		mListButton.get(position).setBackgroundResource(mClickedButtonResource[position]);
	}
	
}
