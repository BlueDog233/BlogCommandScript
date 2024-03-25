package cn.bluedog233.commandscript.command;

import cn.bluedog233.commandscript.common.Result;
import cn.bluedog233.commandscript.Command;
import cn.bluedog233.commandscript.Context;
import cn.bluedog233.commandscript.annotation.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TestCommand extends Command {
    @Inject
    private String name;
    @Inject String str;
    @Inject
    private List<String> strs;
    @Inject
    private HashMap<String,String> strmap;
    @Override
    public Result func(Context context) throws IOException {
        System.out.println(str+" "+strs+" "+strmap);
        return Result.success("我是"+name+"的执行返回结果");
    }
}
