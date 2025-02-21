package store.scriptbenio.command.impl;

import net.minecraft.util.EnumChatFormatting;
import store.scriptbenio.Benio;
import store.scriptbenio.command.Command;
import store.scriptbenio.command.CommandInfo;
import store.scriptbenio.exception.CommandException;
import store.scriptbenio.util.ChatUtil;

@CommandInfo(
        name = "help",
        usage = "#help <command>",
        description = "Use this to get to known all our Features!",
        aliases = {"hlp", "hlep"}

)
public final class HelpCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if (args.length > 0) {
            ChatUtil.addChatMessage("\n");
            final Command command = Benio.INSTANCE.getCm().getCommand(args[0])
                    .orElseThrow(() ->
                            new CommandException(String.format(EnumChatFormatting.RED + "ERROR: Command \"%s\" not found! \n",args[0])));
            return;
        }
        ChatUtil.addChatMessage("\n");
        Benio.INSTANCE.getCm()
                .getCommands()
                .values()
                .stream()
                .filter(command -> !(command instanceof HelpCommand))
                .forEach(command -> ChatUtil.addChatMessage(
                        String.format(EnumChatFormatting.BLACK
                                + "%s "
                                + EnumChatFormatting.WHITE
                                + "- "
                                + EnumChatFormatting.GRAY
                                + "%s", " " + command.getName(), command.getDescription()
                                + "\n")
                        , true));

        ChatUtil.addChatMessage("\n");
    }
}
