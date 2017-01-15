package info.smemo.nbaseaction.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinUtil {

    public static String getFirstChar(String str) {
        try {
            if (!StringUtil.isEmpty(str)) {
                char c = str.toCharArray()[0];
                if (isChinese(c)) {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
                    if (pinyinArray.length > 0 && pinyinArray[0].length() > 0) {
                        return String.valueOf(pinyinArray[0].toCharArray()[0]).toUpperCase();
                    }
                } else {
                    return String.valueOf(c).toUpperCase();
                }
            }
        } catch (Exception e) {

        }
        return "A";
    }


    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}
