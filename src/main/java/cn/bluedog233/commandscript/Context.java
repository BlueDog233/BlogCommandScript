package cn.bluedog233.commandscript;

import cn.bluedog233.commandscript.common.Result;
import cn.bluedog233.commandscript.annotation.NotProptoInject;

import java.util.HashMap;

/**
 * 执行环境类,其中存放执行所需要的属性,需要时自动注入
 */
public class Context {
    public String title="dsadsa";
    HashMap<String,String> props=new HashMap<>();//可扩展环境
    @NotProptoInject
    HashMap<Integer,Result> history=new HashMap<>();

    //其他环境
    public void put(String key,String value){
        props.put(key,value);
    }
    public String get(String key){
        return props.get(key);
    }
    public void putHistory(int key,Result value){
        key++;
        props.put("#"+key,value.getMessage());
        history.put(key,value);
    }
    public Result getHistory(String key){
        return history.get(key);
    }
}
