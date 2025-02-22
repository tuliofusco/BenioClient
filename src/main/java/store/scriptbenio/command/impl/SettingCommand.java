package store.scriptbenio.command.impl;

import store.scriptbenio.Benio;
import store.scriptbenio.command.Command;
import store.scriptbenio.command.CommandInfo;
import store.scriptbenio.exception.CommandException;
import store.scriptbenio.module.Module;
import store.scriptbenio.settings.Setting;
import store.scriptbenio.settings.impl.BooleanSetting;
import store.scriptbenio.settings.impl.DoubleSetting;
import store.scriptbenio.settings.impl.ModeSetting;
import store.scriptbenio.util.ChatUtil;

@CommandInfo(
        name = "setting",
        usage = "#setting <Module> <Setting> <Value>",
        description = "Set your preferences and settings",
        aliases = {"s", "set", "settings"}

)
public class SettingCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        Module mod = Benio.INSTANCE.getMm().getModule(args[0]);

        if (args.length != 3 && args.length != 6) {
            ChatUtil.addChatMessage("&9 Usage: &c\"&b" + getUsage() + "&c\"\n");
            return;
        }

        if (mod == null) {
            ChatUtil.addChatMessage("&cNo Module was found!");
            return;
        }

        Setting setting = Benio.INSTANCE.getSm().getSetting(mod, args[1]);
        if (setting == null) {
            ChatUtil.addChatMessage("No Setting was found!");
            return;
        }

        try {
            if (setting instanceof BooleanSetting) {
                BooleanSetting bs = (BooleanSetting) setting;

                if (!(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false"))) {
                    ChatUtil.addChatMessage("&The values must be &atrue&r &4or &cfalse");
                    return;
                }

                bs.setState(args[2].equalsIgnoreCase("true"));
            }

            if (setting instanceof DoubleSetting) {
                DoubleSetting ds = (DoubleSetting) setting;

                 double myDouble = Double.parseDouble(args[2]);
                 ds.setVal(myDouble);
                 ChatUtil.addChatMessage("Set " + mod.getName() + "'s " + setting.getName() + " to " + args[2]);
                 return;
            }

            if (setting instanceof ModeSetting) {
                ModeSetting ms = (ModeSetting) setting;

                if (ms.getModes().stream().noneMatch(s -> s.equalsIgnoreCase(args[2]))) {
                    ChatUtil.addChatMessage("Specified module was not found!");
                    return;
                }

                ms.setCurrentMode(args[2]);
            }

        } catch (NumberFormatException e) {
            ChatUtil.addChatMessage("You must pass a number as an argument!/Invalid number!");
            return;
        }

    }
}
