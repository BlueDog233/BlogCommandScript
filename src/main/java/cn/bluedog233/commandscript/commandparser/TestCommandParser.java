package cn.bluedog233.commandscript.commandparser;

import cn.bluedog233.commandscript.Command;
import cn.bluedog233.commandscript.CommandBuilder;
import cn.bluedog233.commandscript.CommandParser;
import cn.bluedog233.commandscript.command.TestCommand;
import cn.bluedog233.commandscript.varinject.Prop;

public class TestCommandParser extends CommandParser {

    public TestCommandParser() {
        super("test");
    }

    @Override
    public Command parseD(Prop props) {
        return CommandBuilder.build(TestCommand.class,props);
    }
}
