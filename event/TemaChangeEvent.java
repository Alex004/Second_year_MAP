package event;

import entities.Tema;
import utils.event.Event;


public class TemaChangeEvent implements Event, event.Event {
    private TemaEventType type;
    private Tema data, oldData;

    public TemaChangeEvent(TemaEventType type, Tema data) {
        this.type = type;
        this.data = data;
    }
    public TemaChangeEvent(TemaEventType type, Tema data, Tema oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public TemaEventType getType() {
        return type;
    }

    public Tema getData() {
        return data;
    }

    public Tema getOldData() {
        return oldData;
    }
}
