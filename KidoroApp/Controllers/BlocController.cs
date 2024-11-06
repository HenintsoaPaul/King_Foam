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
            FormBlock block = new FormBlock(longueur, largeur, h, daty, prixRevient);

            string endPoint = "blocs";
            _ = ServletService.Send(block, endPoint);

            var view = View("Index", block);
            return Task.FromResult<IActionResult>(view);
        }

        private static async Task SendToServlet(FormBlock block, string endPoint)
        {
            var json = JsonConvert.SerializeObject(block);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                using var client = new HttpClient();
                string requestUri = "http://localhost:8081/kidoro/" + endPoint;
                var response = await client.PostAsync(requestUri, content);
                response.EnsureSuccessStatusCode();
                Console.WriteLine($"R�ponse du servlet : {await response.Content.ReadAsStringAsync()}");
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"Erreur lors de l'envoi � le servlet : {ex.Message}");
                Console.WriteLine(ex.StackTrace);
            }
        }
    }
}
