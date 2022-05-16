package com.execute;

import java.util.Hashtable;
import java.util.Map; 
import com.interfaces.EventToRun;
/**
 * ִ���¼�����
 * @author test
 *
 */
public class RunEvent {
	private Map<String,EventToRun> mapEvent = null;
	public RunEvent() {
		mapEvent = new Hashtable<String,EventToRun>();
	}
	/**
	 * ���һ���¼�
	 * @param name �¼�����
	 * @param eventToRun ִ���¼��Ľӿ�
	 */
	public void addEvent(String name,EventToRun eventToRun) {
		if(mapEvent.get(name)!=null) {
			System.err.println("�ظ�����¼�����:"+name);
			return;
		}
		mapEvent.put(name, eventToRun);
	}
	/**
	 * ͨ�����֣���ȡһ���¼���ִ��call
	 * @param name �¼�����
	 * @return ���ؽӿ�EventToRun
	 */
	public EventToRun getEvemt(String name) {
		return mapEvent.get(name);
	}
	/**
	 * �Ƴ�һ���¼�
	 * @param name �¼�����
	 */
	public void removelEvent(String name) {
		if(mapEvent.get(name)!=null) {
			mapEvent.remove(name);
		} 
	}
	/**
	 * �ر��¼�
	 */
	public void closeEvent() {
		mapEvent.clear();
	}
}
