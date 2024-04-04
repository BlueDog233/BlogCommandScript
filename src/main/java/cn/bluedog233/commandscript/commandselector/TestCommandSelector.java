package cn.bluedog233.commandscript.commandselector;

import cn.bluedog233.commandscript.Command;
import cn.bluedog233.commandscript.CommandBuilder;
import cn.bluedog233.commandscript.CommandSelector;
import cn.bluedog233.commandscript.command.TestCommand;
import cn.bluedog233.commandscript.parser.propcontainer.Prop;

public class TestCommandSelector extends CommandSelector {

    public TestCommandSelector() {
        super("test");
    }

    @Override
    public Command parseD(Prop props) {
        return CommandBuilder.build(TestCommand.class,props);
    }
}
