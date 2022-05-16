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
	 * ���һ��һ��������
	 * @param eventToRun ִ�нӿ�
	 * @param delay ��ʱ
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
	 * ���һ���ظ�������
	 * @param eventToRun ִ�нӿ�
	 * @param taskName ��������
	 * @param delay ��ʱ
	 * @param period �ظ�����ʱ��һ��
	 */
	public void addRepeatExecute(EventToRun eventToRun,String taskName,long delay, long period) {
		if(mapTask.get(taskName) != null) {
			System.err.println("����ظ�����ʧ�ܣ��ظ�����������:"+taskName);
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
	 * ȡ��һ���ظ�������
	 * @param taskName ��������
	 */
	public void cancelRepeatExecute(String taskName) {
		if(mapTask.get(taskName) != null) {
			TimerTask task = mapTask.get(taskName);
			task.cancel();
		}
	}
	/**
	 * �ر�һ����ʱ��
	 */
	public void closeTimer() {
		//ȡ����������
		timer.cancel();
	}
}
