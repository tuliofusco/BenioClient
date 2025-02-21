package store.scriptbenio.command;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.Validate;
import store.scriptbenio.Benio;
import store.scriptbenio.exception.CommandException;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Command {

    protected static final Minecraft mc = Benio.INSTANCE.getMc();

    private final String name;
    private final String description;
    private final String usage;
    private final List<String> aliases;

    public Command() {
        final CommandInfo info = this.getClass().getAnnotation(CommandInfo.class);
        Validate.notNull(info, "COMMAND EXCEPTION");
        this.name = info.name();
        this.description = info.description();
        this.usage = info.usage();
        this.aliases = Arrays.asList(info.aliases());
    }

    public boolean isAlias(final String str) {
        return aliases.stream()
                .anyMatch(s -> s.equalsIgnoreCase(str));
    }

    public abstract void execute(String... args) throws CommandException;
}
