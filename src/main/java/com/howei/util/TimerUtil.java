package com.howei.util;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;

import java.text.ParseException;
import java.util.*;

/**
 * 定时功能
 */
public class TimerUtil {
    private static Logger logger = (Logger) LogManager.getLogger(TimerUtil.class);

    private Timer timer = new Timer();

    public static Map<String, TimerTask> map = new HashMap<>();    //用来存储所有定时器

    /**
     * 延时执行定时任务
     * @param uuid  定时任务id
     * @param delay 延时时间（毫秒）
     */
    public void startTimerDelay(String uuid, long delay) throws Exception {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                logger.info("-------设定要指定任务--------");
            }
        };
        timer.schedule(task, delay);
//        TimerController.map.put(uuid, task);
    }

    /**
     * 延时并按周期循环执行定时任务
     * @param uuid  定时任务id
     * @param delay 延时时间（毫秒）
     * @param period  循环间隔时间（毫秒）
     * @param str   打印信息
     * @throws InterruptedException
     * @throws ParseException
     */
    public void startTimerDelayPeriod(final String uuid, long delay, long period, final String str) throws InterruptedException, ParseException {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                logger.info("====>  " + uuid + str);
            }
        };
        timer.scheduleAtFixedRate(task, delay, period);
        map.put(uuid, task);
    }

    /**
     * 设置指定任务TimerTask在今天指定时间开始执行，并且以固定的period毫秒为周期执行
     * @param uuid  定时任务id
     * @param period 周期（毫秒）
     * @param hour      几点
     * @param minute    几分
     * @param second    几秒
     */
    public void startTimerDelayPeriodByTime(String uuid, long period, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);     //时
        calendar.set(Calendar.MINUTE, minute);   //分
        calendar.set(Calendar.SECOND, second);    //秒
        Date time = calendar.getTime(); //得出执行任务的时间,此处为今天的12：00：00
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        };
        timer.scheduleAtFixedRate(task, time, period);   //这里设定将延时每天固定执行
        map.put(uuid, task);
    }

    /**
     * 停止定时任务
     * @param uuid  定时任务id
     */
    public void stopTimer(String uuid) {
        TimerTask task = map.get(uuid);
        if (task != null) {
            task.cancel();
            logger.info("====>  结束定时任务[" + uuid + "]");
            map.remove(uuid);
        }
    }

}
