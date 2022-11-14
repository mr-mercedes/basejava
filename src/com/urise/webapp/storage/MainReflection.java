package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "old_uuid");
        // TODO : invoke r.toString via reflection
        Class<? extends Resume> refClass = r.getClass();
        Method toStringMethod = refClass.getMethod("toString", null);
        System.out.println(toStringMethod.invoke(r, null));
        field.set(r, "new_uuid");
        System.out.println(toStringMethod.invoke(r, null));
    }
}
