package common.ep.client;

import common.exceptions.RsException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * crud клиент
 * @author elf
 * @param <Dto> dto class
 * @param <Key> key class
 */
public interface CrudClient<Dto,Key> {

    // Синхронные методы
    Dto getInstance(Long id) throws RsException;
    Dto getInstanceByKey(Key key) throws RsException;
    
    List<Dto> listAllInstances() throws RsException;
    List<Dto> listInstances(int fromRow, int rowCount) throws RsException;
    
    Dto createInstance(Dto instance) throws RsException;
    Dto editInstance(Dto instance) throws RsException;
    
    void removeInstance(Long id) throws RsException;
    
    // Асинхронные методы
    Future<Dto> getInstanceAsync(Long id);
    Future<Dto> getInstanceByKeyAsync(Key key);
    
    Future<List<Dto>> listAllAsync();
    Future<List<Dto>> listInstancesAsync(int fromRow, int rowCount);
    
    Future<Dto> createInstanceAsync(Dto instance);
    Future<Dto> editInstanceAsync(Dto instance);
    
    Future<?> removeInstanceAsync(Long id);
}
