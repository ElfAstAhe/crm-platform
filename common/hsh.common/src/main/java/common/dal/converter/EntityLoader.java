package common.dal.converter;

import common.bll.provider.FilteredDataProvider;
import common.dal.entity.IdEntity;

/**
 * Функциональный интерфейс загрузки entity
 *
 * @author elf
 * @param <Entity> тип entity
 */
public interface EntityLoader<Entity extends IdEntity> extends FilteredDataProvider<Object, Entity> {
}
