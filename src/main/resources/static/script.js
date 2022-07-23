fetch("http://localhost:8080/api/v1/songs")
    .then(
        response => response.json()
    ).then(
    songs => {
        for (let i = 0; i < songs.length; i++) {
            if (i === 0) {
                document.getElementById('list').innerHTML +=
                    '<li>' +
                    '<article class="background">' +
                    '<img src="' + songs[i].coverImageUrl + '" alt="album-cover" class="song" width="160" height="160"/>' +
                    '<div>' +
                    '<h3>' + songs[i].songName + '</h3>' +
                    '<h5>' + songs[i].artist + '</h5>' +
                    '<p>' + 'Album: ' + songs[i].album + '</p>' +
                    '<p>' + 'Pitchfork rating: ' + songs[i].pitchforkAlbumRating + '</p>' +
                    '</div>' +
                    '</article>' +
                    '</li>';

                continue;
            }

            document.getElementById('list').innerHTML +=
                '<br>' +
                '<li>' +
                '<article class="background">' +
                '<img src="' + songs[i].coverImageUrl + '" alt="album-cover" class="song" width="160" height="160"/>' +
                '<div>' +
                '<h3>' + songs[i].songName + '</h3>' +
                '<h5>' + songs[i].artist + '</h5>' +
                '<p>' + 'Album: ' + songs[i].album + '</p>' +
                '<p>' + 'Pitchfork rating: ' + songs[i].pitchforkAlbumRating + '</p>' +
                '</div>' +
                '</article>' +
                '</li>';
        }
    }
);