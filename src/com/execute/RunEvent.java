package com.execute;

import java.util.Hashtable;
import java.util.Map; 
import com.interfaces.EventToRun;
/**
 * 执行事件的类
 * @author test
 *
 */
public class RunEvent {
	private Map<String,EventToRun> mapEvent = null;
	public RunEvent() {
		mapEvent = new Hashtable<String,EventToRun>();
	}
	/**
	 * 添加一个事件
	 * @param name 事件名字
	 * @param eventToRun 执行事件的接口
	 */
	public void addEvent(String name,EventToRun eventToRun) {
		if(mapEvent.get(name)!=null) {
			System.err.println("重复添加事件名字:"+name);
			return;
		}
		mapEvent.put(name, eventToRun);
	}
	/**
	 * 通过名字，获取一个事件的执行call
	 * @param name 事件名字
	 * @return 返回接口EventToRun
	 */
	public EventToRun getEvemt(String name) {
		return mapEvent.get(name);
	}
	/**
	 * 移除一个事件
	 * @param name 事件名字
	 */
	public void removelEvent(String name) {
		if(mapEvent.get(name)!=null) {
			mapEvent.remove(name);
		} 
	}
	/**
	 * 关闭事件
	 */
	public void closeEvent() {
		mapEvent.clear();
	}
}
