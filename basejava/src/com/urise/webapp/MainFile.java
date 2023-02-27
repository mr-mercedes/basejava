package com.urise.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        File file = new File(".\\.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("D:\\JavaDev\\TopJava\\basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        String tab = "";
        printDirectoryDeeply(dir, tab);
    }

    public static void printDirectoryDeeply(File dir, String tab) {
        File[] files = dir.listFiles();

        if (files != null)
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(tab + "File: " + file.getName());
                } else if (file.isDirectory() && !file.getName().equalsIgnoreCase(".git")) {
                    System.out.println(tab + "Directory: " + file.getName());
                    printDirectoryDeeply(file, tab + "\t");
                }
            }
    }
}
