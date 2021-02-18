namespace hsh.common.dal.entity
{
    /// <summary>
    /// Базовый интерфейс entity с id
    /// </summary>
    public interface IIdEntity
    {
        long? Id { get; set; } 
    }
}