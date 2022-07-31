package music.replay.models;

public class SearchSortParameters {

    private String artistParam;
    private String albumParam;
    private String genreParam;
    private String criteria;
    private String order;

    public SearchSortParameters() {
    }

    public SearchSortParameters(String criteria,
                                String order,
                                String artistParam,
                                String albumParam,
                                String genreParam) {
        this.criteria = criteria;
        this.order = order;
        this.artistParam = artistParam;
        this.albumParam = albumParam;
        this.genreParam = genreParam;
    }

    public String getArtistParam() {
        return artistParam;
    }

    public void setArtistParam(String artistParam) {
        this.artistParam = artistParam;
    }

    public String getAlbumParam() {
        return albumParam;
    }

    public void setAlbumParam(String name) {
        this.albumParam = name;
    }

    public String getGenreParam() {
        return genreParam;
    }

    public void setGenreParam(String genreParam) {
        this.genreParam = genreParam;
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
