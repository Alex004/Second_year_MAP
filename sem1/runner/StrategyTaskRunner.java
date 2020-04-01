package sem1.runner;

import sem1.containers.Container;
import sem1.containers.Strategy;
import sem1.factory.TaskContainerFactory;
import sem1.model.Task;

public class StrategyTaskRunner implements TaskRunner {
    private Container container;
    public StrategyTaskRunner(Strategy strategy) {
        this.container = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if(!container.isEmpty()) {
            Task t = container.remove();
            t.execute();
        }
    }

    @Override
    public void executeAll() {
        while (!container.isEmpty()){
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
