package cn.bluedog233.goodservice.command;




import cn.bluedog233.common.Result;
import cn.bluedog233.goodservice.Command;
import cn.bluedog233.goodservice.Context;
import cn.bluedog233.goodservice.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 自定义指令类,可以添加多个指令,并且可以添加监听器
 * short为唯一定位,该单例注册到CustomCommandParser中
 * 会同时执行多个指令,然后执行监听器内的方法
 * 监听器为静态属性
 */
public class CustomCommand extends Command {
    private Context context;
    private List<Command> commands=new ArrayList();
    private static List<Listener> listeners=new ArrayList();
    private String shortName;
    //添加监听器的静态方法
    public static void addListener(Listener listener){
        listeners.add(listener);
    }
    public CustomCommand(String shortName) {
        this.shortName=shortName;
    }
    public void addCommand(Command command){
        commands.add(command);
    }

    @Override
    public Result func(Context context) {
        return Result.success();
        //无props可转,CustomCommand不需要什么props
    }

    @Override
    public Result execute(Context context) {

        int index=0;
        for (Command command : commands) {
            Result result=command.execute(context);
            context.putHistory(index,result);
        }
        //监听器执行操作
        for(Listener listener:listeners){
            listener.handle(context,this);
        }
        return Result.success();
    }
    public String getShortName(){
        return shortName;
    }
}
