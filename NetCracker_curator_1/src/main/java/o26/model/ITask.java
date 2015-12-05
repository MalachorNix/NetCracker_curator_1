package o26.model;

import java.util.List;

public interface ITask {

    Object getValue(Parameter.TypeParameter type);

    void setValue(Parameter.TypeParameter type, Object value);

    List<Parameter> getParameters();
}
