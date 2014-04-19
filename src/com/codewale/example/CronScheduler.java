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

public class CronScheduler  extends HttpServlet{
	
	
	 public void scheduleJob1() {
	       
	            String jobname = "This is job1";
	            String triggername = "Trigger 1";
	            String scheduletime = "0 0/2 * * * ?";
	        if(jobname!=null)
	        {
	           System.out.println("JOBNAME1="+jobname+" TRIGGERNAME1="+triggername+" JOBCRONEXPR1="+scheduletime);
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
	       
         String jobname = "This is job2";
         String triggername = "Trigger 2";
         String scheduletime = "0 0/2 * * * ?";
     if(jobname!=null)
     {
        System.out.println("JOBNAME2="+jobname+" TRIGGERNAME2="+triggername+" JOBCRONEXPR2="+scheduletime);
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

System.out.println(" Initializing scheduled tasks from Job1.............");
scheduleJob1();

System.out.println(" Initializing scheduled tasks from Job2.............");
scheduleJob2();


}

}
