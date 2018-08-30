package com.io.ssm.framework.utils;

import java.util.Random;

/**
 * @description:
 * @author: llyong
 * @date: 2018/8/30
 * @time: 10:42
 * @version: 1.0
 */
public class RandomUtils {

    private static final String NUMBER = "0123456789";

    private static final String CHART = "abcdefghijkmnpqrstuvwxyz";

    /**
     * 获取随机长度的数字
     *
     * @param length 长度
     * @return 随机数
     */
    public static String getRandomNumber(int length) {
        char[] chars = NUMBER.toCharArray();
        Random rand = new Random();
        StringBuffer text = new StringBuffer();
        for (int i = 0; i < length; i++) {
            text.append(chars[rand.nextInt(NUMBER.length())]);
        }
        return text.toString();
    }

    /**
     * 获取随机长度的英文字母
     *
     * @param length 长度
     * @return 随机英文字母
     */
    public static String getRandomLetter(int length) {
        char[] chars = CHART.toCharArray();
        Random rand = new Random();
        StringBuffer text = new StringBuffer();
        for (int i = 0; i < length; i++) {
            text.append(chars[rand.nextInt(CHART.length())]);
        }
        return text.toString();
    }


    /**
     * 随机生成指定长度的字母数字字符串
     * @param length
     * @return
     */
    public static String getRandomCode(int length) {
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // 创建一个随机数生成器类
        Random random = new Random();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < length; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        return randomCode.toString();
    }

}
