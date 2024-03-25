package cn.bluedog233.commandscript;



import cn.bluedog233.commandscript.common.Result;
import cn.bluedog233.commandscript.annotation.Inject;
import cn.bluedog233.commandscript.annotation.NotProptoInject;
import cn.bluedog233.commandscript.varinject.Prop;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 抽象指令类,传入Context,填充入当下字段,执行指令。
 * 单例
 */
public abstract class Command {
    private Prop prop;
    private String orginLine;
    private HashMap<Field,Object> cache=new HashMap<>();//用于存储已经注入的变量
    public abstract Result func(Context context) throws IOException;
    //运行时注入变量(注入props,history,private)给propsPoint,与hashmap容器,数组容器,以
    public void inject(Context context) throws IllegalAccessException {
        //反射获取所有开头非_的字段值,并存储入_cache,用于缓存
        for (Field field : this.getClass().getDeclaredFields()) {
            if(field.getAnnotation(Inject.class)==null)
                continue;
            field.setAccessible(true);
            cache.put(field,field.get(this));
        }
        HashMap<String,String> waitInjectStr=new HashMap<>();
        HashMap<String,HashMap> waitInjectHashMap=new HashMap<>();
        HashMap<String,List> waitInjectList=new HashMap<>();
        for(Field key:Context.class.getDeclaredFields()){
            if(key.getAnnotation(NotProptoInject.class)!=null)
                continue;
            if(key.getType()==String.class){
                key.setAccessible(true);
                try {
                    waitInjectStr.put(key.getName(),(String)key.get(context));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else if(key.getType()==HashMap.class){
                key.setAccessible(true);
                try {
                    HashMap<String,String> map=(HashMap<String,String>)key.get(context);
                    map.keySet().forEach(k->{
                        waitInjectStr.put(k,map.get(k));
                    });
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        prop.getProps().keySet().forEach(k->waitInjectStr.put(k,prop.getProps().get(k)));
        prop.getPropsPoint().keySet().forEach(k->{
            String s = prop.getPropsPoint().get(k);
            String v=s.substring(1,s.length()-1);
            if(waitInjectStr.containsKey(v)) {
                waitInjectStr.put(k, waitInjectStr.get(v));
            }
        });
        prop.getHashMapContainers().forEach(h->{
            HashMap<String,String> props=new HashMap<>();
            props.putAll(h.getProps());
            h.getPropsPoint().keySet().forEach(ke->{
                String s = h.getPropsPoint().get(ke);
                String v=s.substring(1,s.length()-1);
                if(waitInjectStr.containsKey(v)) {
                    props.put(ke, waitInjectStr.get(v));
                }
            });
            waitInjectHashMap.put(h.getName(),props);
        });
        prop.getArrContainers().forEach(l->{
            List<String> props=new ArrayList<>();
            props.addAll(l.getProps().keySet().stream().toList());
            l.getPropsPoint().keySet().forEach(ke->{
                String v=ke.substring(1,ke.length()-1);
                if(waitInjectStr.containsKey(v)) {
                    props.add(waitInjectStr.get(v));
                }
            });
            waitInjectList.put(l.getName(),props);
        });
        for (Field field : cache.keySet()) {
            if(field.getAnnotation(NotProptoInject.class)!=null)
                continue;
            //注入到Command类自身字段属性
            try {

                switch (field.getType().getName()) {
                    case "java.lang.String":
                        String strValue = waitInjectStr.get(field.getName());
                        if (strValue != null) {
                            field.set(this, strValue);
                        }
                        break;
                    case "java.util.HashMap":
                        HashMap<?, ?> mapValue = waitInjectHashMap.get(field.getName());
                        if (mapValue != null) {
                            field.set(this, mapValue);
                        }
                        break;
                    case "java.util.List":
                        List<?> listValue = waitInjectList.get(field.getName());
                        if (listValue != null) {
                            field.set(this, listValue);
                        }
                        break;
                    default:
                        break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
    public void recover(){
        cache.forEach((field, o) -> {
            try {
                field.set(this,o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }
    public void setProp(Prop prop){
        this.prop=prop;
    }
    public Result execute(Context context)  {
        try {
            inject(context);

            Result result=func(context);
            recover();
            return result;
        }  catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOrginLine(String orginLine){
        this.orginLine=orginLine;
    }
    public String getOrginLine(){
        return orginLine;
    }

}
