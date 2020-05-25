[toc]

# Reactor

用于同步事件的多路分解和分发处理的对象行为模式

## 意图

- `Reactor`设计模式是指处理由一个或多个客户端并发的向一个应用发送服务请求。应用中的每个服务可能由几个方法组成，并且由一个单独的事件处理器表示，事件处理器用于将特定于服务的请求进行分发。事件处理器的分发有一个`initiation dispatcher`来完成，它会管理注册的事件处理器。服务请求的分离是由一个同步的事件分离器来执行的。

##别称

- 分发器、通知器

## 实例

- 为了说明`Reactor`模式，设计了图1中展示的基于事件驱动的分布式日志服务。客户端应用在一个分布式环境中使用日志服务记录它们的状态信息。这些状态信息通常包括错误通知、调试追踪信息、性能报告等。日志记录被发送到一个中央日志服务器中，这个中央日志服务器可以将记录写到多个输出设备中，比如控制台，打印机，文件或者网络管理数据库。
- 图1中展示的日志服务器处理日志记录和客户端发色的连接请求。日志记录和连接请求可能在多个处理器中同时到达。处理器标识操作系统管理的网络通信资源。
- ![](../img/figure_1.png)
- 日志服务器和客户端通过使用一个面向连接的协议进行通信，比如`TCP`。要记录数据的客户端必须先向服务器发送一个连接请求。服务器使用一个`handler factory`来等待这些连接请求，`handler factory`监听客户端已知的地址。当一个连接请求到达时，`handler factory`在客户端和服务器之间通过创建一个新的处理器建立一个连接，这个处理器代表连接的终结点。这个处理器返回到服务器，然后服务器等待客户端服务请求到达处理器。一旦客户端连接，它们可以并发的向服务器发送日志记录，服务器通过连接的`socket `句柄接收这些记录。
- 或许开发一个并发的日志服务器最直观的方法是使用多线程，因为它可以处理多个客户端并发，比如图2中展示的。这种方法同步的接收网络连接，并且生成一个“每个连接一个线程”来处理客户端的日志记录
- ![](../img/figure_2.png)
- 然而，在服务器中使用多线程来实现日志记录的处理无法解决如下问题。
  - **`Efficiency`**：由于上下文切换、同步、数据移动可能会导致性能下降
  - **`Programming simplicity`**：线程可能需要复杂的并发控制方案
  - **`Portability(可移植性)`**：线程不是在所有操作系统平台上都可用

- 由于这些缺点，多线程通常不是开发日志服务器最有效的或者最简单的解决方案

## 上下文

- 在一个分布式系统中一个服务器应用并发的从一个或多个客户端中接收事件

## 问题

- 一个分布式系统中的服务器应用必须处理来自多个客户端发送的服务请求，然而在调用一个具体的服务之前，服务器应用必须将每个到来的请求多路分离并且分发给对应的服务提供者。为了多路分解和分发客户端请求开发的高效服务机制需要解决以下问题：
  - **`Availability`**：即使服务器在等待其它请求的到达，它也可以处理到来的请求。尤其是，服务器在排除其他事件源的情况下不能无限期的阻塞处理任一单一事件源，因为这个可能会显著延迟对其他客户端的响应
  - **`Efficiency`**：服务器必须最小化延迟，最大化吞吐量，并且避免不必要的使用`CPU`
  - **`Programming simplicity`**：服务器的设计应该简化适当并发策略的使用
  - **`Adaptability`**：集成新的服务或者改进服务，比如改变信息格式或者添加服务器端缓存，应该最小化对原有代码的更改以及维护成本。例如实现新的应用服务不应该需要修改原有的多路复用和请求分发机制。
  - **`Portability`**：将服务器移植到新的操作系统上不需要太多的工作

## 解决方案

- 将事件的同步多路复用、事件处理相应的事件处理器的分发整合起来，另外，将特定于应用的分发和服务实现与通用事件多路分解和分发机制分离
- 应用程序提供的每项服务，引入一个单独的`Event Handler`来处理确定类型的事件，所有的`Event Handler`实现同一个接口。`Event Handler`向`Initiation Dispatcher`注册，`Initiation Dispatcher`使用`Synchronous Event Demultiplexer`来等待事件的发生。当事件发生时，`Synchronous Event Demultiplexer`会通知`Initiation Dispatcher`，`Initiation Dispatcher`同步回调关联了事件的`Event Handler`，然后`Event Handler`将事件分发给实现了请求服务的方法。

## 结构

- `Reactor`模式主要的组成部件包括如下几点：

### Handles

- 标识被操作系统管理的资源，这些资源通常包括网络连接，打开的文件，定时器，同步对象等等。在日志服务器中`Handles`被用于标识`socket`终点，因此一个`Synchronous Event Demultiplexer`可以等待事件到达它们。日志服务器感兴趣的两种事件类型是连接事件和读事件，它们代表传入客户端的连接和记录数据的请求，日志服务器为每个客户端维护一个单独的连接，在服务器中每个连接用一个`socket handle`来表示。

### Synchronous Event Demultiplexer

- 在`Handles`集合上阻塞等待事件的发生。当可以在不阻塞的情况下在`Handle`上启动操作时返回。一般`IO`事件的多路复用器是`select`，`select`是一个由`UNIX`和`win32`系统平台提供的事件多路复用的系统调用。`select`调用表示哪些`Handles`可以在不阻塞应用程序进程的情况下对其同步调用操作

### Initiation Dispatcher

- 定义用于注册、溢出、分发`Event Handlers`的接口。最终，`Synchronous Event Demultiplexer`负责等待新事件发生。当它检测到新事件时，它会通知`Initiation Dispatcher`调用特定于应用的事件处理器。常见的事件包括连接接收事件，数据输入和输出事件以及超时事件。

### Event Handler

- 指定由钩子方法组成的接口，钩子方法抽象的表示服务特定于事件的分发操作，这个方法必须被特定于应用的服务实现

### Concrete Event Handler

- 实现了钩子方法，同时实现了应用中处理这些事件的方法。应用将`Concrete Event Handlers`注册到`Initiation Dispatcher`用以处理特定类型的事件。当这些事件到达时，`Initiation Dispatcher`回调合适的`Concrete Event Handler`的钩子方法

- 在日志服务器中有两个`Concrete Event Handler`：`Logging Handler`和`Logging Acceptor`。`Logging Handler`表示接收和处理日志记录。`Logging Acceptor`创建和连接`Logging Handlers`，`Logging Handlers`处理后续从客户端来的日志记录请求

- `Reactor`模式的组成部件如以下`OMT`类图所示：

  ![](../img/reactor_design.png)

## Dynamics

### 模块间的协作关系

- `Reactor`模式中各模块间的协作关系如下：
  - 当一个应用将`Concrete Event Handler`注册到`Initiation Dispatcher`上时，表示当在`Event Handler`关联的`Handle`上有事件发生时，`Initiation Dispatcher`会通知`Concrete Event Handler`。
  - `Initiation Dispatcher`要求每个`Event Handler`返回它内部的`Handle`。这个`Handle`表示操作系统的`Event Handler`
  - 当所有的`Event Handlers`都注册后，应用程序调用`handle events`来启动`Initiation Dispatcher`的事件循环。这时，`Initiation Dispatcher`结合每个已注册`Event Handler`的`Handle`，使用`Synchronous Event Demultiplexer`在这些`Handles`上来等待事件的发生。比如，`TCP`协议层使用`select`同步事件多路复用操作在已连接的`socket handles`来等待客户端日志记录事件请求

