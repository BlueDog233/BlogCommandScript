package cn.bluedog233.commandscript;

import cn.bluedog233.commandscript.varinject.Prop;

/**
 * 指令Builder类,直接BuildCommand
 */
public class CommandBuilder{
    public static <T extends Command> T build(Class<T> clazz, Prop prop) {
        try {
            T command = clazz.newInstance();
            command.setProp(prop);
            return command;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
