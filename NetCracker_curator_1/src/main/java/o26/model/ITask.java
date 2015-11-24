package o26.model;

import java.util.Map;

public interface ITask {

    Object getValue(TaskParameter parameter);

    void setValue(TaskParameter parameter, Object value);

    Map<TaskParameter, Object> getParameters();
}
