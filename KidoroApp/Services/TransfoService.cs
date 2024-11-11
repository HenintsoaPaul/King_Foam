using KidoroApp.Models;

namespace Kidoro.Services;

public class TransfoService(HttpClient httpClient)
{
    private const string BaseUrl = "http://localhost:8081/kidoro/";

    public async Task<List<Bloc>> GetAllBloc()
    {
        const string requestUri = BaseUrl + "/transfo";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<Bloc>>();
        return result ?? [];
    }
}