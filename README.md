# ECS(EasyCommandScript)简单指令式脚本-JAVA中间件

> ## Github仓库链接
>
> https://github.com/BlueDog233/BlogCommandScript
>
> Maven中央仓库目前正在审核

第一次写博客,也是写的第一个中间件。文字与代码稍显稚嫩,多多海涵。谢谢大家。

## 开发动机

>之前写一个发卡网项目,我需要在支付成功后调用一些动作(如,请求接口,从数据库获取信息,发送邮箱)。因为每一个商品所调用的动作不一样,并且存在多个组合动作(比如说 购买apikey一类的商品我需要从数据库取apikey,然后将apikey发送到邮箱,有些时候又需要从其他接口取到数据,然后结合本地数据库中的信息,一起发到指定邮箱)。由于商品繁多并且每个商品的动作难以抽象(例如请求接口,携带header,请求地址,method均可能不同),所以打算采取一种,热更新的方式来增加购买商品后的动作组合。之前有想过使用python,JavaScript等脚本语言,但是对于这种需求,很明显是大材小用。后来就想到使用指令式的方式来完成这种组合动作,于是思考设计,对比之前接触过的指令机制(Minecraft指令,linux指令)之下,诞生了这个中间件。

## 这是什么

>## 0
>
>首先先理解我这里自己定义的这几个比较核心的概念
>
>1. 指令文本集(Command Texts): 一串或多串指令文本(说白了就是字符串),可以被ECS编译为指令(Command)
>2. 指令(Command): 可载入环境(Context)来运行,指令中包含了具体的运行逻辑,指令的变量参数标记,指令的常量参数
>3. 环境(Context): 顾名,运行指令的环境,其中也记录了多串字符串的指令的运行记录(这点来看,也可以叫做为"上下文"),指令的运行必须传入一个环境,环境将自己的变量注入到指令中
>
>这个中间件,最大概的工作流程是: 指令文本------编译------>指令-------运行------>指令运行结果,
>ECS允许你向其中便捷地添加自己的指令服务,并只需关注于指令逻辑本身,其余逻辑均已封装在该中间件中中
>
>相比于传统指令方式(特指Minecraft式)的特点:
>
>* 依赖注入开发
>* 保留执行上下文
>* 可挂载指令监听器
>* 有编译与运行过程(传统方式是直接运行)
>
>## 1
>
>### 依赖注入式开发
>
>指令开发风格类似于Spring的依赖注入Bean,看图秒懂![image-20240404200336179](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240404200336179.png)
>
>### 保留执行上下文
>
>在执行指令文本时,保留每行指令的执行结果,并将执行结果放入Context中,便于下一条指令调用,例如下面这段指令文本集中,有两行指令文本,第二行的指令文本通过str={#1} 来引用第一行指令文本的执行结果
>
>![image-20240404200658852](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240404200658852.png)
>
>### 可挂载监听器
>
>考虑到可能会有部分特殊需求,不能完全靠指令文本集实现。所以添加了监听器机制,在一条指令文本集执行完毕之后,开始调用注册的所有监听器,监听器中存储了当前Context以及当前Command
>
>![image-20240404201158753](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240404201158753.png)
>
>### 编译,运行机制
>
>允许同样一串指令文本,在不同的场景有不同的变量注入。
>例如
>"""
>mail sendto={inputone},context="666"
>"""
>
>这条指令,当传入的context里的inputone字段不同时,注入的属性不同,执行的效果就不同

## 快速上手

> - 这里我将以我发卡网项目作为例子,快速上手ECS的使用
>
> 额外补充的关键概念:
>
> * 执行器(Excutor): ECS中枢,用于调度编译器来编译指令集文本为指令
>
> * 指令选择器(CommandSelector),每个指令至少对应一个指令选择器,指令选择器用于创建指定指令,每个指令选择器有个type属性,该属性其实就是属性名,如TestCommandSelector,type就是"test",当指令文本第一个空格分割字段为test时调用该指令选择器。指令选择器用于构建一个指令对象(本质是 构建指令的参数,然后通过指令建造器将参数对象传进去构建出一个目标指令)
    >
    >   ![image-20240404204757933](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240404204757933.png)
>
> * 指令建造器(CommandBuilder): 用于组装指令,
    >
    >   ![image-20240404204715891](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240404204715891.png)
>
>

### 1 安装Maven

```xml
 <dependency> 
   <groupId>io.github.bluedog233</groupId>
    <artifactId>easy-commandscript</artifactId>
    <version>1.0</version>
</dependency>
```



### 2 确定需求

> 这里我们开发一个test指令,我将以test指令带大家快速掌握如何使用,并快速演示它的特性

### 3 编写TestCommand

```java
public class TestCommand extends Command {
    @Inject
    private String name;
    @Inject String str;
    @Inject
    private List<String> strs;
    @Inject
    private HashMap<String,String> strmap;
    @Override
    public Result func(Context context) throws IOException {
        System.out.println(str+" "+strs+" "+strmap);
        return Result.success("我是"+name+"的执行返回结果");
    }
}

```

> @Inject注解注释在Command中,用于标记哪些属性需要依赖注入,可以进行依赖注入的属性有,String,HashMap<String,String>,List<String>类型的属性。会在运行时在Prop与Context中寻找合适的属性进行注入
>
> func(Context context):Result 为运行逻辑,Result为运行结果对象
>
> ```java
> public class Result<T> {
>     private int code;
>     private String message;
>     private T data;
>     public static <T> Result<T> success(String str) {
>         return new Result<T>(1, str, null);
>     }
>     public static <T> Result<T> success() {
>         return new Result<T>(1, "", null);
>     }
>     public static <T> Result<T> success(String message, T data) {
>         return new Result<T>(1, message, data);
>     }
>     public static <T> Result<T> error(String message) {
>         return new Result<T>(0, message, null);
>     }
> 
> ```
>
> Result的规定:
>
> 1. code:0为结束,1为成功
> 2. message是提供给接下来指令调用的,也就是test中的{#1}: 没错,第一条指令的执行结果用{#1}表示,以此类推,第二次用{#2}
> 3. data是提供给监听器使用,用于更灵活处理更多信息
>
>

### 4 编写TestCommandSelector

```java
public class TestCommandSelector extends CommandSelector {
    static{
        //初始化,注册选择器
        Excutor.registerCommandSelector(new TestCommandSelector());
    }
    public TestCommandSelector() {
        super("test");//这里写指令名称,若指令名称是test则来到这个指令选择器
    }
    @Override
    public Command parseD(Prop props) {
        return CommandBuilder.build(TestCommand.class,props);//目前这里是固定写法,按这样写就好了,这里之后打算加入权限控制
    }
}

```

### 5 编写新的Context

```java
@Data
public class TestContext extends Context{
    String test1;
    String test2;
    String test3;
}
```

### 6 测试

```java
public class Main {
    public static void main(String[] args) {
        CustomCommand.addListener((context, command) -> {
            System.out.println("我是挂载的监听器");
        });
        CustomCommand test = Excutor.parse("""
                test str={test1},strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66),name="test1"
                test str={#1},strmap=[t1="sda sdadsadas",t2=dads,t3={str}],strs=(666,66),name="test2"
                test strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66,"dsadasfsc sadasdas sadasdas",666,{#2})
                """, "test");
        TestContext testContext=new TestContext();
        testContext.setTest1("我来自环境变量");
        test.execute(testContext);
    }
}
```

>一共有三种参数填写方式1.直接填写(k=v)，2.kv式填写(k={k1=v1,k2=v2}),3.数组式填写k=(v1,v2)。分别会对应Command中等待注入的String,HashMap,List,值得注意的是,=前面的值必须与等待注入的值相同。双引号可以省略,但是当有空格或逗号等特殊符号包含在字符串中时,必须使用双引号,变量使用{},引用上文结果使用{#Number}(引用第几行指令文本执行结果就写第几行)
>
>上面也演示了监听器注册方法

![image-20240405013128318](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405013128318.png)

## 依赖注入流程[推荐阅读]



## Some运行原理

> 额外补充的更多概念
>
> * 主指令(CustomCommand):继承了Command,用于装载很多个子指令,可以形象想为,是装载多行指令的容器,执行这条指令相当于执行多行小指令,所有指令执行,都是执行的主指令,挂载的监听器也是监听的主指令。每个主指令有个简写名,可用于重复调用。

原理图

![image-20240405014556355](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405014556355.png)



核心UML图

![image-20240405014701748](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405014701748.png)





</br>



## 真实业务代码中使用(我是怎么用它)

### 0 载入Maven

```xml
 <dependency> 
   <groupId>io.github.bluedog233</groupId>
    <artifactId>easy-commandscript</artifactId>
    <version>1.0</version>
</dependency>
```



### 1 确定目标

>我现在的发卡网中,每个商品对应了一个commandLines字段,这个字段的值将会是 命令文本集,在Springboot项目启动时,将其解析为CustomCommand在物品购买完成之后,执行命令。
>
>下面我们做一个,在购买完成之后：
>在本地文件中拿去apikey,然后发送邮箱给购买者的Command
>计划指令:
>
>getdata type=once,name=testapikey
>mail to={email},title="蓝泉 杂货铺",content={#1}

### 2 编写Command,mailCommand与getDataCommand,分别是发送文件与在本地拿取数据的Command

![image-20240405003931831](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405003931831.png)

![image-20240405004320124](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405004320124.png)

### 3 编写CommandSelector,用于识别命令名并创建命令

![image-20240405004445325](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405004445325.png)

![image-20240405004520675](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405004520675.png)

### 4 设计PayContext,用于保存Pay环境,并在

PayContext继承Context

![image-20240405005415732](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405005415732.png)

在接收到支付成功回调后进行指令操作,下面是关键代码

![image-20240405010129549](https://photofortypora.oss-cn-beijing.aliyuncs.com/image-20240405010129549.png)

然后购买后就可以正常执行业务流程了