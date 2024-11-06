using KidoroApp.Models;
using KidoroApp.Models.stock;
using KidoroApp.Models.viewModels;

namespace Kidoro.Services;

public class StockService(HttpClient httpClient)
{
    private const string BaseUrl = "http://localhost:8081/kidoro/";

    public async Task<StockViewModel> GetStock()
    {
        return new StockViewModel(
            await GetStockUsuel(),
            await GetStockBlocOptim(),
            await GetStockBlocMin()
            );
    }

    public async Task<List<StockUsuel>> GetStockUsuel()
    {
        const string requestUri = BaseUrl + "/stock/usuel";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<StockUsuel>>();
        return result ?? [];
    }

    public async Task<List<StockBloc>> GetStockBlocOptim()
    {
        const string requestUri = BaseUrl + "/stock/bloc?action=optim";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<StockBloc>>();
        return result ?? [];
    }

    public async Task<List<StockBloc>> GetStockBlocMin()
    {
        const string requestUri = BaseUrl + "/stock/bloc?action=min";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<StockBloc>>();
        return result ?? [];
    }

    public List<StockUsuel> GetMockStockUsuel()
    {
        return new List<StockUsuel>
            {
                new StockUsuel
                {
                    id_usuel = "F01",
                    qte_total = 100,
                    p_vente = 150m,
                    p_revient = 120
                },
                new StockUsuel
                {
                    id_usuel = "F02",
                    qte_total = 50,
                    p_vente = 200m,
                    p_revient = 180
                },
                new StockUsuel
                {
                    id_usuel = "F03",
                    qte_total = 75,
                    p_vente = 300m,
                    p_revient = 250
                }
            };
    }
}