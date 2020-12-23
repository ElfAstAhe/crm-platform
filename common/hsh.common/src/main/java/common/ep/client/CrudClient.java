package common.ep.client;

import common.exceptions.base.RsException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * crud клиент
 * @author elf
 * @param <Dto> dto class
 */
public interface CrudClient<Dto> {

    // Синхронные методы
    Dto getInstance(Object id) throws RsException;

    List<Dto> listAllInstances() throws RsException;

    Dto createInstance(Dto instance) throws RsException;
    Dto editInstance(Dto instance) throws RsException;
    
    void removeInstance(Object id) throws RsException;
    
    // Асинхронные методы
    Future<Dto> getInstanceAsync(Object id);

    Future<List<Dto>> listAllAsync();
    Future<List<Dto>> listInstancesAsync(int fromRow, int rowCount);
    
    Future<Dto> createInstanceAsync(Dto instance);
    Future<Dto> editInstanceAsync(Dto instance);
    
    Future<?> removeInstanceAsync(Object id);
}
