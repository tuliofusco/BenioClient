package store.scriptbenio.event.impl.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import store.scriptbenio.event.Event;

@Getter
@AllArgsConstructor
public final class EventKey extends Event {
    private final int key;
}
