# Wridpluins
 1,查询自动切换到从库
 2,扩展Mybatits Plugins
 解决主从数据不一致 脏读的问题    修改数据时  修改成功则 将表名作为key放入redis  1秒（可配置）   
 当读取数据时 主动切换到从库. 解析查询sql中的表名作为key 查询redis    
 如果redis 中无该key 则继续读从库，  
 如果redis 中有key 说明该表1秒内有修改过数据,切换db到主库，读主库
 
 1,查询自动切换到从库:  DataSourceAspect.java
 2, 修改数据时  修改成功则 将表名作为key放入redis  1秒 : MasterUpdateInterceptor.java
 3,当读取数据时 主动切换到从库. 解析查询sql中的表名作为key 查询redis    
    如果redis 中无该key 则继续读从库，  
    如果redis 中有key 说明该表1秒内有修改过数据,切换db到主库，读主库:WriteReadInterceptor.java
 
 测试入口：
TestController
1./updateAndSelect?id=1  验证有事务且查询参与事务的情况
2./updateAndSelectNoT?id=1  验证无事务的情况
3./updateAndSelectNotS?id=1 验证有事务但查询不参与事务的情况
