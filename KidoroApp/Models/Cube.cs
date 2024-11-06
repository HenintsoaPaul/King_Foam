using Boutik.Models;

namespace KidoroApp.Models;

public class Cube : ClassMapTable
{
    public double longueur { get; set; }
    public double largeur { get; set; }
    public double hauteur { get; set; }

    public double GetVolume()
    {
        return hauteur * largeur * longueur;
    }
}
