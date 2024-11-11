using Boutik.Models;

namespace KidoroApp.Models;

public class Usuel : Cube
{
    public string id { get; set; }
    public string val { get; set; }
    public double prix_revient { get; set; }
    public double prix_vente { get; set; }
}
