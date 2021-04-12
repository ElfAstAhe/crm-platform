using Newtonsoft.Json;

namespace audit.dto
{
    public enum DataAuditEventEnum
    {
        [JsonProperty(PropertyName = "CREATED")]
        Created = 0,
        
        [JsonProperty(PropertyName = "MODIFIED")]
        Modified = 1,
        
        [JsonProperty(PropertyName = "REMOVED")]
        Removed = 2
    }
}