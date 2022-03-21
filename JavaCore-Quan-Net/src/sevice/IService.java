package sevice;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    void save(T t);
    void delete(int index);
    T edit(int index);


}
