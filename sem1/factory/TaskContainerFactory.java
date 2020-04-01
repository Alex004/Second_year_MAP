package sem1.factory;

//import ubb.scs.map.ir.sem1.containers.Container;
//import ubb.scs.map.ir.sem1.containers.QueueContainer;
//import ubb.scs.map.ir.sem1.containers.StackContainer;
//import ubb.scs.map.ir.sem1.containers.Strategy;


import sem1.containers.Container;
import sem1.containers.QueueContainer;
import sem1.containers.StackContainer;
import sem1.containers.Strategy;

public class TaskContainerFactory implements Factory {
    private TaskContainerFactory() {
    }
    private static TaskContainerFactory instance = null;
    public static TaskContainerFactory getInstance(){
        if(instance == null){
            instance = new TaskContainerFactory();
        }
        return instance;
    }
    public Container createContainer(Strategy strategy){
        switch (strategy){
            case LIFO:
                return new StackContainer();
            case FIFO:
                return new QueueContainer();
            default:
                return null;
        }
    }
}
