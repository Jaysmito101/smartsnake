package commons;

import java.lang.reflect.Type;

public class KeyValuePair<T> {
    public String key;
    public T value;

    public KeyValuePair(String key, T value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " " + value.getClass();
    }
}
