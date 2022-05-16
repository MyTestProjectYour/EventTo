package com.execute;
 
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque; 
import com.interfaces.EventToRun;

public class RunThread extends Thread {
	private BlockingDeque<EventToRun> eventToDeque = null;
	private volatile boolean flag = true;
	public RunThread() {
		eventToDeque = new LinkedBlockingDeque<EventToRun>();
	}
	/**
	 * �ڶ�������������һ��ִ��(eventToRun)ִ�к����ӿ�
	 * @param eventToRun ִ�к����ӿ�
	 */
	public void push(EventToRun eventToRun) { 
		eventToDeque.addLast(eventToRun);
		if(getState() == Thread.State.WAITING) { 
			synchronized (this) {
				this.notify();
			}
		} 
	}
	/**
	 * �ڶ�����ǰ�����һ��ִ��(eventToRun)ִ�к����ӿ�
	 * @param eventToRun ִ�к����ӿ�
	 */
	public void top(EventToRun eventToRun) {
		eventToDeque.addFirst(eventToRun);
		if(getState() == Thread.State.WAITING) { 
			synchronized (this) {
				this.notify();
			}
		} 
	}
	/**
	 * �ر��߳�
	 */
	public void closeThread() {
		flag = false;
		synchronized (this) {
			this.notify();
		}
	}
	@Override
	public void run() {
		while(flag) {
			//�˳��߳�
			if(!flag) {
				break;
			}
			int count = eventToDeque.size(); 
			if(count > 0) {
				EventToRun firstE = eventToDeque.pollFirst();
				if(firstE != null) {
					firstE.run();  
				} 
			} 
			try {
				count = eventToDeque.size();
				if(count == 0) {
					synchronized (this) {
						this.wait();
					} 
				} 
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
	}
}
