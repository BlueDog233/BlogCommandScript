package cn.bluedog233.commandscript.parser;

import java.util.Deque;

public class EqulsParser extends Parser{
    public EqulsParser(Deque<Character> role) {
        super('=', role);
    }
    public String parse(){
        StringBuilder sb=new StringBuilder();
        while (getDeque().size()>0){
            char c=getDeque().pop();
            if(c==','){
                continue;
            }
            if(c=='='){
                break;
            }
            sb.append(c);
        }
        return sb.toString();

    }
}
