import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int position = 0;

    void clear() {
        position = 0;
    }

    void save(Resume r) {
        storage[position++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < position; i++) {
            if (uuid.equalsIgnoreCase(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < position; i++) {
            if (uuid.equalsIgnoreCase(storage[i].uuid)) {
                System.arraycopy(storage, i + 1, storage, i, position - i - 1);
                position--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, position);
    }

    int size() {
        return position;
    }
}
