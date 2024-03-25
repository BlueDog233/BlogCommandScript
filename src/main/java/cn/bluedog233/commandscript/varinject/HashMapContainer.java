package cn.bluedog233.commandscript.varinject;

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
