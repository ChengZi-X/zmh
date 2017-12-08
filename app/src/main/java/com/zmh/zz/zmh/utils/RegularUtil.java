package com.zmh.zz.zmh.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * Created by Administrator
 * 规则
 */

public class RegularUtil {
    // 禁止输入空格和换行
    public static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (source.equals(" ") || source.toString().contentEquals("\n")) return "";
            else return null;
        }
    };

    // 验证手机格式
    public static boolean isMobileNO(String mobiles) {
        /**
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、177（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8或7，其他位置的可以为0-9
		 */
        String telRegex = "[1][3587]\\d{9}";// "[1]"代表第1位为数字1，"[3587]"代表第二位可以为3、5、8、7中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    // 至少包含大小写字母及数字中的两种
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    // QQ邮箱验证
    public static boolean isQQMailbox(String Mailbox) {
        String telRegex = "^[1-9]\\d+@[qQ]{2}\\.com";
        if (TextUtils.isEmpty(Mailbox))
            return false;
        else
            return Mailbox.matches(telRegex);
    }

    // 身份证号验证
    public static boolean isIDCard(String IDCard) {
        String telRegex = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        if (TextUtils.isEmpty(IDCard))
            return false;
        else
            return IDCard.matches(telRegex);
    }


}
