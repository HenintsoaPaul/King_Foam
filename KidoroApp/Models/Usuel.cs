using Boutik.Models;

namespace KidoroApp.Models;

public class Usuel : ClassMapTable
{
    public string id { get; set; }
    public string val { get; set; }
    public double prix_revient { get; set; }
    public double prix_vente { get; set; }
    public double longueur { get; set; }
    public double largeur { get; set; }
    public double hauteur { get; set; }
}
