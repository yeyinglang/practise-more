package org.example.schdule.schduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;


/**
 * 功能：
 * 动态修改cron表达式时间，来能够让其动态生效；
 * 方法一；
 *  动态替换，
 *  缺点：但是只能等到下一次触发，才能让这次动态替换生效；比如：当前时间7点，7.15触发了定时任务，随后你修改成了7.20，他不会生效，他只能等到明天的7.20才能生效；
 *
 *  方法二：
 *  动态替换：删除之前的定时任务，新增一个新的定时任务；
 */
@Component
public class MySchedulerConfigurer implements SchedulingConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(MySchedulerConfigurer.class);
    private int hour;
    private int minutes;
    private boolean changed;

    private String cron = " 0 0/2 * * * ?";
    @Resource
    ThreadPoolTaskScheduler taskScheduler;

    private Map<String, ScheduledFuture> map = new HashMap<String, ScheduledFuture>();
    private String previousCron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler);
        logger.error("-------------set taskScheduler ---------------");
//        taskRegistrar.getScheduler().schedule(() -> {
//            logger.warn("invoke sth, time ={}", LocalDateTime.now());
//        }, triggerContext -> {
//            CronTrigger con = new CronTrigger(getCron());
//            Date date = con.nextExecutionTime(triggerContext);
//            return date;
//        });
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void cronChanged() {
        String cronFormat = "0 %s %s * * ?";
        if (changed) {
            this.previousCron = this.cron;
            this.cron = String.format(cronFormat, this.minutes, this.hour);
            ScheduledFuture<?> schedule = taskScheduler.schedule(() -> {
                logger.warn("invoke scheduler = {}", this.cron);
            }, triggerContext -> {
                CronTrigger con = new CronTrigger(this.cron);
                return con.nextExecutionTime(triggerContext);
            });

            map.put(cron, schedule);
            ScheduledFuture remove = map.get(this.previousCron);
            if (remove != null && !remove.isCancelled()) {
                remove.cancel(true);
            }
            logger.error("remove previousCron = {}", previousCron);
            map.remove(this.previousCron);
        }
        logger.warn("currentTime={}, setCron={}", LocalDateTime.now(), this.cron);


    }


    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
