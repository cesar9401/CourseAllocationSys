package com.cesar31.system.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar31
 */
public class FileControl {

    public FileControl() {
    }

    public void writeDotFile(String name, String content) {
        File file = new File(name);
        try {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.write(content);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Leer archivo de texto
     *
     * @param path
     * @return
     */
    public String readData(String path) {
        String string = "";
        File file = new File(path);
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                while (line != null) {
                    string += line + "\n";
                    line = br.readLine();
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return string;
    }

    /**
     * Ejecutar comando
     *
     * @param command
     * @throws IOException
     */
    public void execComand(String command) throws IOException {
        List<String> cmdOutput = new ArrayList<>();
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String tmpLine;
        while ((tmpLine = br.readLine()) != null) {
            cmdOutput.add(tmpLine);
        }

        if (!cmdOutput.isEmpty()) {
            cmdOutput.forEach(s -> {
                System.out.println("Respuesta del sistema: " + s);
            });
        }
    }
}
