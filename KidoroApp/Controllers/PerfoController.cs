using Kidoro.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers;

public class PerfoController(HttpClient httpClient) : Controller
{
    private readonly PerfoService _perfoService = new(httpClient);

    public async Task<IActionResult> Index()
    {
        return View(await _perfoService.GetPerfo());
    }

    public async Task<IActionResult> Filter(string annee)
    {
        return View("Index", await _perfoService.GetPerfoByYear(annee));
    }
}
