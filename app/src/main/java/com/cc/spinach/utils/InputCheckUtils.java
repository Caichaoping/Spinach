package com.cc.spinach.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @title  判断输入的是什么类型文字的工具类
 * @author bocai
 * @date 2016-3-10
 */
public class InputCheckUtils {


	/**
	 * 是否为空
	 */
	public static boolean isEmpty(String input)
	{
		if(input==null || input.length()<=0)
			return true;
		else
			return false;
	}


	/**
	 * 是否为电话号码
	 */
	public static boolean checkInputIsPhoneNumber(String input)
	{
		Pattern pattern = Pattern.compile(PatternUtils.CHECK_IS_PHONE);
		Matcher matcher = pattern.matcher(input);

		if(matcher.matches())
			return true;
		else
			return false;
	}


	/**
	 * 是否为昵称（字母、数字和汉字）;
	 */
	public static boolean checkInputIsConFormNickName(String input)
	{
		Pattern pattern = Pattern.compile(PatternUtils.INPUT_ABC_OR_NUMBER_OR_HANZI);
		Matcher matcher = pattern.matcher(input);

		if(matcher.matches())
			return true;
		else
			return false;
	}


	/**
	 * 判断字符串是否相等
	 */
	public static boolean compareIsEqual(String input1,String input2)
	{
		if(input1==null || input2 == null)
		{
			return false;
		}
		else
		{
			if(input1.equals(input2))
			{
				return true;
			}
		}

		return false;
	}


	/**
	 * 判断字符串长度是否在这个范围内
	 */
	public static boolean checkInputRangeIsConform(String input,int minRange,int maxRange)
	{
		if(input == null)
		{
			return false;
		}

		int length = input.length();

		if(length>=minRange && length <= maxRange)
		{
			return true;
		}

		return false;
	}


	/**
	 * 判断字符串长度是否为range
	 */
	public static boolean checkInputRangeIsConform(String input,int range)
	{
		if(input == null)
		{
			return false;
		}

		if(input.length() == range)
		{
			return true;
		}

		return false;
	}


	/**
	 * 是否中国的手机号
	 */
	public static boolean checkPhoneIsChinaMobile(String input)
	{
		if(input == null)
		{
			return false;
		}

		if(input.matches(PatternUtils.CHECK_PHONE_IS_CHINA_MOBILE))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}
	public static boolean isValidEmail(String mail) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return mail.matches(regex);
	}

	public class PatternUtils {

		public static final String CHECK_IS_PHONE = "^(13|15|18)\\d{9}$";
		public static final String CHECK_PHONE_IS_CHINA_MOBILE = "^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8])[0-9]{8}$";
		public static final String CHECK_PASSWORD_ABC_OR_123 = "^[a-zA-Z]{0,1}+[a-zA-Z0-9]{5,13}$";
		public static final String INPUT_ABC_OR_NUMBER_OR_HANZI = "^[\u4E00-\u9FA5A-Za-z]+[\u4E00-\u9FA5A-Za-z0-9_$]+$";
	}


}
