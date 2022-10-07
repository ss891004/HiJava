package com.mp.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    private static int count = 1;
    /**
     * 秒 分 时 日 月 星期几
     * 比如: "0 * * * * MON-FRI" 周一到周五, 每次0秒执行(即每分钟执行一次)
     */
    @Scheduled(cron = "*/3 * * * * MON-FRI")
    public void dataCount() {
        System.out.println("数据统计第" + count++ + "次");
    }
}