# OA树结构微服务

### 基本业务
- 提供OA系统中树结构的基本操作

### 维护人员
- 王昊

### 核心代码
- oa-struct-intf:暴露服务接口，通过远程调用进行操作
- RedisUtil:封装了redis的基本数据操作
- TreeStructUtil:接口类，定义了树操作的一些基本方法
- TreeByRedis：TreeStructUtil的实现类，底层个使用redis

### 更新历史
- 0.0.1:提供OA系统部门结构子树的查询操作