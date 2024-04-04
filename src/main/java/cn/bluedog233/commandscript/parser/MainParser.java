package cn.bluedog233.commandscript.parser;

import cn.bluedog233.commandscript.parser.propcontainer.ArrContainer;
import cn.bluedog233.commandscript.parser.propcontainer.KVContainer;
import cn.bluedog233.commandscript.parser.propcontainer.Prop;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MainParser extends Parser{
    Prop prop=new Prop();

    public MainParser(Deque<Character> role) {
        super(' ', role);

    }
    public Prop parse(){
        while (getDeque().size()>0){
            char c=getDeque().getFirst();
            if(c==',')
                c=getDeque().pop();
            String key = new EqulsParser(getDeque()).parse();
            c=getDeque().getFirst();
            switch (c){
                case '[':
                    getDeque().pop();
                    KVContainer hash=new MediKParser(getDeque()).parser();
                    hash.setName(key);
                    prop.getHashMapContainers().add(hash);
                    break;
                case '(':
                    getDeque().pop();
                    ArrContainer arr=new LittleKParser(getDeque()).parse();
                    arr.setName(key);
                    prop.getArrContainers().add(arr);
                    break;
                default:
                    String[] dot=new DotParser(getDeque(),this).parse();
                    prop.getProps().put(key,dot[0]);
                    break;
            }
        }
        List<String> waitRemove=new ArrayList<>();
        prop.getProps().keySet().stream().filter(k->
          prop.getProps().get(k).contains("{")
        ).forEach(k->{
            prop.getPropsPoint().put(k,prop.getProps().get(k));
            waitRemove.add(k);
        });
        waitRemove.forEach(k->{
            prop.getProps().remove(k);
        });
        return prop;
    }
}
