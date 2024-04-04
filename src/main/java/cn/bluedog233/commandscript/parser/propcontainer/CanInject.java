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


    private HashMap<String,String> props=new HashMap<>();
    private HashMap<String,String> propsPoint=new HashMap<>();
}
