using System;
using System.Text;
using hsh.common.dal.entity;

namespace hsh_common.dal.entity
{
    public class BaseIdEntity : IIdEntity
    {
        public long? Id { get; set; }

        public override int GetHashCode()
        {
            return HashCode.Combine(Id);
        }

        public override string ToString()
        {
            return new StringBuilder().Append($"{nameof(GetType)}[")
                .Append($"id=[{Id}]")
                .Append("]")
                .ToString();
        }
    }
}