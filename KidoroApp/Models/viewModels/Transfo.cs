using System.ComponentModel.DataAnnotations;
using KidoroApp.Models.formModels;

namespace KidoroApp.Models.viewModels;

public class Transfo(List<Bloc> listBloc, List<Usuel> listUsuel)
{
    internal List<Bloc> ListBloc { get; set; } = listBloc;
    internal List<Usuel> ListUsuel { get; set; } = listUsuel;
    internal Teta? Teta { get; set; }
}
