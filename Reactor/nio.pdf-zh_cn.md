# Java中可伸缩的`IO`

`Doug Lea`

## 大纲

- 可伸缩的网络服务

- 事件驱动的处理

- `Reactor`模式
  - 基础版本
  - 多线程版本
  - 其他的变种
- 漫读`java nio`的`API`

## 网络服务

- `Web Services`、`Distributed Objects`等等
- 大部分都有相同的基本结构
  - `Read request`
  - `Decode request`
  - `Process service`
  - `Encode reply`
  - `Send reply`
- 但每一步的花费及本质是不同的
  - `xml`解析，文件传输，`web`页面生成，计算服务

## 经典的服务设计

![](../img/经典的服务设计.png)

- 多个客户端向服务端发起连接，服务端用相应的处理器处理请求，每个处理器在自己的线程中启动

## 经典的`ServerSocket Loop`

```java
class Server implements Runnable {
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
 			while (!Thread.interrupted())
                // 生成新的线程，里面的handler处理客户端请求
 				new Thread(new Handler(ss.accept())).start();
		 } catch (IOException ex) { /* ... */ }
 	}
 	static class Handler implements Runnable {
		final Socket socket;
 		Handler(Socket s) { socket = s; }
 		public void run() {
            // IO操作
 			try {
 				byte[] input = new byte[MAX_INPUT];
                // 获取输入流，读数据
 				socket.getInputStream().read(input);
                // 获取输出流，写数据
 				byte[] output = process(input);
 				socket.getOutputStream().write(output);
 			} catch (IOException ex) { /* ... */ }
 		}
 		private byte[] process(byte[] cmd) { /* ... */ }
 	}
}
```



