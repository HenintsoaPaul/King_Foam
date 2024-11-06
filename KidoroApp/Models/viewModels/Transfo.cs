using System.ComponentModel.DataAnnotations;
using KidoroApp.Models.formModels;

namespace KidoroApp.Models.viewModels;

public class Transfo(List<Bloc> listBloc, List<Usuel> listUsuel)
{
    internal List<Bloc> ListBloc { get; set; } = listBloc;
    internal List<Usuel> ListUsuel { get; set; } = listUsuel;

    [Display(Name = "Daty")]
    [Required(ErrorMessage = "Le champ Daty est requis.")]
    public DateTime Daty { get; set; }

    [Display(Name = "Bloc")]
    [Required(ErrorMessage = "Le champ Bloc est requis.")]
    public int BlocId { get; set; }

    [Display(Name = "Longueur")]
    [Required(ErrorMessage = "Le champ Longueur est requis.")]
    [RegularExpression(@"^(\d+(\.\d{1,2})?)$", ErrorMessage = "Veuillez saisir une valeur numérique entre 0 et 100.")]
    public decimal Longueur { get; set; }

    [Display(Name = "Largeur")]
    [Required(ErrorMessage = "Le champ Largeur est requis.")]
    [RegularExpression(@"^(\d+(\.\d{1,2})?)$", ErrorMessage = "Veuillez saisir une valeur numérique entre 0 et 100.")]
    public decimal Largeur { get; set; }

    [Display(Name = "Hauteur")]
    [Required(ErrorMessage = "Le champ Hauteur est requis.")]
    [RegularExpression(@"^(\d+(\.\d{1,2})?)$", ErrorMessage = "Veuillez saisir une valeur numérique entre 0 et 100.")]
    public decimal Hauteur { get; set; }

    [Display(Name = "Usuels")]
    [Required(ErrorMessage = "Le champ Usuels est requis.")]
    public List<FormUsuel> Usuels { get; set; }
}
