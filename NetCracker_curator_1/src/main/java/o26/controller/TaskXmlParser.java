package o26.controller;

import o26.model.ITask;
import o26.model.Parameter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TaskXmlParser {

    public List<ITask> parse(String filename, List<Integer> idList) {
        List<ITask> result = new ArrayList<>();

        try {
            File inputFile = new File(filename);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);

            Element tasksElement = document.getRootElement();

            List<Element> tasks = tasksElement.getChildren();

            for (int i = 0; i < tasks.size(); i++) {
                Element task = tasks.get(i);
                Integer tmpId = new Integer(task.getChild("id").getText());

                if (idList.contains(tmpId)) {
                    List<Parameter> parameters = new ArrayList<>();

                    Integer taskId = Integer.valueOf(task.getChild("id").getText());
                    parameters.add(new Parameter(Parameter.TypeParameter.ID, taskId));
                    parameters.add(new Parameter(Parameter.TypeParameter.NAME, task.getChild("name").getText()));
                    parameters.add(new Parameter(Parameter.TypeParameter.DESCRIPTION, task.getChild("description").getText()));
                    parameters.add(new Parameter(Parameter.TypeParameter.CONTACTS, task.getChild("contacts").getText()));

                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTimeInMillis(Long.parseLong(task.getChild("date").getText()));

                    parameters.add(new Parameter(Parameter.TypeParameter.DATE, calendar));

                    TaskCreator taskCreator = new TaskCreator();

                    result.add(taskCreator.createTask(parameters));
                }
            }


        } catch (JDOMException | IOException e) {
//            e.printStackTrace();
        }


        return result;
    }
}
