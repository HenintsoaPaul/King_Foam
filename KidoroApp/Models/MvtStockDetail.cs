using Boutik.Models;

namespace KidoroApp.Models;

public class MvtStockDetail : ClassMapTable
{
    public string id { get; set; }
    public string id_mvt_stock { get; set; }
    public string id_usuel { get; set; }
    public string val_usuel { get; set; }
    public double prix_revient { get; set; }
    public double prix_vente { get; set; }
    public int entree { get; set; }
    public int sortie { get; set; }
}