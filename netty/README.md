## netty作用方向
- 作为http服务器，类似tomcat,处理请求。但并没有实现servlet规范。
- socket开发。客户端与服务器端通过socket进行调用，比如dubbo和spark等。
- 支持长连接开发。

## websocket
- 是一种规范，属于html5规范的一部分，解决http协议存在的一些问题
- http是无状态的(即两次请求之间没有关系)。cookie、session就是为了解决这个问题
- http是基于请求和响应模式的。1.0不支持长连接，1.1通过keepAlive让客户端和服务端在一定时间内保持连接
- http 1.0 1.1都不支持服务器向客户端推送数据。 
- websocket可以实现服务端与客户端之间的长连接，真正意义上的长连接，如果没有其他因素干扰，连接不会断掉。
- websocket连接建立后双方是对等的，服务端可以随意向客户端发送数据，客户端也可以向服务端发送数据，双方只需要发送数据本身，而不需要发送头信息
- websocket是基于http协议的
- websocket也可以用在非浏览器的应用上，比如安卓、IOS

## rmi
- remote method invocation，只针对java，跨机器的方法调用
- client调用server的特定方法，调用对象、调用方法、参数等序列化后传输给server，server进行反序列化，执行调用
- 调用结果序列化后再返回给client，client再反序列化成真正的结果
- 序列化、反序列化也叫编码、解码

## RPC
- remote procedure call，很多RPC框架是跨语言的(服务端、客户端可以是不同的语言)
- 模式：
    1. 定义一个接口说明文件：描述了对象、对象成员、接口方法的一系列信息
    2. 通过RPC框架所提供的编译器，将接口说明文件编译成具体语言文件
    3. 在客户端与服务器端分别引入RPC编译器所生成的文件，即可像调用本地方法一样调用远程方法
- webservice VS RPC
    - RPC框架性能被编解码的效率所影响，比如100个字节的文件，编码压缩后只有10字节，传输就会较快。WebService传输的量比较大，效率就较低。
    - RPC通过socket来传输，webservice通过http来传输，socket效率更高
    
## Protocol Buffers
- 是一种语言中立，平台中立，可扩展的用于序列化、结构化数据的机制
[官网](https://developers.google.com/protocol-buffers)
[文档](https://developers.google.com/protocol-buffers/docs/javatutorial)
[下载地址](https://github.com/protocolbuffers/protobuf/releases)    
    
    