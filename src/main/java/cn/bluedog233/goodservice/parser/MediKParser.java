package cn.bluedog233.goodservice.parser;

import cn.bluedog233.goodservice.varinject.HashMapContainer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MediKParser extends Parser{
    HashMapContainer hashMap=new HashMapContainer();
    public MediKParser(Deque<Character> role) {
        super(']', role);
    }
    public HashMapContainer parser(){
        while (getDeque().size()>0) {
            char c = getDeque().getFirst();
            if (c == ']') {
                getDeque().pop();
                break;
            }else{
                String[] dot=new DotParser(getDeque(),this).parse();
                hashMap.getProps().put(dot[0],dot[1]);
            }
        }
        List<String> waitRemove=new ArrayList<>();
        hashMap.getProps().keySet().stream().filter(k->
                hashMap.getProps().get(k).contains("{")
        ).forEach(k->{
            hashMap.getPropsPoint().put(k,hashMap.getProps().get(k));
            waitRemove.add(k);
        });
        waitRemove.forEach(k->{
            hashMap.getProps().remove(k);
        });
        return hashMap;
    }
}
