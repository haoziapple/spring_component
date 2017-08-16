# springboot 项目原型(快速搭建)

## 2.0.0版本新增功能
- 多数据源(单个数据源中支持事务)->check
- 支持多种打包方式(jar包与war包的方式)->check
- 自定义过滤器->check
- 支持开发模式热更新->unsolved(添加spring-boot-devtools依赖后启动报错)
- 支持方法的异步调用(自定义线程池)->check
- redis消息发布的封装->check(使用pub/sub,反射调用spring容器内的方法)
- 测试相关(打包跳过测试)->TODO:springboot的集成测试->check
- 允许跨域访问(CrossOrigins)->check