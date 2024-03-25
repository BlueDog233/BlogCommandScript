package cn.bluedog233;

import cn.bluedog233.goodservice.Context;
import cn.bluedog233.goodservice.Excutor;
import cn.bluedog233.goodservice.command.CustomCommand;

public class Main {
    public static void main(String[] args) {
        CustomCommand.addListener((context, command) -> {
            System.out.println(command.getShortName());
        });
        Excutor.run("""
                test str={title},strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66)
                test str={#1},strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66)
                test strmap=[t1=sda,t2=dads,t3={str}],strs=(666,66)
                ""","test",new Context());


    }
}