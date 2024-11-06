using Boutik.Models;

namespace KidoroApp.Models;

public class Bloc : Cube
{
    public string id { get; set; }
    public string id_bloc_mere { get; set; }
    public string daty_entree { get; set; }
    public string daty_sortie { get; set; }
    public double prix_revient { get; set; }
}
