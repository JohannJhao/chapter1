package com.antsix.common.schedule;


import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 */
@Component
public class ScheduledTasks {

    //private static Logger log = LogManager.getLogger(ScheduledTasks.class.getName());
    private static Logger log = Logger.getLogger(ScheduledTasks.class.getName());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
     * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     * @Scheduled(cron="表达式") ：通过cron表达式定义规则
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.debug("The time is now : "+dateFormat.format(new Date()));
    }
}
