using System;
using System.Diagnostics.CodeAnalysis;

namespace PinManagement.Helper.Domain
{
    public abstract class Entity<IDType> : IEquatable<Entity<IDType>>
    {
        private readonly IDType id;

        protected Entity(IDType id)
        {

            this.id = id;
        }

        public IDType Id
        {
            get { return this.id; }
        }

        public override bool Equals(object obj)
        {
            var entity = obj as Entity<IDType>;
            if (entity != null)
            {
                return this.Equals(entity);
            }
            return base.Equals(obj);
        }

        public override int GetHashCode()
        {
            return this.Id.GetHashCode();
        }

        public bool Equals(Entity<IDType> other)
        {
            if (other == null)
            {
                return false;
            }
            return this.Id.Equals(other.Id);
        }
    }
}
