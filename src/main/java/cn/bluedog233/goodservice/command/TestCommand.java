package cn.bluedog233.goodservice.command;

import cn.bluedog233.common.Result;
import cn.bluedog233.goodservice.Command;
import cn.bluedog233.goodservice.Context;
import cn.bluedog233.goodservice.annotation.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TestCommand extends Command {
    @Inject
    private String str;
    @Inject
    private List<String> strs;
    @Inject
    private HashMap<String,String> strmap;
    @Override
    public Result func(Context context) throws IOException {
        System.out.println(str+" "+strs+" "+strmap);
        return Result.success("成功啦");
    }
}
