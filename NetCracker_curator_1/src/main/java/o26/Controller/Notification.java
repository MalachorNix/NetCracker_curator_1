package o26.Controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import o26.Model.Task;
import o26.Model.TaskParameters;

public class Notification {

    public int getActualTask(ArrayList tasks) {
        int index = 0;
        Map map = ((Task) tasks.get(0)).getParameters();
        long time = ((GregorianCalendar) map.get(TaskParameters.DATE)).getTimeInMillis();

        for (int i = 1; i < tasks.size(); i++) {
            map = ((Task) tasks.get(i)).getParameters();
            if (time > ((GregorianCalendar) map.get(TaskParameters.DATE)).getTimeInMillis()) {
                time = ((GregorianCalendar) map.get(TaskParameters.DATE)).getTimeInMillis();
                index = i;
            }
        }

        return index;
    }
}
