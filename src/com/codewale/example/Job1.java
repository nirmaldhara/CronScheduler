package com.codewale.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job1  implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// execute your job here
		
		System.out.println("Job1 started ..........................");
	}

}
