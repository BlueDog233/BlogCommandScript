package cn.bluedog233;

import cn.bluedog233.commandscript.Context;
import cn.bluedog233.commandscript.Excutor;
import cn.bluedog233.commandscript.command.CustomCommand;

public class Main {
    public static void main(String[] args) {
        CustomCommand.addListener((context, command) -> {
            System.out.println("我是挂载的监听器");
        });
        Excutor.parse("""
                test str="str1",strs=("str2","str3",{title}),strmap=[k1=666,k2="555",k3={title}]
                test str={#1}
                ""","test").execute(new Context());
        Excutor.parse("""
                command test
                ""","test2").execute(new Context());

    }
    }
