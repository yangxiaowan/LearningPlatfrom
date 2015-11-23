package com.learningplatfrom.utils;

public final class RegisterInfoCheckState {
	public static final int REGISTER_USERNAME_NULL = 0;  	//输入注册用户名为空
	public static final int REGISTER_USERNAME_INPUT_ERROR = 1;  	//注册用户名输入格式错误 
	public static final int REGISTER_USERNAME_INPUT_REPEATE = 2; //注册用户名重复
	public static final int REGISTER_USERNAME_INPUT_RIGHT = 3; //注册用户名输入正确
	public static final int REGISTER_PASSWORDFRIST_NULL = 4;		//第一次密码为空
	public static final int REGISTER_PASSWORDFRIST_INPUT_ERROR = 5;	//第一次输入密码格式错误
	public static final int REGISTER_PASSWORDSECOND_NULL = 6;//第二次密码为空
	public static final int REGISTER_PASSWORDSECOND_INPUT_ERROR = 7;	//确认密码输入错误
	public static final int	REGISTER_USEREMAIL_NULL = 8;	//邮箱为空
	public static final int REGISTER_USEREMAIL_INPUT_ERROR = 9;//输入邮箱格式错误
	public static final int REGISTER_USEREMAIL_INPUT_REPEATE = 10;//邮箱已经注册
	public static final int REGISTER_USEREMAIL_INPUT_RIGHT = 11; //邮箱格式输入正确
	public static final int REGISTER_USEREMAIL_INPUT_PASSED = 12; 	//邮箱输入通过
	public static final int REGISTER_VERTIFICATION_NULL = 13;	//验证码为空
	public static final int REGISTER_VERTIFICATION_ERROR = 14; //验证码输入错误
	public static final int REGISTER_VERTIFICATION_RIGHT = 15; //验证码输入正确
	public static final int REGISTER_PASSWORDFIRST_INPUT_RIGHT = 16;  //第一次输入密码正确
	public static final int REGISTER_PASSWORDSECOND_INPUT_RIGHT = 16;  //第二次输入密码正确
}
