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

        if (file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                TaskIO.write(list, writer);
                logger.info("Saved data to a save file.");
            } catch (FileNotFoundException e) {
                logger.info("Got FileNotFoundException trying to save data to a save file.");
            } catch (IOException e) {
                logger.info("Got IOException trying to save data to a save file.");
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                file.createNewFile();
                TaskIO.write(list, writer);
                logger.info("Created new save file and stored data in it.");
            } catch (IOException e) {
                logger.info("Got IOException trying to save data to a save file.");
            }
        }

        return 0;
    }

    public void load(AbstractTaskList list) {
        File file = new File("save.json");

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                if (file.length() > 0){
                    TaskIO.read(list, reader);
                    logger.info("Read data from a save file.");
                } else {
                    logger.info("Nothing to read from save file.");
                }
            } catch (FileNotFoundException e) {
                logger.info("Got FileNotFoundException trying to load data from a save file.");
            } catch (IOException e) {
                logger.info("Got IOException trying to load data from a save file.");
            }
        } else {
            try {
                file.createNewFile();
                logger.info("Created a new save file.");
            } catch (IOException e) {
                logger.info("Got IOException trying to create a save file.");
            }
        }
    }
}
