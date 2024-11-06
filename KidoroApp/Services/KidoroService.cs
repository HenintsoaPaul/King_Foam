using KidoroApp.Models;

namespace Kidoro.Services;

public class KidoroService(HttpClient httpClient)
{
    private const string BaseUrl = "http://localhost:8081/kidoro/";

    public async Task<List<Usuel>> GetAllUsuel()
    {
        const string requestUri = BaseUrl + "/usuels";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<Usuel>>();
        return result ?? [];
    }

    public async Task<List<Bloc>> GetAllBloc()
    {
        const string requestUri = BaseUrl + "/blocs";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<Bloc>>();
        return result ?? [];
    }

    public async Task<List<Bloc>> GetAllBlocInStock()
    {
        const string requestUri = BaseUrl + "/blocs?action=stock";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<Bloc>>();
        return result ?? [];
    }
}