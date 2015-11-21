package com.learningplatform.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo1.R;
import com.learningplatfrom.utils.RegisterInfoCheckState;
import com.learningplatfrom.utils.VerificationCode;
public class RegisterActivity extends Activity {
	private Button mRegisterButton = null;//注册按钮
	private Button mReturnButton = null;  //标题栏返回按钮
	private ImageView mCodeImage = null;  //随机产生验证码
	private TextView mCodeText = null;  //更换验证码
	private EditText mCodeInput = null; //输入验证码框
	private EditText mRegisterName = null;
	private EditText mRegisterPasswordFrist = null;
	private EditText mRegisterPasswordSecond = null;
	private EditText mRegisterEmail = null;
	private boolean mRigsterInfoFlag = false;  //标识所有信息是否输入正确
	private int mUserNameState = RegisterInfoCheckState.REGISTER_USERNAME_NULL;
	private int mPasswordFristState = RegisterInfoCheckState.REGISTER_PASSWORDFRIST_NULL;
	private int mPasswordSecondState = RegisterInfoCheckState.REGISTER_PASSWORDSECOND_NULL;
	private int mEmailState = RegisterInfoCheckState.REGISTER_USEREMAIL_NULL;
	private int mVertificationState = RegisterInfoCheckState.REGISTER_VERTIFICATION_NULL;
	private String codeString = null;  //验证码字符串
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		initView();
		readyToPostRegisterInfoToServer();
	}
	public void readyToPostRegisterInfoToServer(){ //按下注册按钮后准备将数据提交给服务器了
		mRegisterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				checkInputRegisterInfoIsRight();
				if(mUserNameState == RegisterInfoCheckState.REGISTER_USERNAME_NULL){
					Toast.makeText(getApplicationContext(), "用户名不能为空",Toast.LENGTH_SHORT).show();return;
				}else if(mUserNameState == RegisterInfoCheckState.REGISTER_USERNAME_INPUT_ERROR){
					Toast.makeText(getApplicationContext(), "输入用户名格式不正确", Toast.LENGTH_SHORT).show();return;
				}
				if(mPasswordFristState == RegisterInfoCheckState.REGISTER_PASSWORDFRIST_NULL){
					Toast.makeText(getApplicationContext(), "密码不能为空",Toast.LENGTH_SHORT).show();return;
				}else if(mPasswordFristState == RegisterInfoCheckState.REGISTER_PASSWORDFRIST_INPUT_ERROR){
					Toast.makeText(getApplicationContext(), "输入密码格式不正确", Toast.LENGTH_SHORT).show();return;
				}
				if(mPasswordSecondState == RegisterInfoCheckState.REGISTER_PASSWORDSECOND_NULL){
					Toast.makeText(getApplicationContext(), "密码不能为空",Toast.LENGTH_SHORT).show();return;
				}else if(mPasswordSecondState == RegisterInfoCheckState.REGISTER_PASSWORDSECOND_INPUT_ERROR){
					Toast.makeText(getApplicationContext(), "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();return;
				}
				if(mEmailState == RegisterInfoCheckState.REGISTER_USEREMAIL_NULL){
					Toast.makeText(getApplicationContext(), "邮箱不能为空",Toast.LENGTH_SHORT).show();return;
				}else if(mEmailState == RegisterInfoCheckState.REGISTER_USEREMAIL_INPUT_ERROR){
					Toast.makeText(getApplicationContext(), "输入邮箱名格式不正确", Toast.LENGTH_SHORT).show();return;
				}
				if(mVertificationState == RegisterInfoCheckState.REGISTER_VERTIFICATION_NULL){
					Toast.makeText(getApplicationContext(), "验证码不能为空",Toast.LENGTH_SHORT).show();return;
				}else if(mVertificationState == RegisterInfoCheckState.REGISTER_VERTIFICATION_ERROR){
					Toast.makeText(getApplicationContext(), "输入验证码不正确", Toast.LENGTH_SHORT).show();return;
				}
			}
		});
	}
	public void checkInputRegisterInfoIsRight(){  //用正则表达式检查输入信息是否正确
		String userName = mRegisterName.getText().toString(); 
		String userPasswordFirst = mRegisterPasswordFrist.getText().toString();
		String userPasswordSecond = mRegisterPasswordSecond.getText().toString();
		String userEmail = mRegisterEmail.getText().toString();  //获得各个编辑框的输入信息
		String mInputCode = mCodeInput.getText().toString();
		Pattern mPattern = Pattern.compile("[a-zA-Z0-9]{8,31}");
		Matcher mMatcher = mPattern.matcher(userName);
		if(userName.equals("")){
			mUserNameState = RegisterInfoCheckState.REGISTER_USERNAME_NULL;
		}else if(!mMatcher.matches()){
			mUserNameState = RegisterInfoCheckState.REGISTER_USERNAME_INPUT_ERROR;
		} else{
			mUserNameState = RegisterInfoCheckState.REGISTER_USEREMAIL_INPUT_RIGHT;
		}
		mPattern = Pattern.compile("[a-zA-Z0-9]{6,31}");
		mMatcher = mPattern.matcher(userPasswordFirst);
		if(userPasswordFirst.equals("")){
			mPasswordFristState = RegisterInfoCheckState.REGISTER_PASSWORDFRIST_NULL;
		}else if(!mMatcher.matches()){
			mPasswordFristState = RegisterInfoCheckState.REGISTER_PASSWORDFRIST_INPUT_ERROR;
		}else{
			mPasswordFristState = RegisterInfoCheckState.REGISTER_PASSWORDFIRST_INPUT_RIGHT;
		}
		if(!userPasswordSecond.equals(userPasswordFirst)){
			mPasswordSecondState = RegisterInfoCheckState.REGISTER_PASSWORDSECOND_INPUT_ERROR;
		}else if(userPasswordSecond.equals("")){
			mPasswordSecondState = RegisterInfoCheckState.REGISTER_PASSWORDSECOND_NULL;
		} else{
			mPasswordSecondState = RegisterInfoCheckState.REGISTER_PASSWORDSECOND_INPUT_RIGHT;
		}
		mPattern = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
		mMatcher = mPattern.matcher(userEmail);
		if(!mMatcher.matches()){
			mEmailState = RegisterInfoCheckState.REGISTER_USEREMAIL_INPUT_ERROR;
		}else if(userEmail.equals("")){
			mEmailState = RegisterInfoCheckState.REGISTER_USEREMAIL_NULL;
		}else{
			mEmailState = RegisterInfoCheckState.REGISTER_USEREMAIL_INPUT_RIGHT;
		}
		if(mInputCode.equals("")){
			mVertificationState = RegisterInfoCheckState.REGISTER_VERTIFICATION_NULL;
		}else if(!mInputCode.equalsIgnoreCase(codeString)){
			mVertificationState = RegisterInfoCheckState.REGISTER_VERTIFICATION_ERROR;
		}else{
			mVertificationState = RegisterInfoCheckState.REGISTER_VERTIFICATION_RIGHT;
		}
	}
	public void initView(){		//初始化视图
		mRegisterButton = (Button)this.findViewById(R.id.register_registerbt);
		mReturnButton = (Button)this.findViewById(R.id.return_button);
		mCodeImage = (ImageView)this.findViewById(R.id.register_vertificationcode_imageview);
		mCodeText = (TextView)this.findViewById(R.id.register_changecode_textview);
		mRegisterName = (EditText)this.findViewById(R.id.register_username_textview);
		mRegisterPasswordFrist = (EditText)this.findViewById(R.id.register_passwordfirst_textview);
		mRegisterPasswordSecond = (EditText)this.findViewById(R.id.register_passwordsecond_textview);
		mCodeInput = (EditText)this.findViewById(R.id.register_inputcode_edittext);
		mRegisterEmail = (EditText)this.findViewById(R.id.register_email_textview);
		mCodeImage.setImageBitmap(VerificationCode.getInstance().createCodeBitmap());
		mReturnButton.setOnClickListener(new Button.OnClickListener(){  //返回主页面
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(RegisterActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		mCodeText.setOnClickListener(new TextView.OnClickListener(){ //点击更换一张验证码后，随机产生一张验证码
			@Override
			public void onClick(View view) {
				VerificationCode mBitmapCode = VerificationCode.getInstance();
				mCodeImage.setImageBitmap(mBitmapCode.createCodeBitmap());
				codeString = mBitmapCode.getRandomCode();
			}
		});
	}
}
