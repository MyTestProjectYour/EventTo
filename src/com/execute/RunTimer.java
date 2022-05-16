package com.execute;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import com.interfaces.EventToRun;

public class RunTimer {
	private Timer timer = null; 
	private Map<String, TimerTask> mapTask = null;
	public RunTimer() {
		timer = new Timer("Timer");
		mapTask = new HashMap<String, TimerTask>();
	}
	/**
	 * 添加一个一次性任务
	 * @param eventToRun 执行接口
	 * @param delay 延时
	 */
	public void addOneExecute(EventToRun eventToRun,long delay) {
		TimerTask task = new TimerTask() {
	        public void run() {
	        	eventToRun.run();
	        }
	    }; 
	    timer.schedule(task, delay); 
	}
	/**
	 * 添加一个重复性任务
	 * @param eventToRun 执行接口
	 * @param taskName 任务名字
	 * @param delay 延时
	 * @param period 重复多少时间一次
	 */
	public void addRepeatExecute(EventToRun eventToRun,String taskName,long delay, long period) {
		if(mapTask.get(taskName) != null) {
			System.err.println("添加重复任务失败，重复的任务名字:"+taskName);
			return;
		}
		TimerTask task = new TimerTask() {
	        public void run() {
	        	eventToRun.run();
	        }
	    }; 
	    timer.schedule(task, delay,period); 
	    mapTask.put(taskName, task);
	}
	/**
	 * 取消一个重复性任务
	 * @param taskName 任务名字
	 */
	public void cancelRepeatExecute(String taskName) {
		if(mapTask.get(taskName) != null) {
			TimerTask task = mapTask.get(taskName);
			task.cancel();
		}
	}
	/**
	 * 关闭一个定时器
	 */
	public void closeTimer() {
		//取消所有任务
		timer.cancel();
	}
}
