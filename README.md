# EventTo
这是一个JAVA的事件调度框架，用于处理同步，异步的事件调度工作，并且支持事件。

## 使用方法
```
EventTo eventTo = EventTo.build();
```
## 你可以连续注册多个执行任务来处理你的事情，每个任务优先级不同
```
eventTo.pushEventTo(new EventToRun() {  
   @Override  
   public void run() {  
	  System.out.println("测试1");  
       }  
   });  
```
## 你也可以连续调用  
```
eventTo.topEventTo(new EventToRun() {   
	@Override
	public void run() {  
		System.out.println("测试2");  
	}  
}).newEventTo(new EventToRun() {   
	@Override  
	public void run() {  
		System.out.println("测试3");   
	}  
},0);  
```
## 你也可以注册一个重复执行任务(每秒执行一次)。
```
eventTo.repeatEventTo(new EventToRun() {  
	@Override  
	public void run() {  
		System.out.println("测试4");   
	}  
}, "test", 0, 1000);  
```
## 取消重复执行
```
eventTo.cancelRepeatEventTo("test");
```
## 你也可以注册一个带key的事件，在有需要的时候执行
```
eventTo.registerMessageEventTo("sendTest1", new EventToRun() {  
	@Override  
	public void run() {  
		System.out.println("接受一个测试5");   
	}  
})  
```
## 异步执行
```
eventTo.asySendMessageEventTo("sendTest1");
```
## 同步执行（注意同步执行会阻塞当前执行流程）
```
eventTo.syncSendMessageEventTo("sendTest1");
```
## 移除事件
```
eventTo.removeMessageEventTo("sendTest1");
```
## 销毁EventTo
```
eventTo.destroyEventTo();
```
