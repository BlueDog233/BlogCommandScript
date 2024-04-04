package cn.bluedog233.commandscript;


import cn.bluedog233.commandscript.command.CustomCommand;
import cn.bluedog233.commandscript.commandselector.CustomCommandSelector;
import cn.bluedog233.commandscript.commandselector.TestCommandSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行类,所有指令操作在这里完成
 */
public class Excutor {
    //主扫描路径
    public static String mainPath="cn.bluedog233.commandscript";
    //指令缓存池
    private static List<Command> pools=new ArrayList<>();
    //指令选择器List
    private static List<CommandSelector> commandSelectors =new ArrayList<>(){{
            add(new TestCommandSelector());
    }};
    //注册指令解析器
    public static void registerCommandParser(CommandSelector commandSelector) {
        commandSelectors.add(commandSelector);
    }
    //解析多行文本,最后创建一个新的CustomCommand
    public static CustomCommand parser(String commandLines, String shortName) {
        CustomCommand customCommand = new CustomCommand(shortName);
        String[] tokens = commandLines.split("\n");
        for (String commandLine : tokens) {
            final String[] s = commandLine.split(" ",2);
            if (s.length < 2) throw new RuntimeException("参数填写错误");
            if (s.length >= 2) {
                String type = s[0];
                String commandLine_t = s[1];
                Command command;
                command = getCommand(type, commandLine_t);
                if (command!=null) {
                    customCommand.addCommand(command);
                } else {
                    continue;
                }
            }
        }
        //自定义解析器中注册
        CustomCommandSelector.registerCustomCommand(customCommand);
        return customCommand;

    }

    private static Command getCommand(String type,String commandLine_t) {
        //缓存池取
        for (Command c:pools){
            if(c.getOrginLine().equals(type+" "+commandLine_t))
                return c;
        }
        for (CommandSelector commandSelector : commandSelectors) {
            if(!commandSelector.type.equals(type))
                continue;
            Command command = commandSelector.parse(commandLine_t);
            if (command != null) {
                return command;
            }
        }
        return null;
    }


    public static void run(String commandLines,String shortName,Context context) {
        parser(commandLines,shortName).execute(context);
    }
}
