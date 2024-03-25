package cn.bluedog233.goodservice.varinject;

import cn.bluedog233.goodservice.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapContainer extends CanInject{

    private String name;

    public HashMapContainer(){

    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

}
