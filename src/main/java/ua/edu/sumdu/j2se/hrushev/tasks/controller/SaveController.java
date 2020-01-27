package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.TaskIO;

import java.io.*;

public class SaveController extends Controller {
    @Override
    public int process(AbstractTaskList list) {
        File file = new File("save.json");

        try {
            boolean check = file.createNewFile();
            if (check) {
                TaskIO.write(list, new FileWriter(file));
            } else {
                new PrintWriter(file).close();
                TaskIO.write(list, new FileWriter(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void load(AbstractTaskList list) {
        File file = new File("save.json");

        try {
            if (file.createNewFile()) {
                return;
            } else {
                TaskIO.read(list, new FileReader(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
