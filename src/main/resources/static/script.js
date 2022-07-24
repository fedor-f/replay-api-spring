function displayItems(songs) {
    for (let i = 0; i < songs.length; i++) {
        if (i === 0) {
            document.getElementById('list').innerHTML +=
                '<li id="' + i + '">' +
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
            '<li id="' + i + '">' +
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

function clearHTML() {
    document.getElementById('container').innerHTML = '';
    document.getElementById('container').innerHTML = '<ul id="list"></ul>';
}

function sortSomehow() {
    let el1 = document.getElementById("criteria");
    let crit = el1.options[el1.selectedIndex].text;

    let el2 = document.getElementById("order");
    let ord = el2.options[el2.selectedIndex].text;

    let sortParams = {criteria: crit, order: ord};

    fetch("http://localhost:8080/api/v1/songs/sort",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(sortParams)
        })
        .then(response => response.json())
        .then(songs => {
            clearHTML();
            displayItems(songs);
        })
}

fetch("http://localhost:8080/api/v1/songs")
    .then(
        response => response.json()
    ).then(
    songs => {
        displayItems(songs);
    }
);