using KidoroApp.Models.stock;

namespace KidoroApp.Models.viewModels;

public class StockViewModel(List<StockUsuel> usuels, List<StockBloc> blocsOptim, List<StockBloc> blocsMin)
{
    public List<StockUsuel> Usuels { get; set; } = usuels;
    public List<StockBloc> BlocsOptim { get; set; } = blocsOptim;
    public List<StockBloc> BlocsMin { get; set; } = blocsMin;

    // Methods
    // Usuels
    public int GetTotalQuantiteUsuels()
    {
        return Usuels.Sum(u => u.qte_total);
    }

    public decimal GetTotalPrixVenteUsuels()
    {
        return Usuels.Sum(u => u.p_vente);
    }

    public decimal GetTotalPrixRevientUsuels()
    {
        return Usuels.Sum(u => u.p_revient);
    }

    // Blocs
    //public decimal GetTotalPrixRevientBloc()
    //{
    //    return Blocs.Sum(b => b.prixRevient);
    //}
    //public decimal GetTotalPrixVenteOptimisteBloc()
    //{
    //    return Blocs.Sum(b => b.prixVenteOptimiste);
    //}
    //public decimal GetTotalPrixVenteVolMinBloc()
    //{
    //    return Blocs.Sum(b => b.prixVenteVolMin);
    //}
}
