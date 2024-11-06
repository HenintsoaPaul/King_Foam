using Kidoro.Services;
using KidoroApp.Models;
using KidoroApp.Models.formModels;
using KidoroApp.Models.viewModels;
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
        public Task<IActionResult> CreateTransfo(
            string daty, string bloc,
            double longueur, double largeur, double hauteur,
            int F1, int F2, int F3, int Eponge)
        {
            Console.WriteLine("Formulaire validé avec succès.");

            var arrUsuel = new List<FormUsuel>
            {
                new("F1", F1),
                new("F2", F2),
                new("F3", F3),
                new("Eponge", Eponge)
            };

            var formTransfo = new FormTransfo
            {
                daty = daty,
                id_bloc = bloc,
                longueur = longueur,
                largeur = largeur,
                hauteur = hauteur,
                usuels = arrUsuel
            };

            _ = ServletService.Send(formTransfo, "transfos");

            var view = View("Index", formTransfo);
            return Task.FromResult<IActionResult>(view);
        }
    }
}
