# EventTo 
## This Code is UTF-8 Encoded
这是一个JAVA的多任务处理和事件调度框架。
This is a JAVA multitasking and event scheduling framework.
## 使用方法
## how to use?
```
EventTo eventTo = EventTo.build();
```
## 你可以注册多个任务处理你的事情，每个任务拥有不同的优先级
## You can register multiple tasks in succession to handle your affairs, each task has a different priority
```
//注册一个插入到队列最后面的任务
//Register a task to be inserted at the end of the queue
eventTo.pushEventTo(new EventToRun() {  
   @Override  
   public void run() {  
	  System.out.println("测试1");  
       }  
   });  
```
## 你也可以连续调用  
## You can also call continuously
```
//注册一个插入到队列最前面的任务
//Register a task to be inserted at the front of the queue
eventTo.topEventTo(new EventToRun() {   
	@Override
	public void run() {  
		System.out.println("测试2");  
	}  
//注册一个非队列的任务
//Register a non-queued task
}).newEventTo(new EventToRun() {   
	@Override  
	public void run() {  
		System.out.println("测试3");   
	}  
},0);  
```
## 你也可以注册一个重复执行任务(每毫秒执行一次)。
## You can also register a recurring task (every millisecond).
```
eventTo.repeatEventTo(new EventToRun() {  
	@Override  
	public void run() {  
		System.out.println("测试4");   
	}  
}, "test", 0, 1000);  
```
## 取消重复执行任务
## Cancel repeat execution task
```
eventTo.cancelRepeatEventTo("test");
```
## 你也可以注册一个带key的事件，在有需要的时候执行
## You can also register an event with a key to execute when needed
```
eventTo.registerMessageEventTo("sendTest1", new EventToRun() {  
	@Override  
	public void run() {  
		System.out.println("接受一个测试5");   
	}  
})  
```
## 异步执行事件
## Asynchronous execution of events
```
eventTo.asySendMessageEventTo("sendTest1");
```
## 同步执行事件（注意同步执行会阻塞当前执行流程）
## Synchronous execution events (note that synchronous execution will block the current execution process)
```
eventTo.syncSendMessageEventTo("sendTest1");
```
## 移除事件
## remove event
```
eventTo.removeMessageEventTo("sendTest1");
```
## 销毁EventTo
## destroyEventTo
```
eventTo.destroyEventTo();
```
2022.05.18
## 你也可以通过名字创建多个新的实例化
## You can also create multiple new instantiations by name
```
EventTo eventTo = EventTo.build("test");
```
## 你也可以通过名字销毁新建的EventTo.
## You can also destroy the newly created EventTo by name.
```
EventTo.destroyEventTo("test");
```
