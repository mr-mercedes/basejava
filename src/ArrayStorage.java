package basejava.basejava.src;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Resume[] tmpStorage = new Resume[this.storage.length];
        System.arraycopy(tmpStorage, 0, storage, 0, tmpStorage.length);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null){
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        try {
            for (Resume resume : storage) {
                if (resume.toString().equals(uuid)) {
                    return resume;
                }
            }
        } catch (NullPointerException ex) {
            System.out.printf("Item %s not found \n", uuid);
        }
        return null;
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < storage.length; i++) {
            if (get(uuid) == storage[i]){
                index = i;
            }
        }
        Resume[] tmpStorage = new Resume[storage.length];



    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] fillArr = new Resume[size()];
        System.arraycopy(storage, 0, fillArr, 0, fillArr.length);
        return fillArr;
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        return count;
    }
}
