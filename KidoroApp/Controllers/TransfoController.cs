using AspNetCoreGeneratedDocument;
using Kidoro.Services;
using KidoroApp.Models;
using KidoroApp.Models.formModels;
using KidoroApp.Models.viewModels;
using KidoroApp.Services;
using Microsoft.AspNetCore.Mvc;

namespace KidoroApp.Controllers
{
    public class TransfoController : Controller
    {
        private readonly KidoroService _kidoroService;

        public TransfoController(HttpClient httpClient)
        {
            _kidoroService = new KidoroService(httpClient);
        }

        public IActionResult Index()
        {
            return View();
        }

        public async Task<IActionResult> Add()
        {
            List<Bloc> listBloc = await _kidoroService.GetAllBlocInStock();
            List<Usuel> listUsuel = await _kidoroService.GetAllUsuel();
            var data = new Transfo(listBloc, listUsuel);

            data.Teta = await _kidoroService.GetTeta();

            return View(data);
        }

        [HttpPost]
        public async Task<IActionResult> CreateTransfo(
            string daty, string bloc,
            string longueur, string largeur, string hauteur,
            int King, int Queen, int Voay, int Eponge)
        {
            Console.WriteLine("Formulaire validé avec succès.");

            var arrUsuel = new List<FormUsuel>
            {
                new("King", King),
                new("Queen", Queen),
                new("Voay", Voay),
                new("Eponge", Eponge)
            };

            // Convertir les dimensions
            // verifier si longueur est de la form "<nb> m" ou "<nb> cm"
            double ConvertDimension(string dimension)
            {
                if (dimension.EndsWith(" cm"))
                {
                    double val = double.Parse(dimension.Replace(" cm", "")) / 100;
                    Console.WriteLine(dimension);
                    Console.WriteLine(val);
                    return val;
                }
                else if (dimension.EndsWith(" m"))
                {
                    return double.Parse(dimension.Replace(" m", ""));
                }
                else
                {
                    return double.Parse(dimension);
                }
            }


            var formTransfo = new FormTransfo
            {
                daty = daty,
                id_bloc = bloc,
                longueur = ConvertDimension(longueur),
                largeur = ConvertDimension(largeur),
                hauteur = ConvertDimension(hauteur),
                usuels = arrUsuel
            };

            // controller
            var teta = await _kidoroService.GetTeta();
            var valTeta = teta.val;
            Console.WriteLine($"Teta: {valTeta}");

            List<Usuel> listUsuel = await _kidoroService.GetAllUsuel();
            var volUsuels = formTransfo.GetSumVolUsuels(listUsuel);

            // 
            var allBloc = await _kidoroService.GetAllBlocInStock();
            Bloc selected = allBloc.FirstOrDefault(b => b.id == bloc);
            var volMere = selected.GetVolume();

            var resteReel = formTransfo.GetVolResteReel();
            var resteTh = volMere - volUsuels;
            var perte = resteTh - resteReel;
            var tolerence = volMere * valTeta;


            Console.WriteLine($"usuels: {volUsuels}");
            Console.WriteLine($"bloc: {volMere}");
            Console.WriteLine($"Reste Théorique: {resteTh}");
            Console.WriteLine($"Reste Réel: {resteReel}");
            Console.WriteLine($"Perte: {perte}");
            Console.WriteLine($"tolerance: {tolerence}");

            if (tolerence < perte)
            {
                TempData["ErrorMessage"] = "Perte > Tolerence!";

                return RedirectToAction("Add");
            }


            _ = ServletService.SendPost(formTransfo, "transfos");

            TempData["SuccessMessage"] = "Transformation insere avec succès!";

            return RedirectToAction("Add");
        }
    }
}
