using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace audit.dto
{
    /// <summary>
    /// data audit dto
    /// </summary>
    public class DataAudit
    {
        [JsonProperty(PropertyName = "id")]
        public long? Id { get; set; }
        
        [JsonProperty(PropertyName = "date")]
        public DateTimeOffset? Date { get; set; }
        
        [JsonProperty(PropertyName = "source")]
        public string Source { get; set; }
        
        [JsonProperty(PropertyName = "requestId")]
        public string RequestId { get; set; }
        
        [JsonProperty(PropertyName = "event")]
        public DataAuditEventEnum? Event { get; set; }
        
        [JsonProperty(PropertyName = "className")]
        public string ClassName { get; set; }
        
        [JsonProperty(PropertyName = "classDescription")]
        public string ClassDescription { get; set; }
        
        [JsonProperty(PropertyName = "objectId")]
        public string ObjectId { get; set; }
        
        [JsonProperty(PropertyName = "objectName")]
        public string ObjectName { get; set; }
        
        [JsonProperty(PropertyName = "values")]
        public IList<DataAuditValue> Values { get; set; }
        
        [JsonProperty(PropertyName = "user")]
        public string User { get; set; }
        
        [JsonProperty(PropertyName = "runAsUser")]
        public string RunAsUser { get; set; }
        
        [JsonProperty(PropertyName = "status")]
        public AuditStatusEnum? Status { get; set; }
    }
}