package basejava.basejava.src;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        this.size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
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
        for (int i = 0; i < this.size; i++) {
            if (get(uuid) == storage[i]) {
                index = i;
            }
        }
        System.arraycopy(storage, index + 1, storage, index, this.size--);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] fillArr = new Resume[this.size];
        System.arraycopy(storage, 0, fillArr, 0, fillArr.length);
        return fillArr;
    }

    int size() {
        return this.size;
    }
}
