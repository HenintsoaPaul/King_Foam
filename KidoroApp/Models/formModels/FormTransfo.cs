namespace KidoroApp.Models.formModels
{
    public class FormTransfo
    {
        public string daty { get; set; }
        public string id_bloc { get; set; }
        public double longueur { get; set; }
        public double largeur { get; set; }
        public double hauteur { get; set; }
        public List<FormUsuel> usuels { get; set; }
    }
}
