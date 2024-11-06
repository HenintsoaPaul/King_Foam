namespace KidoroApp.Models.formModels
{
    public class FormUsuel(string u, int qte)
    {
        public string val_usuel { get; set; } = u;
        public int quantite { get; set; } = qte;
    }
}