package com.codewale.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// you can execute you job here
		System.out.println("Job2 started ..........................");
		
	}

}
