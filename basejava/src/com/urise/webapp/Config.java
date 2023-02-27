package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Config {
    private final SqlStorage storage;
    private static final File PROPS = new File(getHomeDir(), "config\\resumes.properties");

    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private Set<String> immutableUuids = new HashSet<>() {{
        add("11111111-1111-1111-1111-111111111111");
        add("22222222-2222-2222-2222-222222222222");
    }};

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public SqlStorage getStorage() {
        return storage;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getHomeDir() {
        String prop = System.getProperty("homeDir");
        File homeDir = new File(prop == null ? "basejava" : prop);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + " is not directory");
        }
        return homeDir;
    }

    public boolean isImmutable(String uuids) {
        return immutableUuids.contains(uuids);
    }

    public void checkImmutable(String uuids) {
        if (immutableUuids.contains(uuids))
            throw new RuntimeException("Зарезервированные резюме нельзя менять");
    }
}
