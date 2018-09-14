package com.io.ssm.framework.utils.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @description: 时间工具类
 * @author: llyong
 * @date: 2018/9/14
 * @time: 12:50
 * @version: 1.0
 */
public class DateTimeUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(DateTimeUtil.class);

    /**
     * the millisecond of a day
     */
    public static final  long DAY_MILLISECOND = 24*60*60*1000;
    /**
     * the millisecond of a hour
     */
    public static final  long HOUR_MILLISECOND = 60*60*1000;
    /**
     * the millisecond of a minute
     */
    public static final  long MINUTE_MILLISECOND = 60*1000;
    /**
     * the millisecond of a second
     */
    public static final  long SECOND_MILLISECOND = 1000;

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

    /**
     * 获取当前时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getCurLocalDateTime() {
        return LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
    }
    /**
     * 当前DateTime转自定义格式
     * @param format
     * @return
     * @throws Exception
     */
    public static String getCurDateTimeString(String format) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))).format(formatter);
    }
    /**
     * 当前Date转自定义格式
     * @param format
     * @return
     * @throws Exception
     */
    public static String getCurDateString(String format) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.now(Clock.system(ZoneId.of("Asia/Shanghai"))).format(formatter);
    }
    /**
     * 当前Time转自定义格式
     * @param format
     * @return
     * @throws Exception
     */
    public static String getCurTimeString(String format) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))).format(formatter);
    }
    /**
     * 将指定的DateTime 转为自定义格式
     * @param localDateTime
     * @param format
     * @return
     * @throws Exception
     */
    public static String localDateTimeToString(LocalDateTime localDateTime,String format) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }
    /**
     * 将指定的Date 转为自定义格式
     * @param localDate
     * @param format
     * @return
     * @throws Exception
     */
    public static String localDateToString(LocalDate localDate,String format) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }
    /**
     * 将指定的Time 转为自定义格式
     * @param localTime
     * @param format
     * @return
     * @throws Exception
     */
    public static String localTimeToString(LocalTime localTime,String format) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localTime.format(formatter);
    }
    /**
     * 将Date 转为 LocalDateTime
     * @param date
     * @return
     * @throws Exception
     */
    public static LocalDateTime dateToLocalDateTime(Date date) throws Exception {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDateTime();
    }
    /**
     * 将Date 转为 LocalDate
     * @param date
     * @return
     * @throws Exception
     */
    public static LocalDate dateToLocalDate(Date date) throws Exception {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDate();
    }
    /**
     * 将Date 转为 LocalTime
     * @param date
     * @return
     * @throws Exception
     */
    public static LocalTime dateToLocalTime(Date date) throws Exception {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalTime();
    }
    /**
     * 将LocalDateTime 转为 Date
     * @param localDateTime
     * @return
     * @throws Exception
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) throws Exception {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
    /**
     * 将LocalDate 转为 Date
     * @param localDate
     * @return
     * @throws Exception
     */
    public static Date localDateToDate(LocalDate localDate) throws Exception {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
    /**
     * 将LocalTime 转为 Date
     * @param localTime
     * @return
     * @throws Exception
     */
    public static Date localTimeToDate(LocalTime localTime) throws Exception {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
    /**
     * 根据日期获得星期
     *
     * @param date
     * @return 1:星期一；2:星期二；3:星期三；4:星期四；5:星期五；6:星期六；7:星期日；
     */
    public static int getWeekOfDate(Date date) throws Exception {
        return dateToLocalDateTime(date).getDayOfWeek().getValue();
    }
    /**
     * 计算两个日期LocalDate相差的天数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsDateDiffDay(LocalDate before, LocalDate after) {
        return Math.abs(Period.between(before, after).getDays());
    }
    /**
     * 计算两个时间LocalDateTime相差的天数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getLocalDateTimeDiffDay(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getDays());
    }

    /**
     * 计算两个时间LocalDateTime相差的月数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getLocalDateTimeDiffMonth(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getMonths());
    }

    /**
     * 计算两个时间LocalDateTime相差的年数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getLocalDateTimeDiffYear(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getYears());
    }
    /**
     * string 转 LocalDateTime
     *
     * @param dateStr 例："2017-08-11 01:00:00"
     * @param format  例："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当月天数
     * @param date
     * @return
     * @throws Exception
     */
    public static int getActualMaximum(Date date) throws Exception {
        return dateToLocalDateTime(date).getMonth().length(dateToLocalDate(date).isLeapYear());
    }
    /**
     * 根据传入日期返回星期几
     *
     * @param date 日期
     * @return 1-7 1：星期天,2:星期一,3:星期二,4:星期三,5:星期四,6:星期五,7:星期六
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获取指定日期的当月的月份数
     *
     * @param date
     * @return
     */
    public static int getLastMonth(Date date) throws Exception{
        return dateToLocalDateTime(date).getMonth().getValue();

    }


    /**
     * 特定日期的当月第一天
     *
     * @param date
     * @return
     */
    public static LocalDate newThisMonth(Date date) throws Exception{
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    /**
     * 特定日期的当月最后一天
     *
     * @param date
     * @return
     */
    public static LocalDate lastThisMonth(Date date) throws Exception{
        int lastDay = getActualMaximum(date);
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), lastDay);
    }


    /**
     * 特定日期的当年第一天
     *
     * @param date
     * @return
     */
    public static LocalDate newThisYear(Date date) throws Exception{
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), 1, 1);

    }
    /**
     * 修改日期时间的时间部分
     *
     * @param date
     * @param customTime 必须为"hh:mm:ss"这种格式
     */
    public static LocalDateTime reserveDateCustomTime(Date date, String customTime) throws Exception {
        String dateStr = dateToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 修改日期时间的时间部分
     *
     * @param date
     * @param customTime 必须为"hh:mm:ss"这种格式
     */
    public static LocalDateTime reserveDateCustomTime(Timestamp date, String customTime) {
        String dateStr = timestampToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 把日期后的时间归0 变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param date
     * @return LocalDateTime
     */
    public static final LocalDateTime zerolizedTime(Date date) throws Exception {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);

    }

    /**
     * 把时间变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime zerolizedTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);
    }

    /**
     * 把日期的时间变成(yyyy-MM-dd 23:59:59:999)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime getEndTime(Date date) throws Exception {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999 * 1000000);
    }

    /**
     * 把时间变成(yyyy-MM-dd 23:59:59:999)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime getEndTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999 * 1000000);
    }

    /**
     * 计算特定时间到 当天 23.59.59.999 的秒数
     *
     * @return
     */
    public static int calculateToEndTime(Date date) throws Exception {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime end = getEndTime(date);
        return (int) (end.toEpochSecond(ZoneOffset.UTC) - localDateTime.toEpochSecond(ZoneOffset.UTC));
    }


    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param localDateTime 例：ChronoUnit.DAYS
     * @param chronoUnit
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(LocalDateTime localDateTime, ChronoUnit chronoUnit, int num) {
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param chronoUnit 例：ChronoUnit.DAYS
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(Date date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param chronoUnit 例：ChronoUnit.DAYS
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(Timestamp date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * Timestamp 转 LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime timestampToLocalDateTime(Timestamp date) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, date.getNanos(), ZoneOffset.of("+8"));

        return localDateTime;
    }

    /**
     * timestamp 转 LocalDateTime
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate timestampToLocalDate(Timestamp date) {

        return timestampToLocalDateTime(date).toLocalDate();
    }

    /**
     * 比较两个LocalDateTime是否同一天
     *
     * @param begin
     * @param end
     * @return
     */
    public static boolean isTheSameDay(LocalDateTime begin, LocalDateTime end) {
        return begin.toLocalDate().equals(end.toLocalDate());
    }


    /**
     * 比较两个时间LocalDateTime大小
     *
     * @param time1
     * @param time2
     * @return 1:第一个比第二个大；0：第一个与第二个相同；-1：第一个比第二个小
     */
    public static int compareTwoTime(LocalDateTime time1, LocalDateTime time2) {

        if (time1.isAfter(time2)) {
            return 1;
        } else if (time1.isBefore(time2)) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 比较time1,time2两个时间相差的秒数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffSecond(Timestamp time1, Timestamp time2) {
        long diff = timestampToLocalDateTime(time1).toEpochSecond(ZoneOffset.UTC) - timestampToLocalDateTime(time2).toEpochSecond(ZoneOffset.UTC);
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 比较time1,time2两个时间相差的分钟数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffMin(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 60;
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 比较time1,time2两个时间相差的小时数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffHour(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 3600;
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 判断当前时间是否在时间范围内
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isTimeInRange(Date startTime, Date endTime) throws Exception {
        LocalDateTime now = getCurLocalDateTime();
        LocalDateTime start = dateToLocalDateTime(startTime);
        LocalDateTime end = dateToLocalDateTime(endTime);
        return (start.isBefore(now) && end.isAfter(now)) || start.isEqual(now) || end.isEqual(now);
    }

}
