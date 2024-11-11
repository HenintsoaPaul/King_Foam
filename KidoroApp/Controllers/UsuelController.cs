using Kidoro.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers;

public class UsuelController(HttpClient httpClient) : Controller
{
    private readonly UsuelService _usuelService = new(httpClient);

    public async Task<IActionResult> Index()
    {
        return View(await _usuelService.GetAllUsuel());
    }
}
