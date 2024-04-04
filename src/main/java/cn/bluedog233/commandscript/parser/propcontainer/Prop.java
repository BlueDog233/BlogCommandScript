package cn.bluedog233.commandscript.parser.propcontainer;


import java.util.ArrayList;
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

    public List<KVContainer> getHashMapContainers() {
        return hashMapContainers;
    }

    public void setHashMapContainers(List<KVContainer> hashMapContainers) {
        this.hashMapContainers = hashMapContainers;
    }

    private List<ArrContainer> arrContainers = new ArrayList<>();
    private List<KVContainer> hashMapContainers = new ArrayList<>();

}
