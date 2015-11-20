package com.learningplatform.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.learningplatfrom.utils.VerificationCode;
import com.example.demo1.R;
public class RegisterActivity extends Activity {
	private Button mReturnButton = null;
	private ImageView mCodeImage = null;  //随机产生验证码
	private TextView mCodeText = null;
	private EditText mCodeInput = null; //输入验证码框
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		initView();
	}
	public void initView(){
		mReturnButton = (Button)this.findViewById(R.id.return_button);
		mCodeImage = (ImageView)this.findViewById(R.id.register_vertificationcode_imageview);
		mCodeText = (TextView)this.findViewById(R.id.register_changecode_textview);
		mCodeImage.setImageBitmap(VerificationCode.getInstance().createCodeBitmap());
		mReturnButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(RegisterActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		mCodeText.setOnClickListener(new TextView.OnClickListener(){
			@Override
			public void onClick(View view) {
				mCodeImage.setImageBitmap(VerificationCode.getInstance().createCodeBitmap());
			}
		});
	}
}
