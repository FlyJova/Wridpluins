# Wridpluins
 扩展Mybatits Plugins    解决主从数据不一致 脏读的问题    修改数据时  修改成功则 将表名作为key放入redis  1秒（可配置）    当读取数据时 主动切换到从库. 解析查询sql中的表名作为key 查询redis    如果redis 中无该key 则继续读从库，    如果redis 中有key 说明该表1秒内有修改过数据,切换db到主库，读主库
