package cn.bluedog233.goodservice;


import cn.bluedog233.goodservice.command.CustomCommand;

import java.util.stream.Stream;

/**
 * 监听器,函数式方法,用于监听事件
 */
public interface Listener {
    public void handle(Context context, CustomCommand command);
}
