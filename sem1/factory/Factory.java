package sem1.factory;

import sem1.containers.Container;
import sem1.containers.Strategy;

public interface Factory {
    public Container createContainer(Strategy strategy);
}
