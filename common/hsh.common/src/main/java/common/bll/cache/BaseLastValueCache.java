package common.bll.cache;

import common.bll.model.BusinessModel;
import common.bll.model.BusinessModelKey;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Базовая реализация LastValueCache
 * ВНИМАНИЕ!!! Наследники должны быть EJB, concurrencyType.BEAN
 *
 * @param <Key>   ключ
 * @param <Model> модель
 *
 * @author elf
 */
public abstract class BaseLastValueCache<Key extends BusinessModelKey, Model extends BusinessModel<Key>> extends BaseSimpleCache<Key, Model> {
    @Override
    public synchronized void put(Key key, Model model) {
        if (key == null || model == null)
            return;

        // Удаляем
        getCacheValues().entrySet()
                        .removeIf(e -> isRemoveCondition(e.getValue(), model));
        // Добавляем
        getCacheValues().put(key, model);
    }

    protected abstract boolean isRemoveCondition(Model existedModel, Model newModel);
}
