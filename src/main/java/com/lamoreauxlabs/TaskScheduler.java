package com.lamoreauxlabs;

import org.springframework.scheduling.annotation.Scheduled;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;


public class TaskScheduler {
    
    @Scheduled(cron = "*/15 * * * * *")
    @SchedulerLock(name = "scheduledTaskName", lockAtMostFor = "14s", lockAtLeastFor = "14s")
    public void scheduledTask() {
        System.out.println( "Running scheduled Task" );
    }
}
