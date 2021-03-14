# spring-cloud-study
1.在SpringCloud集成Zookeeper时候，需要注意ZK的本地依赖版本与ZK服务的版本是否一致。
2.添加上ZK的依赖，启动报错：
   ① ZK版本不一致
   ② 去掉ZK以来中的log4j、netty等包，再单独引入