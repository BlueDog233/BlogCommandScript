package cn.bluedog233.goodservice;

import cn.bluedog233.goodservice.varinject.HashMapContainer;
import cn.bluedog233.goodservice.varinject.Prop;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

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
