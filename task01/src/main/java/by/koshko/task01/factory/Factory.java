package by.koshko.task01.factory;

import java.util.List;

public interface Factory<T> {

    /**
     * asd.
     * @param params params.
     * @return T.
     */
    T createInstance(List<String> params);

}
