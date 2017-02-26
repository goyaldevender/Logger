package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

/**
 * Created by Devender on 06/02/17.
 */
public class LogToFile extends Thread {

    Queue<String> q;

    LogToFile(Queue<String> q) {
        this.q = q;
        run();
    }

    public void run() {

        File file = new File("logFile.txt");

        // creates the file
        try {
            file.createNewFile();

            // creates a FileWriter Object
            FileWriter writer = null;

            writer = new FileWriter(file);

            // Writes the content to the file
            while (!q.isEmpty())
                writer.write(q.remove() + "\n");

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
