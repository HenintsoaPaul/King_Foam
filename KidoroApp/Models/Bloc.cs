using Boutik.Models;

namespace KidoroApp.Models;

public class Bloc : ClassMapTable
{
    public string id { get; set; }
    public string id_bloc_mere { get; set; }
    public string daty_entree { get; set; }
    public string daty_sortie { get; set; }
    public double prix_revient { get; set; }
    public double longueur { get; set; }
    public double largeur { get; set; }
    public double hauteur { get; set; }
}
