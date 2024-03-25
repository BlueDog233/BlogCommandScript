package cn.bluedog233;

import cn.bluedog233.commandscript.Context;
import cn.bluedog233.commandscript.Excutor;
import cn.bluedog233.commandscript.command.CustomCommand;

public class Main {
    public static void main(String[] args) {
        CustomCommand.addListener((context, command) -> {
            System.out.println("我是挂载的监听器");
        });
        Excutor.run("""
                test str={title},strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66),name=我是test1
                test str={#1},strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66)
                test strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66)
                ""","test",new Context());
        Excutor.run("""
                command test
                ""","test2",new Context());

    }
}