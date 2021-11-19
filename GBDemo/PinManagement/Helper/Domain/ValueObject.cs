using System;
using System.Diagnostics.CodeAnalysis;

namespace PinManagement.Helper.Domain
{
    public abstract class ValueObject: IEquatable<ValueObject>
    {
        public ValueObject()
        {
        }

        public abstract bool Equals([AllowNull] ValueObject other);
    }
}
