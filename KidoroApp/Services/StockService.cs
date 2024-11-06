using KidoroApp.Models;
using KidoroApp.Models.stock;
using KidoroApp.Models.viewModels;

namespace Kidoro.Services;

public class StockService(HttpClient httpClient)
{
    public StockViewModel GetStock()
    {
        return new StockViewModel(GetStockUsuel(), GetStockBloc());
    }

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

    public static List<StockBloc> GetStockBloc()
    {
        return new List<StockBloc>
            {
                new StockBloc
                {
                    idBloc = "B001",
                    prixRevient = 5000,
                    prixVenteOptimiste = 7500m,
                    prixVenteVolMin = 6000
                },
                new StockBloc
                {
                    idBloc = "B002",
                    prixRevient = 8000,
                    prixVenteOptimiste = 11000m,
                    prixVenteVolMin = 9000
                },
                new StockBloc
                {
                    idBloc = "B003",
                    prixRevient = 4000,
                    prixVenteOptimiste = 5500m,
                    prixVenteVolMin = 4500
                }
            };
    }
}