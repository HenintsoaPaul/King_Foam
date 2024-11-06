using KidoroApp.Models.stock;

namespace KidoroApp.Models.viewModels;

public class StockViewModel(List<StockUsuel> usuels, List<StockBloc> blocs)
{
    public List<StockUsuel> Usuels { get; set; } = usuels;
    public List<StockBloc> Blocs { get; set; } = blocs;
}
