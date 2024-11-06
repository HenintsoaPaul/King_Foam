using Kidoro.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers;

public class StockController(HttpClient httpClient) : Controller
{
    private readonly StockService _stockService = new(httpClient);

    public async Task<IActionResult> Index()
    {
        return View(await _stockService.GetStock());
    }
}
