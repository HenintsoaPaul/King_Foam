using Boutik.Models;

namespace KidoroApp.Models.stock;

public class StockUsuel : ClassMapTable
{
    public string id_usuel { get; set; }
    public int qte_total { get; set; }
    public decimal pu_vente { get; set; }
    public decimal avg_pu_revient { get; set; }
    public decimal p_vente { get; set; }
    public decimal p_revient { get; set; }
}