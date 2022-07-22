package music.replay.models;

public class SortParameters {

    private String criteria;
    private String order;

    public SortParameters() {
    }

    public SortParameters(String criteria, String order) {
        this.criteria = criteria;
        this.order = order;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
