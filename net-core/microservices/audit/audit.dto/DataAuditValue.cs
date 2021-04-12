using Newtonsoft.Json;

namespace audit.dto
{
    public class DataAuditValue
    {
        [JsonProperty(PropertyName = "name")]
        public string Name { get; set; }
        
        [JsonProperty(PropertyName = "description")]
        public string Description { get; set; }
        
        [JsonProperty(PropertyName = "before")]
        public string Before { get; set; }
        
        [JsonProperty(PropertyName = "after")]
        public string After { get; set; }
    }
}