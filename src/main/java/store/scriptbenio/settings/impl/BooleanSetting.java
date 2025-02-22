package store.scriptbenio.settings.impl;

import lombok.Setter;
import store.scriptbenio.settings.Setting;

@Setter
public class BooleanSetting extends Setting {
    private boolean state;

    public BooleanSetting(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    private boolean isEnabled() {
        return state;
    }

    private void toggle() {
        setState(!isEnabled());
    }

}
