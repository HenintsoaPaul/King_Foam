using Kidoro.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers;

public class StockController(HttpClient httpClient) : Controller
{
    private readonly StockService _stockService = new(httpClient);

    public IActionResult Index()
    {
        var data = _stockService.GetStockUsuel();
        return View(data);
    }
}
