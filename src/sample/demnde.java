package sample;

public class demnde {
    public String prod;
    public int cont;
    public int vente;
    public Double profit;
    public Double total;

    public demnde(String prod, int cont, int vente, Double profit) {
        this.prod = prod;
        this.cont = cont;
        this.vente = vente;
        this.profit = profit;
    }
    public demnde(String prod, int cont, int vente, Double profit,Double total) {
        this.prod = prod;
        this.cont = cont;
        this.vente = vente;
        this.profit = profit;
        this.total=total;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getVente() {
        return vente;
    }

    public void setVente(int vente) {
        this.vente = vente;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
