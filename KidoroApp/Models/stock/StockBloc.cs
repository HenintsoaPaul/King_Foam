namespace KidoroApp.Models;

public class StockBloc
{
    public string id_bloc { get; set; }
    public string id_usuel { get; set; }
    public decimal pv_usuel { get; set; }
    public decimal vol_bloc { get; set; }
    public decimal vol_usuel { get; set; }
    public int qte_produit { get; set; }
    public decimal pr_bloc { get; set; }
    public decimal pv_bloc { get; set; }
}