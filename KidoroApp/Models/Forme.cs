namespace KidoroApp.Models
{
    public class Forme
    {
        public double L { get; set; }
        public double l { get; set; }
        public double h { get; set; }

        public Forme(double L, double l, double h)
        {
            this.L = L;
            this.l = l;
            this.h = h;
        }
    }
}
