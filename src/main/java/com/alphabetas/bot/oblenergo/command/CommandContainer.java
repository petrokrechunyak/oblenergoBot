package com.alphabetas.bot.oblenergo.command;

import com.alphabetas.bot.oblenergo.repo.GroupRepo;
import com.alphabetas.bot.oblenergo.service.MessageService;
import com.alphabetas.bot.oblenergo.repo.UserRepo;
import com.alphabetas.bot.oblenergo.utils.ScheduleUtil;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private final Map<String, Command> commands;
    private final MessageService messageService;

    // All Commands
    Command start, stop, unknown;

    public CommandContainer(MessageService messageService, UserRepo userRepo, GroupRepo groupRepo) {
        this.commands = new HashMap<>();
        this.messageService = messageService;

        start = new StartCommand(messageService, userRepo);
        stop = new StopCommand(messageService, userRepo);
        unknown = new UnknownCommand();

        new ScheduleUtil(messageService, userRepo, groupRepo);

        commands.put("/start", start);
        commands.put("/stop", stop);
    }

    public Command retrieveCommand(String command){
            return commands.getOrDefault(command.split("[ @]")[0].toLowerCase(), unknown);
    }

}
