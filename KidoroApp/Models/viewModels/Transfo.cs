using KidoroApp.Models;

namespace Kidoro.Models.viewModels;

public class Transfo(List<Bloc> listBloc, List<Usuel> listUsuel)
{
    internal List<Bloc> ListBloc { get; set; } = listBloc;
    internal List<Usuel> ListUsuel { get; set; } = listUsuel;
}   