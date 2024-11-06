using Kidoro.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers;

public class StockController(HttpClient httpClient) : Controller
{
    private readonly StockService _stockService = new(httpClient);

    public IActionResult Index()
    {
        return View(_stockService.GetStock());
    }
}
