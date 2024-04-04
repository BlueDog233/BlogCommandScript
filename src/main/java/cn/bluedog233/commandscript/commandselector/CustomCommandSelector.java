package cn.bluedog233.commandscript.commandselector;


import cn.bluedog233.commandscript.Command;
import cn.bluedog233.commandscript.CommandSelector;
import cn.bluedog233.commandscript.command.CustomCommand;
import cn.bluedog233.commandscript.parser.propcontainer.Prop;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义指令解析器,
 * 通过shortname字段来识别需要自定义执行哪个方法
 */
public class CustomCommandSelector extends CommandSelector {
    public static final String PROPS_SHORTNAME="shortname";
    public static List<CustomCommand> customCommands=new ArrayList<>();
    //注册自定义指令解析器
    public static void registerCustomCommand(CustomCommand customCommand) {
        customCommands.add(customCommand);
    }
    public CustomCommandSelector() {
        super("command");
    }



    @Override
    public Command parseD(Prop props) {
        for(CustomCommand customCommand:customCommands){
            if(customCommand.getShortName().equals(props.getProps().get(PROPS_SHORTNAME))){
                return customCommand;
            }
        }
        return null;
    }
}
