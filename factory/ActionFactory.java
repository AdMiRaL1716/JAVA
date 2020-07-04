package factory;

import command.creater.CreaterClassEnum;
import interfaces.ActionCommand;

public class ActionFactory {
    public ActionCommand defineCommand(String command){
        CreaterClassEnum createrClassEnum = CreaterClassEnum.valueOf(command.toUpperCase());
        ActionCommand currentCommand = createrClassEnum.getActionCommand();
        return currentCommand;
    }
}
