package com.main;

import java.util.HashMap;
import java.util.Map;
import com.execute.RunEvent;
import com.execute.RunThread;
import com.execute.RunTimer; 
import com.interfaces.EventToRun; 
public class EventTo {
	private static Map<String, EventTo> mapEventTo = null; 
	private RunThread runThread = null; 
	private RunTimer runTimer = null;
	private RunEvent runEvent = null;
	private final static String DEFINE_BUILD_NAME = "MainBuild";
	/**
	 * 实例化事件，注意这是单列的！
	 * @return EventTo this
	 */
	public static EventTo build() {
		EventTo eventTo = build(DEFINE_BUILD_NAME);
		return eventTo;
	}
	/**
	 * 构建一个新的单列，通过你输入的名字构建
	 * @param name 单列名字
	 * @return
	 */
	public static EventTo build(String name) {
		if(mapEventTo == null) {
			mapEventTo = new HashMap<String, EventTo>();
		}
		EventTo eventTo = mapEventTo.get(name);
		if(eventTo != null) {
			return eventTo;
		}
		eventTo = new EventTo();
		mapEventTo.put(name, eventTo);
		return eventTo;
	}
	
	private EventTo() {
		runThread = new RunThread();
		runThread.start();
		runTimer = new RunTimer();
		runEvent = new RunEvent();
	}
	/**
	 * 向队列的最后面添加一个执行的事件
	 * @param eventToRun 执行函数接口
	 * @return EventTo this
	 */
	public EventTo pushEventTo(EventToRun eventToRun) {
		if(eventToRun != null) {
			runThread.push(eventToRun);
		} 
		return this;
	}
	/**
	 * 向队列最前面添加一个执行事件
	 * @param eventToRun 执行函数接口
	 * @return EventTo this
	 */
	public EventTo topEventTo(EventToRun eventToRun) {
		if(eventToRun != null) {
			runThread.top(eventToRun);
		}
		return this;
	}
	/**
	 * 添加一个一次性执行事件
	 * @param eventToRun 执行函数接口
	 * @param delay 延时多久执行
	 * @return EventTo this
	 */
	public EventTo newEventTo(EventToRun eventToRun,long delay) {
		if(eventToRun != null) {
			runTimer.addOneExecute(eventToRun,delay);
		}
		return this;
	}
	/**
	 * 添加一个重复性的任务
	 * @param eventToRun 执行函数接口
	 * @param taskName 任务名字
	 * @param delay 延时多久执行
	 * @param period 每多少秒执行一次
	 * @return EventTo this
	 */
	public EventTo repeatEventTo(EventToRun eventToRun,String taskName,long delay, long period) {
		if(eventToRun != null) {
			runTimer.addRepeatExecute(eventToRun, taskName, delay, period);
		}
		return this;
	}
	/**
	 * 取消一个重复性的任务 
	 * @param taskName 任务名字
	 * @return EventTo this
	 */
	public EventTo cancelRepeatEventTo(String taskName) {
		if(taskName != null) {
			runTimer.cancelRepeatExecute(taskName);
		}
		return this;
	}
	/**
	 * 注册一个事件
	 * @param name 事件名字
	 * @param eventToRun 执行函数接口
	 * @return EventTo this
	 */
	public EventTo registerMessageEventTo(String name,EventToRun eventToRun) {
		runEvent.addEvent(name, eventToRun);
		return this;
	}
	/**
	 * 同步执行一个事件,注意这里会阻塞当前的执行流程
	 * @param name 事件名字
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
	 * 异步执行一个事件
	 * @param name 事件名字
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
	 * 移除一个事件 
	 * @param name 事件名字
	 * @return EventTo this
	 */
	public EventTo removeMessageEventTo(String name) {
		if(name != null) {
			runEvent.removelEvent(name);
		}
		return this;
	}
	
	/**
	 * 销毁默认的单列
	 */
	public void destroyEventTo() {
		destroyEventTo(DEFINE_BUILD_NAME);
	}
	/**
	 * 销毁一个单列，通过名字
	 * @param name
	 */
	public void destroyEventTo(String name) {
		EventTo eventTo = build(name);
		eventTo.runThread.closeThread();
		eventTo.runTimer.closeTimer();
		eventTo.runEvent.closeEvent();
		mapEventTo.remove(name);
		runEvent = null;
	}
}
