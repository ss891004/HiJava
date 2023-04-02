package com.base.b09;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

public class DateUtil {

    /**
     * Joda-Time
     *
     * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
     *  Local(本地) − 简化了日期时间的处理，没有时区的问题。
     *  Zoned(时区) − 通过制定的时区处理日期时间。
     * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
     *
     *  java.time 中包含了所有关于
     * 本地日期（ LocalDate ）、本地时间( LocalTime ）、本地日期时间 LocalDateTime ）、时区 ZonedDateTime 和持续时间（ Duration ）的类
     *
     * java.time         包含值对象的基础包
     * java.time.chrono  提供对不同的日历系统的访问
     * java.time.format  格式化和解析时间和日期
     * java.time.temporal 包括底层框架和扩展特性
     * java.time.zone 包含时区支持的类
     *
     *
     * LocalDate 、 LocalTime 、 LocalDateTime 都是不可变的对象, 代表 ISO 8601日历系统的日期、时间、日期和时间。
     */


    public static void main(String[] args) {

        Test1();




    }


    //LocalDate，LocalTime
    //LocalDate类的实例是一个不可变的对象，只提供了简单的日期，并不包含当前的时间信息（只关注与年月日）。也不附带任何与时区相关的信息。
    //LocalTime类关注时分秒。

    public static void time1(){

        //关注与年月日
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);  //2017-06-26

        System.out.println(localDate.getYear()); //2017，年
        System.out.println(localDate.getMonthValue()); //6，月
        System.out.println(localDate.getDayOfMonth()); //26，日
        System.out.println(localDate.getDayOfWeek()); //MONDAY,星期几
        System.out.println(localDate.lengthOfMonth()); //30,返回当前月份的长度
        System.out.println(localDate.isLeapYear());//false,是否是闰年

        System.out.println("------------------");

        LocalDate localDate2 = LocalDate.of(2017,4,1);
        System.out.println(localDate2); //2017-04-01

        System.out.println("------------------");

        //MonthDay关注月份和日
        LocalDate localDate3 = LocalDate.of(2017,3,25);
        MonthDay monthDay = MonthDay.of(localDate3.getMonth(),localDate3.getDayOfMonth());
        System.out.println(monthDay); //--03-25
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2014,3,25));
        System.out.println(monthDay2); //--03-25

        if(monthDay.equals(monthDay2)){
            System.out.println("equal");
        }else{
            System.out.println("not equal");
        }

        //关注与时分秒
        LocalTime time = LocalTime.now();
        System.out.println(time); //22:30:01.512
        System.out.println(time.getHour()); //22,时
        System.out.println(time.getMinute()); //30，分
        System.out.println(time.getSecond());  //01，秒

        LocalTime time2 = time.plusHours(3).plusMinutes(40);
        System.out.println(time2);  //02:10:01.512



    }


    public static void time2(){
        //LocalDate的parse只能转换2007-12-03这样的格式的,不能解析的也会抛出一个RuntimeException
        //或者DateTimeParseException
        LocalDate date = LocalDate.parse("2014-03-18");
        LocalDate date2 = LocalDate.parse("2017-03-18");
        System.out.println(date);
        System.out.println(date2);

        LocalDate nowdate = LocalDate.now();
        String date3 = nowdate.toString();
        System.out.println(date3); //2017-06-26
    }

    public static void  time3(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); //2017-06-27

        //当前日期的二周后
        LocalDate localDate2 = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(localDate2); //2017-07-11
        System.out.println("............");

        //当前时间的二个月之前
        LocalDate localDate3 = localDate.minus(2,ChronoUnit.MONTHS);
        System.out.println(localDate3);//2017-04-27
        System.out.println("..............");

        Clock clock = Clock.systemDefaultZone(); //当前时区的时刻
        System.out.println(clock.millis()); //获得当前的毫秒数,1498529786982


        LocalDate localDate5 = LocalDate.now();
        LocalDate localDate6 = LocalDate.of(2017,4,12);
        System.out.println(localDate5.isBefore(localDate6));  //判断时间在什么时间之前
        System.out.println(localDate5.isAfter(localDate6)); //判断时间在什么时间之后
        System.out.println(localDate5.isEqual(localDate6)); //判断时间和什么时间相等
        System.out.println("..............");
    }

    public static void  datetime1(){
        LocalDateTime dt1 = LocalDateTime.of(2017, Month.APRIL,18,13,45,20);
        System.out.println(dt1);
        LocalDate date1 = dt1.toLocalDate(); //通过LocalDateTime获得LocalDate
        LocalTime time1 = dt1.toLocalTime(); //通过LocalDateTime获得LocalTime
        System.out.println("date1======="+date1+"time1===="+time1);

        LocalDate date = LocalDate.of(2014,02,26);
        LocalTime time = LocalTime.of(12,23,20);
        LocalDateTime dt2 = LocalDateTime.of(date,time);
        System.out.println(dt2);

        //LocalDate结合LocalTime成一个LocalDateTime
        LocalDateTime dt3 = date.atTime(13,45,20);
        System.out.println(dt3); //2014-02-26T13:45:20

        //LocalDate结合LocalTime成一个LocalDateTime
        LocalDateTime dt4 = date.atTime(time);
        System.out.println(dt4); //2014-02-26T12:23:20

        //LocalTime结合LocalDate成LocalDateTime
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt5); //2014-02-26T12:23:20

    }


    public static void instant1(){
        Instant instant1 = Instant.ofEpochSecond(3);
        System.out.println(instant1);//1970-01-01T00:00:03Z

        //第一个参数是秒，第二个是纳秒参数，纳秒的存储范围是0至999,999,999
        Instant instant2 = Instant.ofEpochSecond(3,0);
        System.out.println(instant2);//1970-01-01T00:00:03Z

        //2s之后的在加上100万纳秒（1s）
        Instant instant3 = Instant.ofEpochSecond(2,1000000000);
        System.out.println(instant3); //1970-01-01T00:00:03Z

        Instant instant4 = Instant.ofEpochSecond(4,-1000000000);
        System.out.println(instant4); //1970-01-01T00:00:03Z

        Instant instant = Instant.now();
        System.out.println(instant);
    }


    public static void Duration_(){
        LocalTime time1 = LocalTime.of(18,23,45);
        LocalTime time2 = LocalTime.of(23,34,56);

        Duration d1 = Duration.between(time1,time2);
        System.out.println(d1.getSeconds()); //18671


        LocalDateTime localDateTime1 = LocalDateTime.of(2016,Month.MAY,12,11,13,11);
        LocalDateTime localDateTime2 = LocalDateTime.of(2017, Month.AUGUST,18,23,45,20);

        Duration d2 = Duration.between(localDateTime1,localDateTime2);
        System.out.println(d2.getSeconds()); //40048329


        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(6);

        Duration d3 = Duration.between(instant1,instant2);
        System.out.println(d3.getSeconds()); //3
    }

    public static void Period_(){
        //LocalDate localDate1 = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2017,4,12);
        LocalDate localDate2 = LocalDate.of(2018,3,16);

        Period period = Period.between(localDate1,localDate2);
        System.out.println(period.getYears()); //获取相隔的年份差 0
        System.out.println(period.getMonths()); //获取相隔的月份差 11
        System.out.println(period.getDays()); //获取相隔的日子差 4

        System.out.println("...............");

        System.out.println(Instant.now()); //表示当前的不带时区的UTC标准时间,2017-04-12T14:40:29.309Z

    }


    public static void timezone(){
        //时区
        Set<String> sets = ZoneId.getAvailableZoneIds();

        //sets.stream().forEach(System.out::println);

        //构建一个TreeSet的匿名内部类,然后里面是个代码块表示在实例创建的时候执行这个代码块
        TreeSet<String> treeSet = new TreeSet<String>(){
            {
                addAll(sets);
            }
        };

        treeSet.stream().forEach(System.out::println);

        System.out.println("........................");

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);//2017-04-12T22:05:22.500

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime,zoneId);
        System.out.println(zonedDateTime); //2017-04-12T22:05:22.500+08:00[Asia/Shanghai]

        System.out.println("..................");

        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth); //2017-06
        System.out.println(yearMonth.lengthOfMonth());  //当月有多少天,30
        System.out.println(yearMonth.isLeapYear()); //是否是闰年,false

        System.out.println(".............");

        YearMonth yearMonth2 = YearMonth.of(2016,2);
        System.out.println(yearMonth2); //2016-02
        System.out.println(yearMonth2.lengthOfMonth()); //当前的月有多少天,29
        System.out.println(yearMonth2.lengthOfYear()); //一年有多少天,366
        System.out.println(yearMonth2.isLeapYear()); //是否是闰年,true
    }


    //本地化日期时间 API
    public static void Test1(){
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }


    //使用时区的日期时间API
    public static void Test2(){

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

    }




}
