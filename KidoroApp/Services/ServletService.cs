using System.Text;
using Newtonsoft.Json;

namespace KidoroApp.Services
{
    public class ServletService
    {
        private static string apiUrl = "http://localhost:8081/kidoro/";

        public static async Task SendPost(Object o, string endPoint)
        {
            var json = JsonConvert.SerializeObject(o);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                string requestUri = apiUrl + endPoint;
                using var client = new HttpClient();
                var response = await client.PostAsync(requestUri, content);
                response.EnsureSuccessStatusCode();
                Console.WriteLine($"R�ponse du servlet : {await response.Content.ReadAsStringAsync()}");
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"Erreur lors de l'envoi vers le servlet : {ex.Message}");
                Console.WriteLine(ex.StackTrace);
            }
        }

        public static async Task SendPut(Object o, string endPoint)
        {
            var json = JsonConvert.SerializeObject(o);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                string requestUri = apiUrl + endPoint;
                using var client = new HttpClient();
                var response = await client.PutAsync(requestUri, content);
                response.EnsureSuccessStatusCode();
                Console.WriteLine($"R�ponse du servlet : {await response.Content.ReadAsStringAsync()}");
            }
            catch (HttpRequestException ex)
            {
                Console.WriteLine($"Erreur lors de l'envoi vers le servlet : {ex.Message}");
                Console.WriteLine(ex.StackTrace);
            }
        }
    }
}
