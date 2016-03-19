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

    public void createXml(List<ITask> tasks) {
        // Корневой элемент
        Element tasksElement = new Element("tasks");
        Document doc = new Document(tasksElement);

        for (ITask task : tasks) {
            // Элемент отдельной задачи
            Element taskElement = new Element("task");

            Element name = new Element("name");
            Element description = new Element("description");
            Element contacts = new Element("contacts");
            Element date = new Element("date");

            name.setText((String) task.getValue(Parameter.TypeParameter.NAME));
            description.setText((String) task.getValue(Parameter.TypeParameter.DESCRIPTION));
            contacts.setText((String) task.getValue(Parameter.TypeParameter.CONTACTS));
            GregorianCalendar calendar = (GregorianCalendar) task.getValue(Parameter.TypeParameter.DATE);
            date.setText(calendar.toString());

            taskElement.addContent(name);
            taskElement.addContent(description);
            taskElement.addContent(contacts);
            taskElement.addContent(date);

            doc.getRootElement().addContent(taskElement);
        }

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        try {
            xmlOutputter.output(doc, new FileWriter("test.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
