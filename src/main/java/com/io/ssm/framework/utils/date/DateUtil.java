package com.io.ssm.framework.utils.date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 时间操作工具类
 * @author: llyong
 * @date: 2018/9/14
 * @time: 14:38
 * @version: 1.0
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * date format yyyy
     */
    private static final String DATE_FORMAT_1 = "yyyy";
    /**
     * date format yyyyMM
     */
    private static final String DATE_FORMAT_2 = "yyyyMM";
    /**
     * date format yyyyMMdd
     */
    private static final String DATE_FORMAT_3 = "yyyyMMdd";
    /**
     * date format yyyy年MM月
     */
    private static final String DATE_FORMAT_4 = "yyyy年MM月";
    /**
     * date format yyyy年MM月dd日
     */
    private static final String DATE_FORMAT_5 = "yyyy年MM月dd日";

    /**
     * time format HHmmss
     */
    private static final String TIME_FORMAT_1 = "HHmmss";
    /**
     * time format HHmm
     */
    private static final String TIME_FORMAT_2 = "HHmm";


    /**
     * datetime format yyyyMMddHHmmssSSS
     */
    private static final String DATE_TIME_FORMAT_1 = "yyyyMMddHHmmssSSS";
    /**
     * datetime format yyyyMMddHHmmss
     */
    private static final String DATE_TIME_FORMAT_2 = "yyyyMMddHHmmss";
    /**
     * datetime format yyyyMMddHHmm
     */
    private static final String DATE_TIME_FORMAT_3 = "yyyyMMddHHmm";
    /**
     * datetime format yyyy-MM-dd HH:mm:ss
     */
    private static final String DATE_TIME_FORMAT_4 = "yyyy-MM-dd HH:mm:ss";
    /**
     * datetime format yyyy-MM-dd HH:mm
     */
    private static final String DATE_TIME_FORMAT_5 = "yyyy-MM-dd HH:mm";
    /**
     * datetime format yyyy年MM月dd日 HH时mm分ss秒
     */
    private static final String DATE_TIME_FORMAT_6 = "yyyy年MM月dd日 HH时mm分ss秒";

    private static SimpleDateFormat simpleDateFormat;

    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String DateToString(Date source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }

    /**
     *
     * unix时间戳转为指定格式的String类型
     *
     *
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String timeStampToString(long source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source * 1000);
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期转换为时间戳(unix时间戳,单位秒)
     *
     * @param date
     * @return
     */
    public static long dateToTimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime() / 1000;

    }

    /**
     *
     * 字符串转换为对应日期(可能会报错异常)
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date stringToDate(String source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (Exception e) {
            logger.error("字符串转换日期异常", e);
        }
        return date;
    }

    /**
     * 获得当前时间对应的指定格式
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前unix时间戳(单位秒)
     *
     * @return 当前unix时间戳
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
}
