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

        public double GetSumVolUsuels(List<Usuel> listUsuels)
        {
            double sum = 0;
            if (usuels != null)
            {
                foreach (FormUsuel fu in usuels)
                {
                    var usuel = fu.GetUsuel(listUsuels);
                    if (usuel != null)
                    {
                        sum += usuel.GetVolume() * fu.quantite;
                    }
                }
            }
            return sum;
        }

        public double GetVolResteReel()
        {
            return longueur * largeur * hauteur;
        }
    }
}
