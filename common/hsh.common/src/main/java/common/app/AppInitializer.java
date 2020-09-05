package common.app;

/**
 * интерфейс класса инициализатора приложения
 *
 * @author elf
 */
public interface AppInitializer {

    /**
     * возвращает признак готовности приложения
     *
     * @return признак
     */
    boolean isReady();
}
