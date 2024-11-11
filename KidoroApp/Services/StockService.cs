using KidoroApp.Models;
using KidoroApp.Models.stock;
using KidoroApp.Models.viewModels;

namespace Kidoro.Services;

public class StockService(HttpClient httpClient)
{
    private const string BaseUrl = "http://localhost:8081/kidoro/";

    public async Task<StockViewModel> GetStock()
    {
        var data = new StockViewModel(
            await GetStockUsuel(),
            await GetStockBlocOptim(),
            await GetStockBlocMin()
            );

        data.MvtStockDetails = await GetMvtStockDetail();

        return data;
    }

    public async Task<List<MvtStockDetail>> GetMvtStockDetail()
    {
        const string requestUri = BaseUrl + "/stock/mvtStock";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<MvtStockDetail>>();
        return result ?? [];
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
}