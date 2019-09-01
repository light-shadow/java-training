ISP: 接口隔离原则（符合高内聚、低耦合）
    用多个专门的接口，而不使用单一的接口，客户端不应该依赖它不需要的接口。
注意点：
    1、一个类对一类的依赖应该建立在最小的接口之上。
    2、建立单一接口，不要建立庞大臃肿的接口。
    3、尽量细化接口，接口中的方法尽量少（不是越少越好，要适度）