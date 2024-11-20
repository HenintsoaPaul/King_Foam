package fabrication.lib;

import bean.ClassMAPTable;

public class PrPratiqueLib extends ClassMAPTable {

    double sum_pr_pratique;
    double sum_volume;
    double pr_pratique_volumique;
    double avg_pr_pratique_volumique;

    // Constr
    public PrPratiqueLib() {
        this.setNomTable( "pr_pratique_lib" );
    }

    // Getters n Setters
    public double getSum_pr_pratique() {
        return sum_pr_pratique;
    }

    public void setSum_pr_pratique( double sum_pr_pratique ) {
        this.sum_pr_pratique = sum_pr_pratique;
    }

    public double getSum_volume() {
        return sum_volume;
    }

    public void setSum_volume( double sum_volume ) {
        this.sum_volume = sum_volume;
    }

    public double getPr_pratique_volumique() {
        return pr_pratique_volumique;
    }

    public void setPr_pratique_volumique( double pr_pratique_volumique ) {
        this.pr_pratique_volumique = pr_pratique_volumique;
    }

    public double getAvg_pr_pratique_volumique() {
        return avg_pr_pratique_volumique;
    }

    public void setAvg_pr_pratique_volumique( double avg_pr_pratique_volumique ) {
        this.avg_pr_pratique_volumique = avg_pr_pratique_volumique;
    }

    // Overrides
    @Override
    public String getTuppleID() {
        return "";
    }

    @Override
    public String getAttributIDName() {
        return "";
    }
}
