package com.learningplatform.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo1.R;
import com.learningplatfrom.utils.VerificationCode;

public class BackPasswordActivity extends Activity {
	private Button mReturnButton = null;
	private ImageView mVertificationImageView = null;
	private TextView mChangeCode = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.backpassword_layout);
		initView();
	}
	public void initView(){
		mReturnButton = (Button)this.findViewById(R.id.backpassword_return_button);
		mVertificationImageView = (ImageView)this.findViewById(R.id.backpassword_vertificationcode_imageview);
		mVertificationImageView.setImageBitmap(VerificationCode.getInstance().createCodeBitmap());
		mChangeCode = (TextView)this.findViewById(R.id.backpassword_changecode_textview);
		mReturnButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(BackPasswordActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		mChangeCode.setOnClickListener(new TextView.OnClickListener(){
			@Override
			public void onClick(View view) {
				mVertificationImageView.setImageBitmap(VerificationCode.getInstance().createCodeBitmap());
			}
		});
	}
}
