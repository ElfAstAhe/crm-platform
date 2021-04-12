using Newtonsoft.Json;

namespace audit.dto
{
    public enum AuditStatusEnum
    {
        [JsonProperty(PropertyName = "FAIL")]
        Fail = 0,
        
        [JsonProperty(PropertyName = "SUCCESS")]
        Success = 1
    }
}