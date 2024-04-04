# 更新计划
> 新型指令:
> 
> 风格1
> > getemail sesstion={session},name={name}
> >
> > *send to={#1},message=helloworld,title="this is title"
> >
> > *do email={#1}
> >
> 风格2
> > getemail {session} {name}
> >
> > *send {#1} helloworld "this is title"
> >
> > *do email={#1}
## 重写解析器部分:
-  重构基础架构
- ""解析
- 基本运算解析
## 插件加载器部分
- 插拔式加载
- 指令管理(Bean,Scan机制)
- CommandPaser,Command,Listener注解一键注册
## 并发操作支持
- 指令重编排,池化
## 统一自定义命令加载器
> 加载指定资源路径使用txt编写的自定义命令

[//]: # (## 基本运算指令)

[//]: # (- ifdo指令)

[//]: # (- for指令)

[//]: # (- while指令)