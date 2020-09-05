package common.dto.converter;

/**
 *
 * @author elf
 * @param <Dto>
 * @param <Model>
 */
public interface Converter<Dto, Model> {
    
    /**
     * Преобразовать модель в dto
     * @param model model
     * @return dto
     */
    Dto toDto(Model model);
    
    /**
     * Преобразовать dto в модель
     * @param dto dto
     * @return model
     */
    Model toModel(Dto dto);
}
