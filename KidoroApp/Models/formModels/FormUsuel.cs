namespace KidoroApp.Models.formModels
{
    public class FormUsuel(string u, int qte)
    {
        public string val_usuel { get; set; } = u;
        public int quantite { get; set; } = qte;

        public Usuel GetUsuel(List<Usuel> listUsuel) {
            return listUsuel.FirstOrDefault(listUsuel => listUsuel.val == this.val_usuel);
        }
    }
}