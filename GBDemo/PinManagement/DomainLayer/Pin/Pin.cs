using System;
using PinManagement.Helper.Domain;

namespace PinManagement.DomainLayer.Pin
{
    public class Pin: Entity<String>
    {
        public String PinId { get; private set; }
        public String Name { get; set; }
        public String SiteId { get; set; }


        public Pin(String PinId): base(PinId)
        {
            this.PinId = PinId;
         
        }
    }
}
