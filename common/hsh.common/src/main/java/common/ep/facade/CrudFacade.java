package common.ep.facade;

import java.util.List;

/**
 * base crud facade interface
 *
 * @param <Dto> dto class
 */
public interface CrudFacade<Dto> {
    Dto getInstance(Object id);

    List<Dto> listAllInstances();

    Dto createInstance(Dto instance);

    Dto editInstance(Object id, Dto instance);

    void removeInstance(Object id);
}
