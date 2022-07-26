function displayItems(songs) {
    for (let i = 0; i < songs.length; i++) {
        if (i === 0) {
            document.getElementById('list').innerHTML +=
                '<li id="' + i + '">' +
                '<article class="background">' +
                '<img src="' + songs[i].coverImageUrl + '" alt="album-cover" class="song" width="160" height="160"/>' +
                '<div class="list-div">' +
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
            '<div class="list-div">' +
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

function sort(artistParam, albumParam, genreParam) {
    let el1 = document.getElementById("criteria");
    let crit = el1.options[el1.selectedIndex].text;

    let el2 = document.getElementById("order");
    let ord = el2.options[el2.selectedIndex].text;

    let info = {
        criteria: crit,
        order: ord,
        artistParam: artistParam,
        albumParam: albumParam,
        genreParam: genreParam
    };

    fetch("http://localhost:8080/api/v1/songs/sort",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(info)
        })
        .then(response => response.json())
        .then(songs => {
            clearHTML();
            displayItems(songs);
        })
}

function sortAll() {
    let el1 = document.getElementById("criteria");
    let crit = el1.options[el1.selectedIndex].text;

    let el2 = document.getElementById("order");
    let ord = el2.options[el2.selectedIndex].text;

    let info = {
        criteria: crit,
        order: ord,
        artistParam: 'all',
        albumParam: 'all',
        genreParam: 'all'
    };

    fetch("http://localhost:8080/api/v1/songs/sort",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(info)
        })
        .then(response => response.json())
        .then(songs => {
            clearHTML();
            displayItems(songs);
        })
}

function sortSomehow() {
    if (document.getElementById('all-rec').checked) {
        sortAll();
    } else {
        sort(document.getElementById('artists').value,
            document.getElementById('albums').value,
            document.getElementById('genres').value);
    }
}

function getCategories(name) {
    fetch(`http://localhost:8080/api/v1/songs/${name}`)
        .then(
            response => response.json()
        ).then(
        songs => {
            for (let i = 0; i < songs.length; i++) {
                document.getElementById(name).innerHTML +=
                    '<option value="' + songs[i] + '">' + songs[i] + '</option>';
            }
        }
    );
}

function next(label, category, search) {
    document.getElementById(label).style.display = 'inline';
    document.getElementById(category).style.display = 'inline';
    document.getElementById(search).style.display = 'inline';
}

function search() {
    let searchParams = {
        criteria: null,
        order: null,
        artistParam: document.getElementById('artists').value,
        albumParam: document.getElementById('albums').value,
        genreParam: document.getElementById('genres').value
    };

    fetch("http://localhost:8080/api/v1/songs/search",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(searchParams)
        })
        .then(response => response.json())
        .then(songs => {
            clearHTML();
            displayItems(songs);
        })
}

function showAll() {
    fetch("http://localhost:8080/api/v1/songs")
        .then(
            response => response.json()
        ).then(
        songs => {
            clearHTML();
            displayItems(songs);
        }
    );

    document.getElementById('art-search').disabled = true;
    document.getElementById('alb-search').disabled = true;
    document.getElementById('gen-search').disabled = true;
}

function searchRecords() {
    document.getElementById('art-search').disabled = false;
    document.getElementById('alb-search').disabled = false;
    document.getElementById('gen-search').disabled = false;
}

getCategories("artists");
getCategories("albums");
getCategories("genres");

fetch("http://localhost:8080/api/v1/songs")
    .then(
        response => response.json()
    ).then(
    songs => {
        displayItems(songs);
    }
);