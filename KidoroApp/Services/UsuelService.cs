using KidoroApp.Models;

namespace Kidoro.Services;

public class UsuelService(HttpClient httpClient)
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
}