package event;

import entities.Nota;

public class NotaChangeEvent implements utils.event.Event, event.Event {
    private NotaEventType type;
    private Nota data, oldData;

    public NotaChangeEvent(NotaEventType type, Nota data) {
        this.type = type;
        this.data = data;
    }
    public NotaChangeEvent(NotaEventType type, Nota data, Nota oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public NotaEventType getType() {
        return type;
    }

    public Nota getData() {
        return data;
    }

    public Nota getOldData() {
        return oldData;
    }
}