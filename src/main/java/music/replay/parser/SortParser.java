package music.replay.parser;

import music.replay.models.SortParameters;

public class SortParser {
    public static void parseSortParams(SortParameters sortParameters) throws Exception {
        switch (sortParameters.getCriteria()) {
            case "Album rating" -> sortParameters.setCriteria("pitchforkAlbumRating");
            case "Release date" -> sortParameters.setCriteria("releaseDate");
            case "Most listened" -> sortParameters.setCriteria("id");
            default -> throw new Exception();
        }
    }
}
