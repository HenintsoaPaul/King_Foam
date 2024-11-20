package fabrication.lib;

import bean.ClassMAPTable;

public class PerfoLib extends ClassMAPTable {

    String id_machine;
    double vol_total;
    double sum_pr_pratique, sum_pr_theorique;
    double diff_th_reel, performance;

    // Constr
    public PerfoLib() {
        this.setNomTable( "perfo_lib" );
    }

    // Getters n Setters
    public String getId_machine() {
        return id_machine;
    }

    public void setId_machine( String id_machine ) {
        this.id_machine = id_machine;
    }

    public double getVol_total() {
        return vol_total;
    }

    public void setVol_total( double vol_total ) {
        this.vol_total = vol_total;
    }

    public double getSum_pr_pratique() {
        return sum_pr_pratique;
    }

    public void setSum_pr_pratique( double sum_pr_pratique ) {
        this.sum_pr_pratique = sum_pr_pratique;
    }

    public double getSum_pr_theorique() {
        return sum_pr_theorique;
    }

    public void setSum_pr_theorique( double sum_pr_theorique ) {
        this.sum_pr_theorique = sum_pr_theorique;
    }

    public double getDiff_th_reel() {
        return diff_th_reel;
    }

    public void setDiff_th_reel( double diff_th_reel ) {
        this.diff_th_reel = diff_th_reel;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance( double performance ) {
        this.performance = performance;
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
