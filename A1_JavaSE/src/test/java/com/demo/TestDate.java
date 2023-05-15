package com.demo;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TestDate {

    /**
     * Java8  时间类 在 java.time 包中
     * <p>
     * 关键类
     * LocalDateTime、LocalDate、LocalTime：本地的时间日期类。
     * ZonedDateTime：带时区的时间日期类
     * Instant：瞬时类，代表某个时间点。以1970-01-01 08:00:00 为起点开始，发生在这之后的为正值，这之前的为负值，精确到纳秒。
     * ZoneId：时区类。
     * ZoneOffset：时区偏移量
     * Period、Duration、ChronoUnit：周期、持续时间、测量时间间隔
     * Clock 时钟类
     *
     * LocalDate、LocalTime、LocalDateTime、Instant**为不可变对象，修改这些对象对象会返回一个副本
     */


    @Test
    public void localDate() {

        // java8获取当前日期
        LocalDate.now();

        //Java 8中获取当前时间
        LocalTime.now();

        LocalDateTime.now();

        //Java 8中获取年、月、日信息
        LocalDate.now().getYear();
        LocalDate.now().getMonthValue();
        LocalDate.now().getDayOfMonth();


        //Java 8中处理特定日期
        LocalDate.of(2018, 2, 6);

        // Java 8中判断两个日期是否相等
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018, 2, 5);
        if (date1.equals(date2)) {
            System.out.println("时间相等");
        }

        //Java 8中增加当前时间
        LocalTime.now().plusHours(3);

        //Java 8如何计算一周后的日期
        LocalDate.now().plus(1, ChronoUnit.WEEKS);

        //Java 8计算一年前或一年后的日期
        LocalDate.now().minus(1, ChronoUnit.YEARS);
        LocalDate.now().plus(1, ChronoUnit.YEARS);


        //Java 8的Clock时钟类
        Clock.systemUTC();
        Clock.systemDefaultZone();

        //Java 8中处理时区
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);


        YearMonth.now();
        YearMonth.of(2019, Month.FEBRUARY);

        //计算两个日期之间的天数和月数
        LocalDate today = LocalDate.now();
        LocalDate otherDt = LocalDate.of(2023, 1, 1);
        Period periodToNextJavaRelease = Period.between(today, otherDt);


        //在Java 8中获取当前的时间戳
        Instant.now();

        //字符串互转日期类型
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:" + str);
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//字符串转日期
        LocalDate date3 = LocalDate.parse(str, format2);
        System.out.println("日期类型:" + date2);


    }


}
