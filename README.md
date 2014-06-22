CronScheduler
=============

Cron job in java

We know about cron job in unix. If we want to schedule any job in a particular day and time ,use corn scheduler in unix.
Being a java developer we always think if we can do the same in java. If we want to schedule a job in java we can use 
“Quartz Scheduler” To trigger a job we should use “cron expressions”, it tells when a job should be trigger. Below are 
the few examples of “cron expressions”

Expression	                     Meaning

0 0 12 * * ?	             Fire at 12pm (noon) every day
0 15 10 ? * *	             Fire at 10:15am every day
0 15 10 * * ?	             Fire at 10:15am every day
0 15 10 * * ? *	           Fire at 10:15am every day
0 15 10 * * ? 2005	       Fire at 10:15am every day during the year 2005
0 * 14 * * ?	             Fire every minute starting at 2pm and ending at 2:59pm, every day
0 0/5 14 * * ?	           Fire every 5 minutes starting at 2pm and ending at 2:55pm, every day
0 0/5 14,18 * * ?	         Fire every 5 minutes starting at 2pm and ending at 2:55pm, AND fire every 5 minutes starting at 6pm and ending at 6:55pm, every day
0 0-5 14 * * ?	           Fire every minute starting at 2pm and ending at 2:05pm, every day
0 10,44 14 ? 3 WED	       Fire at 2:10pm and at 2:44pm every Wednesday in the month of March.
0 15 10 ? * MON-FRI	       Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday
0 15 10 15 * ?	           Fire at 10:15am on the 15th day of every month
0 15 10 L * ?	             Fire at 10:15am on the last day of every month
0 15 10 L-2 * ?            Fire at 10:15am on the 2nd-to-last last day of every month
0 15 10 ? * 6L	           Fire at 10:15am on the last Friday of every month
0 15 10 ? * 6L	           Fire at 10:15am on the last Friday of every month
0 15 10 ? * 6L 2002-2005	 Fire at 10:15am on every last friday of every month during the years 2002, 2003, 2004 and 2005
0 15 10 ? * 6#3	           Fire at 10:15am on the third Friday of every month
0 0 12 1/5 * ?	           Fire at 12pm (noon) every 5 days every month, starting on the first day of the month.
0 11 11 11 11 ?	           Fire every November 11th at 11:11am.

For more detail please check http://quartz-scheduler.org/documentation/quartz-2.1.x/tutorials/crontrigger

Please follow the below steps to schedule a job in java based project.

1. CronScheduler.java

package com.codewale.example;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronScheduler extends HttpServlet{

public void scheduleJob1() {

String jobname = “This is job1″;
String triggername = “Trigger 1″;
String scheduletime = “0 0/2 * * * ?”;
if(jobname!=null)
{
System.out.println(“JOBNAME1=”+jobname+” TRIGGERNAME1=”+triggername+” JOBCRONEXPR1=”+scheduletime);
JobDetail job;
job = JobBuilder.newJob(Job1.class).withIdentity(jobname).build();

Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggername).withSchedule(CronScheduleBuilder.cronSchedule(scheduletime)).build();

Scheduler scheduler=null;
try {
scheduler = new StdSchedulerFactory().getScheduler();

scheduler.start();
scheduler.scheduleJob(job, trigger);
} catch(SchedulerException e){
try {
scheduler.shutdown(true);
} catch (SchedulerException f) {
f.printStackTrace();
}
}
}
}

public void scheduleJob2() {

String jobname = “This is job2″;
String triggername = “Trigger 2″;
String scheduletime = “0 0/2 * * * ?”;
if(jobname!=null)
{
System.out.println(“JOBNAME2=”+jobname+” TRIGGERNAME2=”+triggername+” JOBCRONEXPR2=”+scheduletime);
JobDetail job;
job = JobBuilder.newJob(Job2.class).withIdentity(jobname).build();

Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggername).withSchedule(CronScheduleBuilder.cronSchedule(scheduletime)).build();

Scheduler scheduler=null;
try {
scheduler = new StdSchedulerFactory().getScheduler();

scheduler.start();
scheduler.scheduleJob(job, trigger);
} catch(SchedulerException e){
try {
scheduler.shutdown(true);
} catch (SchedulerException f) {
f.printStackTrace();
}
}
}
}

public void init(ServletConfig config) throws ServletException
{
super.init(config);

System.out.println(” Initializing scheduled tasks from Job1………….”);
scheduleJob1();

System.out.println(” Initializing scheduled tasks from Job2………….”);
scheduleJob2();

}

}

2.Job1.java

package com.codewale.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job1 implements Job{

@Override
public void execute(JobExecutionContext arg0) throws JobExecutionException {
// TODO Auto-generated method stub

System.out.println(“Job1 started ……………………..”);
}

}

3.Job2.java

package com.codewale.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job{

@Override
public void execute(JobExecutionContext arg0) throws JobExecutionException {
// you can execute you job here
System.out.println(“Job2 started ……………………..”);

}

}

4.Index.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding=”ISO-8859-1″%>





Please check the log /console, if jobs started.

5.Web.xml

<?xml version=”1.0″ encoding=”UTF-8″?>
<web-app id=”WebApp_ID” version=”3.0″
xmlns=”http://java.sun.com/xml/ns/javaee”
xmlns:xsi=”http://www.w3.org/2001/XMLSchema-instance” xsi:schemaLocation=”http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd”>
<display-name>CronSchedulerInjava</display-name>

<welcome-file-list>
<welcome-file>index.html</welcome-file>
<welcome-file>index.htm</welcome-file>
<welcome-file>index.jsp</welcome-file>
<welcome-file>default.html</welcome-file>
<welcome-file>default.htm</welcome-file>
<welcome-file>default.jsp</welcome-file>
</welcome-file-list>

<servlet>
<servlet-name>CronScheduler</servlet-name>
<servlet-class>com.codewale.example.CronScheduler</servlet-class>
<load-on-startup>5</load-on-startup>
</servlet>
</web-app>

When web.xml will load, it will load the CronScheduler class. CronScheduler will decide which job it will execute.
Don’t forget to configure the web.xml
