package cn.bluedog233.goodservice.commandparser;

import cn.bluedog233.goodservice.Command;
import cn.bluedog233.goodservice.CommandBuilder;
import cn.bluedog233.goodservice.CommandParser;
import cn.bluedog233.goodservice.command.TestCommand;
import cn.bluedog233.goodservice.varinject.Prop;

public class TestCommandParser extends CommandParser {

    public TestCommandParser() {
        super("test");
    }

    @Override
    public Command parseD(Prop props) {
        return CommandBuilder.build(TestCommand.class,props);
    }
}
