using Kidoro.Services;
using KidoroApp.Models.formModels;
using KidoroApp.Models.viewModels;
using KidoroApp.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers
{
    public class BlocController(HttpClient httpClient) : Controller
    {
        private readonly BlocService _blocService = new(httpClient);

        public async Task<IActionResult> Index()
        {
            var viewModel = new BlocViewModel(await _blocService.GetAllBlocMere(), await _blocService.GetAllBloc());
            return View(viewModel);
        }

        public IActionResult Add()
        {
            return View();
        }

        [HttpPost]
        public IActionResult CreateBloc(double longueur, double largeur, double h, string daty, double prixRevient)
        {
            FormBloc block = new FormBloc(longueur, largeur, h, daty, prixRevient);

            string endPoint = "blocs";
            _ = ServletService.SendPost(block, endPoint);

            TempData["SuccessMessage"] = "Bloc inseree avec succès!";

            return RedirectToAction("Add");
        }

        [HttpPost]
        public IActionResult UpdateBloc(string bloc, double prixRevient)
        {
            FormBloc block = new FormBloc(bloc, prixRevient);

            string endPoint = "blocs";
            _ = ServletService.SendPut(block, endPoint);

            TempData["SuccessMessage"] = "Bloc mis à jour avec succès!";

            return RedirectToAction("Index");
        }
    }
}
