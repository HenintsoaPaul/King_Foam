using KidoroApp.Models;
using KidoroApp.Models.stock;
using KidoroApp.Models.viewModels;

namespace Kidoro.Services;

public class StockService(HttpClient httpClient)
{
    private const string BaseUrl = "http://localhost:8081/kidoro/";

    public async Task<StockViewModel> GetStock()
    {
        List<StockUsuel> u = await GetStockUsuel();
        return new StockViewModel(u, GetStockBloc());
    }

    public async Task<List<StockUsuel>> GetStockUsuel()
    {
        const string requestUri = BaseUrl + "/stock/usuel";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<StockUsuel>>();
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

    public static List<StockBloc> GetStockBloc()
    {
        return new List<StockBloc>
            {
                new StockBloc
                {
                    idBloc = "B001",
                    prixRevient = 5000,
                    prixVenteOptimiste = 7500m,
                    prixVenteVolMin = 6000
                },
                new StockBloc
                {
                    idBloc = "B002",
                    prixRevient = 8000,
                    prixVenteOptimiste = 11000m,
                    prixVenteVolMin = 9000
                },
                new StockBloc
                {
                    idBloc = "B003",
                    prixRevient = 4000,
                    prixVenteOptimiste = 5500m,
                    prixVenteVolMin = 4500
                }
            };
    }
}