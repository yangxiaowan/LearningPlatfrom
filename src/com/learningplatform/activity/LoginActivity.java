package com.learningplatform.activity;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo1.R;
import com.learningplatfrom.consts.ServerServletURL;

public class LoginActivity extends Activity {  //登陆界面LoginActivity
	private EditText mAccountEdit = null;
	private EditText mPasswordEdit = null;
	private Button mLoginButton = null;
	private Button mRegisterButton = null;
	private TextView mBackPasswordTextView = null;
	private String checkLoginAccount = null;
	private String checkLoginPassword = null;
    private Button.OnClickListener loginButtonListener = new Button.OnClickListener(){
		@Override
		public void onClick(View view) {
			if(!CheckInputInformation()){
				return;
			}else{
				new AsyncTask<String, Float, Boolean>() {
					private int resutlCode = 0;
					@Override
					protected Boolean doInBackground(String... params) {
						URL url = null;
						try {
							url = new URL(params[0]);  //用servlet的url设置url
						} catch (MalformedURLException e) {
							e.printStackTrace();
							return false;
						}
						HttpURLConnection httpConnection = null;
						try {
							httpConnection = (HttpURLConnection) url.openConnection();
						} catch (IOException e) {
							e.printStackTrace();
							return false;
						}
						httpConnection.setConnectTimeout(3000);
						httpConnection.setDoInput(true);
						httpConnection.setDoOutput(true);
						try {
							httpConnection.setRequestMethod("POST");
						} catch (ProtocolException e) {
							e.printStackTrace();
							return false;
						}
						httpConnection.setRequestProperty("Content-Type",
								"application/x-www-form-urlencoded");
						httpConnection.setRequestProperty("login_name",
								params[1]);
						httpConnection.setRequestProperty("login_password",
								params[2]);
						try {
							httpConnection.connect();
							int response = httpConnection.getResponseCode();
							if(response == HttpURLConnection.HTTP_ACCEPTED){  //链接服务器成功 返回CODE = 200 Accepted
								Log.i("login_servlet", "登陆服务器成功");
								InputStream is = httpConnection.getInputStream();
								DataInputStream ds = new DataInputStream(is);
								resutlCode = ds.readInt();
								Log.i("login_servlet", "注册结果码为： "
										+ resutlCode);
								ds.close();
								is.close();
								return true;
							}else{
								Log.i("login_servlet","登陆服务器失败");
								return false;
							}
						} catch (IOException e) {
							e.printStackTrace();
							Log.i("login_servlet","登陆服务器异常");
							return false;
						}
					}
					@Override
					protected void onPostExecute(Boolean result) {
						
					}
				}.execute(ServerServletURL.LOGIN_SERVLET_LOCATION, checkLoginAccount,
						checkLoginPassword);
			}
		}
    };
    public boolean CheckInputInformation(){
    	checkLoginAccount = mAccountEdit.getText().toString();
    	checkLoginPassword = mAccountEdit.getText().toString();
    	Pattern mPattern = Pattern.compile("[a-zA-Z0-9]{8,31}");
		Matcher mMatcher = mPattern.matcher(checkLoginAccount);
		if(checkLoginAccount == ""){
			Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_LONG).show();
			return false;
		}else if(!mMatcher.matches()){
			Toast.makeText(getApplicationContext(), "您输入的用户名不符合规范", Toast.LENGTH_LONG).show();
			return false;
		}else if(checkLoginPassword == ""){
			Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
    }
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
    	mLoginButton.setOnClickListener(loginButtonListener);
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
    	/*mLoginButton.setOnClickListener(new Button.OnClickListener() {  //登陆按钮的响应
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
    			intent.setClass(LoginActivity.this, HomeActivity.class);
    			startActivity(intent);
			}
		});*/
    }
}
