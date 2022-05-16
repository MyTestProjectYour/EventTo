package com.main;

import com.execute.RunEvent;
import com.execute.RunThread;
import com.execute.RunTimer; 
import com.interfaces.EventToRun; 
public class EventTo {
	private static EventTo eventTo = null;
	private RunThread runThread = null; 
	private RunTimer runTimer = null;
	private RunEvent runEvent = null;
	/**
	 * ʵ�����¼���ע�����ǵ��еģ�
	 * @return EventTo this
	 */
	public static EventTo build() {
		if(eventTo == null) {
			eventTo = new EventTo();
		}
		return eventTo;
	}
	public EventTo() {
		runThread = new RunThread();
		runThread.start();
		runTimer = new RunTimer();
		runEvent = new RunEvent();
	}
	/**
	 * ����е���������һ��ִ�е��¼�
	 * @param eventToRun ִ�к����ӿ�
	 * @return EventTo this
	 */
	public EventTo pushEventTo(EventToRun eventToRun) {
		if(eventToRun != null) {
			runThread.push(eventToRun);
		} 
		return this;
	}
	/**
	 * �������ǰ�����һ��ִ���¼�
	 * @param eventToRun ִ�к����ӿ�
	 * @return EventTo this
	 */
	public EventTo topEventTo(EventToRun eventToRun) {
		if(eventToRun != null) {
			runThread.top(eventToRun);
		}
		return this;
	}
	/**
	 * ���һ��һ����ִ���¼�
	 * @param eventToRun ִ�к����ӿ�
	 * @param delay ��ʱ���ִ��
	 * @return EventTo this
	 */
	public EventTo newEventTo(EventToRun eventToRun,long delay) {
		if(eventToRun != null) {
			runTimer.addOneExecute(eventToRun,delay);
		}
		return this;
	}
	/**
	 * ���һ���ظ��Ե�����
	 * @param eventToRun ִ�к����ӿ�
	 * @param taskName ��������
	 * @param delay ��ʱ���ִ��
	 * @param period ÿ������ִ��һ��
	 * @return EventTo this
	 */
	public EventTo repeatEventTo(EventToRun eventToRun,String taskName,long delay, long period) {
		if(eventToRun != null) {
			runTimer.addRepeatExecute(eventToRun, taskName, delay, period);
		}
		return this;
	}
	/**
	 * ȡ��һ���ظ��Ե����� 
	 * @param taskName ��������
	 * @return EventTo this
	 */
	public EventTo cancelRepeatEventTo(String taskName) {
		if(taskName != null) {
			runTimer.cancelRepeatExecute(taskName);
		}
		return this;
	}
	/**
	 * ���һ���¼�
	 * @param name �¼�����
	 * @param eventToRun ִ�к����ӿ�
	 * @return EventTo this
	 */
	public EventTo registerMessageEventTo(String name,EventToRun eventToRun) {
		runEvent.addEvent(name, eventToRun);
		return this;
	}
	/**
	 * ͬ��ִ��һ���¼�,ע�������������ǰ��ִ������
	 * @param name �¼�����
	 * @return EventTo this
	 */
	public EventTo syncSendMessageEventTo(String name) {
		EventToRun eventToRun = runEvent.getEvemt(name);
		if(eventToRun != null) {
			eventToRun.run();
		}
		return this;
	}
	/**
	 * �첽ִ��һ���¼�
	 * @param name �¼�����
	 * @return EventTo this
	 */
	public EventTo asySendMessageEventTo(String name) {
		EventToRun eventToRun = runEvent.getEvemt(name);
		if(eventToRun != null) {
			runTimer.addOneExecute(eventToRun, 0);
		}
		return this;
	}
	/**
	 * �Ƴ�һ���¼� 
	 * @param name �¼�����
	 * @return EventTo this
	 */
	public EventTo removeMessageEventTo(String name) {
		if(name != null) {
			runEvent.removelEvent(name);
		}
		return this;
	}
	
	/**
	 * �������е����ݺ��Լ�
	 */
	public void destroyEventTo() {
		runThread.closeThread();
		runTimer.closeTimer();
		runEvent.closeEvent();
		eventTo = null;
	}
}
