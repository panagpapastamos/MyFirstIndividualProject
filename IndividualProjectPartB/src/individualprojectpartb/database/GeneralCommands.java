package individualprojectpartb.database;

import java.util.List;

//an interface to make sure all the classes will implement the same methods
public interface GeneralCommands<T> {

    List<T> findAll();

    T findById(int id);

    void create(T t);

    void update(T t);

    void delete(int id);

}
