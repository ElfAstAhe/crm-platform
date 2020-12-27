package common.ep.client;

import common.exceptions.base.ClientException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * crud клиент
 * @author elf
 * @param <Dto> dto class
 */
public interface CrudClient<Dto> {

    // Синхронные методы
    Dto getInstance(Object id);

    List<Dto> listAllInstances();

    Dto createInstance(Dto instance);
    Dto editInstance(Dto instance);
    
    void removeInstance(Object id);
    
    // Асинхронные методы
    Future<Dto> getInstanceAsync(Object id);

    Future<List<Dto>> listAllAsync();

    Future<Dto> createInstanceAsync(Dto instance);
    Future<Dto> editInstanceAsync(Dto instance);
    
    Future<?> removeInstanceAsync(Object id);
}
