package cn.bluedog233.commandscript.varinject;

import java.util.ArrayList;
import java.util.List;

/*
    数组容器
 */
public class ArrContainer extends CanInject{

    List<HashMapContainer> hashMapContainers=new ArrayList<>();
    private String name;

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }


}
