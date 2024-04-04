package cn.bluedog233.commandscript.parser.propcontainer;


import java.util.HashMap;

public abstract class CanInject {
    public HashMap<String, String> getProps() {
        return props;
    }

    public void setProps(HashMap<String, String> props) {
        this.props = props;
    }

    public HashMap<String, String> getPropsPoint() {
        return propsPoint;
    }

    public void setPropsPoint(HashMap<String, String> propsPoint) {
        this.propsPoint = propsPoint;
    }

    public HashMap<String, String> getCache() {
        return cache;
    }

    public void setCache(HashMap<String, String> cache) {
        this.cache = cache;
    }

    private HashMap<String,String> props=new HashMap<>();
    private HashMap<String,String> propsPoint=new HashMap<>();
    private HashMap<String,String> cache=new HashMap<>();//用于存储已经注入的变量
}
