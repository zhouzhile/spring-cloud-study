### 复习Nettyy时创建
#### 1.初步实现Netty的发送与回复消息
* 创建ClientSimpleHander、ServerSimpleHandler
* 消息发送时，将消息转换成ByteBuf
* 问题: 如果在for循环内发送消息，会出现粘包、拆包现象。如图：
![img.png](img.png)
#### 2.粘包、拆包问题处理