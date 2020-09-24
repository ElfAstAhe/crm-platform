package common.bll.model;

/**
 * Бизнес модель
 *
 * @author elf
 * @param <Key> Бизнес ключ
 */
public interface BusinessModel<Key extends BusinessModelKey> {
    Key getKey();
}
