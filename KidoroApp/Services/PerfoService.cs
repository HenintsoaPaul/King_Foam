using KidoroApp.Models;

namespace Kidoro.Services;

public class PerfoService(HttpClient httpClient)
{
    private const string BaseUrl = "http://localhost:8081/kidoro/";

    public async Task<List<Perfo>> GetPerfo()
    {
        //return await GetMockPerfo();
        const string requestUri = BaseUrl + "/perfo";
        var response = await httpClient.GetAsync(requestUri);
        if (!response.IsSuccessStatusCode) return [];
        var result = await response.Content.ReadFromJsonAsync<List<Perfo>>();
        return result ?? [];
    }
    public Task<List<Perfo>> GetMockPerfo()
    {
        var mockData = new List<Perfo>
    {
        new() { id_machine = "Machine1", vol_total = 100, sum_pr_theorique = 50, sum_pr_pratique = 45, diff_th_reel = 5 },
        new() { id_machine = "Machine2", vol_total = 200, sum_pr_theorique = 100, sum_pr_pratique = 90, diff_th_reel = 10 },
        new() { id_machine = "Machine3", vol_total = 300, sum_pr_theorique = 150, sum_pr_pratique = 140, diff_th_reel = 10 }
    };
        return Task.FromResult(mockData);
    }
}