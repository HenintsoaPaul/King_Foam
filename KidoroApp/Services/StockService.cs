using KidoroApp.Models;

namespace Kidoro.Services;

public class StockService(HttpClient httpClient)
{
    public List<StockUsuel> GetStockUsuel()
    {
        return new List<StockUsuel>
            {
                new StockUsuel
                {
                    idForme = "F01",
                    quantite = 100,
                    sumPrixVente = 150m,
                    sumPrixRevient = 120
                },
                new StockUsuel
                {
                    idForme = "F02",
                    quantite = 50,
                    sumPrixVente = 200m,
                    sumPrixRevient = 180
                },
                new StockUsuel
                {
                    idForme = "F03",
                    quantite = 75,
                    sumPrixVente = 300m,
                    sumPrixRevient = 250
                }
            };
    }
}