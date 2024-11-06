using KidoroApp.Models;
using KidoroApp.Models.formModels;
using KidoroApp.Services;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;

namespace KidoroApp.Controllers
{
    public class BlocController : Controller
    {

        public IActionResult Index()
        {
            return View("Add");
        }

        public IActionResult Add()
        {
            return View();
        }

        [HttpPost]
        public Task<IActionResult> CreateBloc(double longueur, double largeur, double h, string daty, double prixRevient)
        {
            FormBloc block = new FormBloc(longueur, largeur, h, daty, prixRevient);

            string endPoint = "blocs";
            _ = ServletService.Send(block, endPoint);

            var view = View("Index", block);
            return Task.FromResult<IActionResult>(view);
        }
    }
}
