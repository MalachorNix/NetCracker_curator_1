package o26.controller;

import o26.model.ITask;
import o26.model.Parameter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

public class TaskXmlCreator {

    public void createTaskXml(List<ITask> tasks, String fileName) {
        // Корневой элемент
        Element tasksElement = new Element("tasks");
        Document doc = new Document(tasksElement);

        for (ITask task : tasks) {
            // Элемент отдельной задачи
            Element taskElement = new Element("task");

            Element id = new Element("id");
            Element name = new Element("name");
            Element description = new Element("description");
            Element contacts = new Element("contacts");
            Element date = new Element("date");

            id.setText(Integer.toString((int) task.getValue(Parameter.TypeParameter.ID)));
            name.setText((String) task.getValue(Parameter.TypeParameter.NAME));
            description.setText((String) task.getValue(Parameter.TypeParameter.DESCRIPTION));
            contacts.setText((String) task.getValue(Parameter.TypeParameter.CONTACTS));
            GregorianCalendar calendar = (GregorianCalendar) task.getValue(Parameter.TypeParameter.DATE);
            date.setText(Long.toString(calendar.getTimeInMillis()));

            taskElement.addContent(id);
            taskElement.addContent(name);
            taskElement.addContent(description);
            taskElement.addContent(contacts);
            taskElement.addContent(date);

            doc.getRootElement().addContent(taskElement);
        }

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        try {
            xmlOutputter.output(doc, new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
