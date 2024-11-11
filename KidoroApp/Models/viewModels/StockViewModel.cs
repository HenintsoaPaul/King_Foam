using KidoroApp.Models.stock;

namespace KidoroApp.Models.viewModels;

public class StockViewModel(List<StockUsuel> usuels, List<StockBloc> blocsOptim, List<StockBloc> blocsMin)
{
    public List<StockUsuel> Usuels { get; set; } = usuels;
    public List<StockBloc> BlocsOptim { get; set; } = blocsOptim;
    public List<StockBloc> BlocsMin { get; set; } = blocsMin;

    public List<MvtStockDetail>? MvtStockDetails { get; set; }
}
