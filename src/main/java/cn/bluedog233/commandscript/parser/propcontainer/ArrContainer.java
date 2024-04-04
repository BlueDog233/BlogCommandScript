package cn.bluedog233.commandscript.parser.propcontainer;

import java.util.ArrayList;
import java.util.List;

/*
    数组容器
 */
public class ArrContainer extends CanInject{

    private String name;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
