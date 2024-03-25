package cn.bluedog233.commandscript;


import cn.bluedog233.commandscript.command.CustomCommand;

/**
 * 监听器,函数式方法,用于监听事件
 */
public interface Listener {
    public void handle(Context context, CustomCommand command);
}
