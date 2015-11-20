package com.learningplatform.activity;

import com.example.demo1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private EditText mAccountEdit = null;
	private EditText mPasswordEdit = null;
	private Button mLoginButton = null;
	private Button mRegisterButton = null;
	private TextView mBackPasswordTextView = null;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
    }

    public void initView(){
    	mAccountEdit = (EditText)this.findViewById(R.id.account_input_edittext);
    	mPasswordEdit= (EditText)this.findViewById(R.id.password_input_edittext);
    	mLoginButton = (Button)this.findViewById(R.id.main_bg_login);
    	mRegisterButton = (Button)this.findViewById(R.id.main_bg_register);
    	mBackPasswordTextView = (TextView)this.findViewById(R.id.mian_bg_backpassword_textview);
    	mBackPasswordTextView.setOnClickListener(new TextView.OnClickListener(){
    		@Override
    		public void onClick(View view) {
    			Intent intent = new Intent();
    			intent.setClass(LoginActivity.this, BackPasswordActivity.class);
    			startActivity(intent);
    		}
    	});
    	mRegisterButton.setOnClickListener(new Button.OnClickListener(){
    		@Override
    		public void onClick(View view) {
    			Intent intent = new Intent();
    			intent.setClass(LoginActivity.this, RegisterActivity.class);
    			startActivity(intent);
    		}
    	});
    	mLoginButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
    			intent.setClass(LoginActivity.this, HomeActivity.class);
    			startActivity(intent);
			}
		});
    }
}
