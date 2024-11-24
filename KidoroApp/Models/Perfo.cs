namespace KidoroApp.Models;

public class Perfo
{
    public string id_machine { get; set; }
    public double vol_total { get; set; }
    public double sum_pr_theorique { get; set; }
    public double sum_pr_pratique { get; set; }
    public double diff_th_reel { get; set; }

    public double GetPerformance()
    {
        return (this.diff_th_reel / this.sum_pr_theorique) * 100 ;
    }
}
