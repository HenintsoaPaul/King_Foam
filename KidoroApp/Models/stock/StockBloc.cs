namespace KidoroApp.Models;

public class StockBloc
{
    public string idBloc { get; set; }
    public int prixRevient { get; set; }
    public decimal prixVenteOptimiste { get; set; }
    public int prixVenteVolMin { get; set; }
}