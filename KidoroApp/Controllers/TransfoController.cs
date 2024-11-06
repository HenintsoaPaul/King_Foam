using Kidoro.Models.viewModels;
using Kidoro.Services;
using KidoroApp.Models;
using KidoroApp.Models.formModels;
using KidoroApp.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers
{
    public class TransfoController(HttpClient httpClient) : Controller
    {
        private readonly KidoroService _kidoroService = new(httpClient);

        public IActionResult Index()
        {
            return View("Add");
        }

        public async Task<IActionResult> Add()
        {
            List<Bloc> listBloc = await _kidoroService.GetAllBloc();
            List<Usuel> listUsuel = await _kidoroService.GetAllUsuel();
            var data = new Transfo(listBloc, listUsuel);
            return View(data);
        }

        [HttpPost]
        public Task<IActionResult> CreateTransfo(double longueur, double largeur, double h, string daty, double prixRevient)
        {
            FormTransfo Transfo = new FormTransfo(longueur, largeur, h, daty, prixRevient);

            string endPoint = "transfos";
            _ = ServletService.Send(Transfo, endPoint);

            var view = View("Index", Transfo);
            return Task.FromResult<IActionResult>(view);
        }
    }
}
