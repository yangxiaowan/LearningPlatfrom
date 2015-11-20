package com.learningplatfrom.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo1.R;

public class SchoolFragment extends Fragment {

	public SchoolFragment() {
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.school_fragment_layout, container,false);
	}
}
