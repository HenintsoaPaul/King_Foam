using KidoroApp.Models;

namespace Kidoro.Services;

public class PerfoService(HttpClient httpClient)
{
    private const string BaseUrl = "http://localhost:8081/kidoro/";

    public async Task<List<Perfo>> GetPerfo()
    {
        return await GetMockPerfo();
        // const string requestUri = BaseUrl + "/stock/perfo";
        // var response = await httpClient.GetAsync(requestUri);
        // if (!response.IsSuccessStatusCode) return [];
        // var result = await response.Content.ReadFromJsonAsync<List<Perfo>>();
        // return result ?? [];
    }
    public Task<List<Perfo>> GetMockPerfo()
    {
        var mockData = new List<Perfo>
    {
        new() { machine = "Machine1", volume_total = 100, pr_theorique = 50, pr_pratique = 45, diff = 5 },
        new() { machine = "Machine2", volume_total = 200, pr_theorique = 100, pr_pratique = 90, diff = 10 },
        new() { machine = "Machine3", volume_total = 300, pr_theorique = 150, pr_pratique = 140, diff = 10 }
    };
        return Task.FromResult(mockData);
    }
}