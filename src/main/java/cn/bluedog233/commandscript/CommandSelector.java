package cn.bluedog233.commandscript;


import cn.bluedog233.commandscript.parser.MainParser;
import cn.bluedog233.commandscript.parser.propcontainer.Prop;

import java.util.*;

/**
 * 指令解析器:将字符串指令解析为指令对象,单例。应注册到Excutor的解析器容器中
 * 例:mail to=2052774863~title=标题~content=内容
 */
public abstract class CommandSelector {

    protected String type;
    protected CommandSelector(String type){
        this.type=type;
    }
    //字符串解析为props
    public Command parse(String commandLine_t){
        Deque<Character> deque=new ArrayDeque();
        for (char c : commandLine_t.toCharArray()) {
            deque.offer(c);
        }
        //prop解析
        Prop prop=new MainParser(deque).parse();
        Command command = parseD(prop);
        command.setOrginLine(type+" "+commandLine_t);
        return command;
    }
    //实现的类提供props,自己解析指令
    public abstract Command parseD(Prop props);
}
