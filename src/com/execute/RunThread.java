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
	 * 在队列最后面面加入一个执行(eventToRun)执行函数接口
	 * @param eventToRun 执行函数接口
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
	 * 在队列最前面加入一个执行(eventToRun)执行函数接口
	 * @param eventToRun 执行函数接口
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
	 * 关闭线程
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
