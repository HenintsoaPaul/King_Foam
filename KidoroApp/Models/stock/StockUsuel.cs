using Boutik.Models;

namespace KidoroApp.Models.stock;

public class StockUsuel : ClassMapTable
{
    public string id_usuel { get; set; }
    public int qte_total { get; set; }
    public decimal prix_vente_total { get; set; }
    public decimal prix_revient_total { get; set; }
    public decimal prix_revient_avg { get; set; }
}