package cn.bluedog233;

import cn.bluedog233.commandscript.Context;
import cn.bluedog233.commandscript.Excutor;
import cn.bluedog233.commandscript.command.CustomCommand;

public class Main {
    public static void main(String[] args) {
        CustomCommand.addListener((context, command) -> {
            System.out.println("我是挂载的监听器");
        });
        CustomCommand test = Excutor.parse("""
                test str={test1},strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66),name="test1"
                test str={#1},strmap=[t1="sda sdadsadas",t2=dads,t3={str}],strs=(666,66),name="test2"
                test strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66,"dsadasfsc sadasdas sadasdas",666,{#2})
                """, "test");
        TestContext testContext=new TestContext();
        testContext.setTest1("我来自环境变量");
        test.execute(testContext);
    }
}
