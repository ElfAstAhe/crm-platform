package common.helpers.builder.base;

/**
 * Базовый класс билдера
 *
 * @param <Clazz>
 *
 * @author elf
 */
public class BaseBuilder<Clazz> {
    private Clazz instance;

    protected BaseBuilder(Class<Clazz> clazz) {
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch(Exception ex) {
            instance = null;
        }
    }

    public Clazz build() {
        return instance;
    }

    protected Clazz getInstance() {
        return instance;
    }
}
