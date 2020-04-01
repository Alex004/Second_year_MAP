package event;


import entities.Student;

public class StudentChangeEvent implements Event {
    private StudentEventType type;
    private Student data, oldData;

    public StudentChangeEvent(StudentEventType type, Student data) {
        this.type = type;
        this.data = data;
    }
    public StudentChangeEvent(StudentEventType type, Student data, Student oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public StudentEventType getType() {
        return type;
    }

    public Student getData() {
        return data;
    }

    public Student getOldData() {
        return oldData;
    }
}
