using KidoroApp.Models.stock;

namespace KidoroApp.Models.viewModels;

public class BlocViewModel(List<Bloc> mere, List<Bloc> all)
{
    public List<Bloc> BlocMere { get; set; } = mere;
    public List<Bloc> BlocAll { get; set; } = all;
}
