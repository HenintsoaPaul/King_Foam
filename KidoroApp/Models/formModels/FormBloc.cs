namespace KidoroApp.Models.formModels
{
    public class FormBloc
    {
        public double L { get; set; }
        public double l { get; set; }
        public double h { get; set; }
        public string daty { get; set; }
        public double prixRevient { get; set; }

        public FormBloc(double L, double l, double h, string strDaty, double prixRevient)
        {
            this.L = L;
            this.l = l;
            this.h = h;
            this.daty = strDaty;
            this.prixRevient = ValidatePrixRevient(prixRevient);
        }

        private double ValidatePrixRevient(double prixRevient)
        {
            if (prixRevient <= 0)
            {
                throw new ArgumentException("PrixRevient must be > 0.");
            }
            return prixRevient;
        }
    }
}
