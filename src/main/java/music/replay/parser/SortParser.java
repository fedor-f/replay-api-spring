package music.replay.parser;

import music.replay.models.SearchSortParameters;

public class SortParser {
    public static void parseSortParams(SearchSortParameters searchSortParameters) throws Exception {
        switch (searchSortParameters.getCriteria()) {
            case "Album rating" -> searchSortParameters.setCriteria("pitchforkAlbumRating");
            case "Release date" -> searchSortParameters.setCriteria("releaseDate");
            case "Most listened" -> searchSortParameters.setCriteria("id");
            default -> throw new Exception();
        }
    }
}
