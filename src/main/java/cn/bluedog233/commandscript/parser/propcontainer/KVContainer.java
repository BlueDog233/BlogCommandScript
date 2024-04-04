package cn.bluedog233.commandscript.parser.propcontainer;

public class KVContainer extends CanInject{

    private String name;

    public KVContainer(){

    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

}
