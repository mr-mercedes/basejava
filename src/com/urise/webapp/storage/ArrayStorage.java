package basejava.basejava.src.com.urise.webapp.storage;

import basejava.basejava.src.com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0 , size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (storage[size] != null) {
            System.out.println("ERROR: Can't save resume");
        } else if (size == storage.length) {
            System.out.println("ERROR: Storage is full");
        } else {
            storage[size++] = r;
        }
    }

    public void update(Resume r) {
        if (isEmpty()){
            System.out.println("ERROR: Storage is empty");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(r)) {
                    storage[i].setUuid(r.toString());
                }
            }
        }
    }

    public Resume get(String uuid) {
        if (isEmpty()) {
            System.out.println("ERROR: Storage is empty");
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.printf("ERROR: %s not found\n", uuid);
        return null;
    }

    public void delete(String uuid) {
        if (isEmpty()){
            System.out.println("ERROR: Storage is empty");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (get(uuid) == storage[i]) {
                System.arraycopy(storage, i + 1, storage, i, size--);
                break;
            }
        }
    }

    private boolean isEmpty(){
        return size == 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
