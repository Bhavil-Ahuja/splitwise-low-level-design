package dto.expense;

public class Split {
    int owerUserId;
    double val;

    public Split(int owerUserId, double val) {
        this.owerUserId = owerUserId;
        this.val = val;
    }

    public int getOwerUserId() {
        return owerUserId;
    }

    public void setOwerUserId(int owerUserId) {
        this.owerUserId = owerUserId;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }
}
