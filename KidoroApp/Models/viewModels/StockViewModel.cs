using KidoroApp.Models.stock;

namespace KidoroApp.Models.viewModels;

public class StockViewModel(List<StockUsuel> usuels, List<StockBloc> blocs)
{
    public List<StockUsuel> Usuels { get; set; } = usuels;
    public List<StockBloc> Blocs { get; set; } = blocs;

    // Methods
    // Usuels
    public int GetTotalQuantiteUsuels()
    {
        return Usuels.Sum(u => u.quantite);
    }

    public decimal GetTotalPrixVenteUsuels()
    {
        return Usuels.Sum(u => u.sumPrixVente);
    }

    public decimal GetTotalPrixRevientUsuels()
    {
        return Usuels.Sum(u => u.sumPrixRevient);
    }

    // Blocs
    public decimal GetTotalPrixRevientBloc()
    {
        return Blocs.Sum(b => b.prixRevient);
    }
    public decimal GetTotalPrixVenteOptimisteBloc()
    {
        return Blocs.Sum(b => b.prixVenteOptimiste);
    }
    public decimal GetTotalPrixVenteVolMinBloc()
    {
        return Blocs.Sum(b => b.prixVenteVolMin);
    }
}
