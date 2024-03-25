package cn.bluedog233.goodservice.varinject;


import cn.bluedog233.goodservice.Context;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 该类是每个Command都有,用于存储指令信息
 */

public class Prop extends CanInject {
    public List<ArrContainer> getArrContainers() {
        return arrContainers;
    }

    public void setArrContainers(List<ArrContainer> arrContainers) {
        this.arrContainers = arrContainers;
    }

    public List<HashMapContainer> getHashMapContainers() {
        return hashMapContainers;
    }

    public void setHashMapContainers(List<HashMapContainer> hashMapContainers) {
        this.hashMapContainers = hashMapContainers;
    }

    private List<ArrContainer> arrContainers = new ArrayList<>();
    private List<HashMapContainer> hashMapContainers = new ArrayList<>();

}
