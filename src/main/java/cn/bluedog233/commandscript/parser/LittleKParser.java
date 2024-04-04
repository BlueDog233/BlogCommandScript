package cn.bluedog233.commandscript.parser;

import cn.bluedog233.commandscript.parser.propcontainer.ArrContainer;

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
        final HashMap<String, String> props = arrContainer.getProps();
        while (getDeque().size()>0) {
            char c = getDeque().pop();
            if (c == ')') {
                break;
            }else{
                getDeque().push(c);
                String[] dot=new DotParser(getDeque(),this).parse();
                props.put("arr"+props.size(),dot[0]);
            }
        }
        List<String> waitRemove=new ArrayList<>();
        props.keySet().stream().filter(k->
                k.contains("{")
        ).forEach(k->{
            waitRemove.add(k);
            arrContainer.getPropsPoint().put(k,props.get(k));
        });
        waitRemove.forEach(k->{
            props.remove(k);
        });
        return arrContainer;
    }
}
