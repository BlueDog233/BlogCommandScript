package cn.bluedog233.goodservice;


import cn.bluedog233.goodservice.parser.MainParser;
import cn.bluedog233.goodservice.varinject.ArrContainer;
import cn.bluedog233.goodservice.varinject.HashMapContainer;
import cn.bluedog233.goodservice.varinject.Prop;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 指令解析器:将字符串指令解析为指令对象,单例。应注册到Excutor的解析器容器中
 * 例:mail to=2052774863~title=标题~content=内容
 */
public abstract class CommandParser {

    protected String type;
    protected CommandParser(String type){
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
