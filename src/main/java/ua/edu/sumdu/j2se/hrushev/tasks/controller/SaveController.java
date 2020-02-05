package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.TaskIO;

import java.io.*;
import java.util.logging.Logger;

public class SaveController extends Controller {
    private final Logger logger = Logger.getLogger(String.valueOf(Main.class));

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
            logger.info("Got IOException exception while trying to save data to a save file.");
        }

        return 0;
    }

    public void load(AbstractTaskList list) {
        File file = new File("save.json");

        try {
            if (file.createNewFile()) {
                return;
            } else {
                if(file.length() != 0) {
                    TaskIO.read(list, new FileReader(file));
                }
            }
        } catch (IOException e) {
            logger.info("Got IOException trying to load data from a save file.");
        }
    }
}
