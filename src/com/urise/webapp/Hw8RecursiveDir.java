package com.urise.webapp;

import java.io.File;

public class Hw8RecursiveDir {
    public static void main(String[] args) {
        File path = new File("D:\\JavaDev\\TopJava");
        recursiveShowAllFile(path);
    }

    private static void recursiveShowAllFile(File path) {
        if (!path.isDirectory()) {
            String[] list = path.list();
            if (list == null) {
                System.out.println(path.getName());
            } else {
                for (String name : list) {
                    System.out.println(name);
                }
            }
        } else {
            File[] files = path.listFiles();
            if (files != null) {
                for (File name : files) {
                    if (!name.getName().equalsIgnoreCase(".git")) {
                        String absolutePath = name.getAbsolutePath();
                        recursiveShowAllFile(new File(absolutePath));
                    }
                }
            }
        }
    }
}
