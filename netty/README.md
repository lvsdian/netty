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
- 下载后配置环境变量，即可使用protoc命令，使用protoc运行.proto文件即可生成源代码文件
- 对于同一个proto文件中可能会定义多个message，在传输时具体传输的message需要按需求变化，解决方案可以参考MyDataInfo.proto中定义的MyMessage，
MyMessage中指定data_type，在传输时指定类型为MyMessage，实际传输的message根据MyMessage的data_type获取。示例可见cn.andios.netty.sixth
- 对于MyDataInfo.proto生成的java文件，服务端和客户端都需要调用，如何让它们都可以调用?(假设项目基于git管理)
1. 利用`git submodule`(git外仓库的一个里层仓库)，假设把MyDataInfo.proto生成的java文件放到`ProtoBuf-Java`项目中，让服务端和客户端都引用这个项目。
- 缺点：
    1. 项目分支一般分为：develop、test、master，服务端、客户端、ProtoBuf-Java可能都有多个分支。服务端、客户端、ProtoBuf-Java等项目的分支都要一一对应，即
    服务端develop分支对应ProtoBuf-Java项目develop分支，不能对应ProtoBuf-Java项目master分支。而在分支切换时，由于疏忽往往切换外层项目分支，没有切换里层项目分支
    2. 比如客户端引入了ProtoBuf-Java项目，客户端中修改了ProtoBuf-Java项目文件，把结果推送到ProtoBuf-Java项目远程，此时git submodule也可能会产生一些问题
2. 利用`git subtree`，将ProtoBuf-Java项目拉取到客户端或者服务端中，与`git submodule`不同的是，此时它们属于同一个项目，而不是两个仓库。此时就不存在外层仓库
与里层仓库分支不一样的情况。
3. 每次修改都把ProtoBuf-Java项目打成jar包放到私服里，方法可行，但每次修改都要改变版本号，修改pom.xml等文件，比较麻烦
    
