package cn.bluedog233.goodservice.parser;

import cn.bluedog233.goodservice.varinject.ArrContainer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class LittleKParser extends Parser{
    private ArrContainer arrContainer=new ArrContainer();
    public LittleKParser(Deque<Character> role) {
        super(')', role);
    }
    public ArrContainer parse(){
        while (getDeque().size()>0) {
            char c = getDeque().pop();
            if (c == ')') {
                break;
            }else{
                getDeque().push(c);
                String[] dot=new DotParser(getDeque(),this).parse();
                arrContainer.getProps().put(dot[0],null);
            }
        }
        List<String> waitRemove=new ArrayList<>();
        arrContainer.getProps().keySet().stream().filter(k->
                k.contains("{")
        ).forEach(k->{
            waitRemove.add(k);
            arrContainer.getPropsPoint().put(k,null);
        });
        waitRemove.forEach(k->{
            arrContainer.getProps().remove(k);
        });
        return arrContainer;
    }
}
