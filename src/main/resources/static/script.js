function displayItems(songs) {
    for (let i = 0; i < songs.length; i++) {
        if (i === 0) {
            document.getElementById('list').innerHTML +=
                '<li id="' + songs[i].id + '">' +
                '<article class="background">' +
                '<img src="' + songs[i].coverImageUrl + '" alt="album-cover" class="song" width="160" height="160"/>' +
                '<div class="list-div" id="div' + songs[i].id + '">' +
                '<h3 id="song' + songs[i].id + '">' + songs[i].songName + '</h3>' +
                '<h5 id="artist' + songs[i].id + '">' + songs[i].artist + '</h5>' +
                '<p id="album' + songs[i].id + '">' + 'Album: ' + songs[i].album + '</p>' +
                '<p id="rating' + songs[i].id + '">' + 'Pitchfork rating: ' + songs[i].pitchforkAlbumRating + '</p>' +
                '<button id="' + songs[i].id + '" type="button" class="delete" onclick="deleteRecord()" style="display: none;">' + 'Delete' + '</button>' +
                '<button id="f' + songs[i].id + '" type="button" class="fav" onclick="addToFavorites()">' + 'Add to favorites' + '</button>' +
                '<button id="d' + songs[i].id + '" type="button" class="fav-del" onclick="removeFromFavorites()">' + 'Remove from favorites' + '</button>' +
                '</div>' +
                '</article>' +
                '</li>';

            continue;
        }

        document.getElementById('list').innerHTML +=
            '<br>' +
            '<li id="' + songs[i].id + '">' +
            '<article class="background">' +
            '<img src="' + songs[i].coverImageUrl + '" alt="album-cover" class="song" width="160" height="160"/>' +
            '<div class="list-div" id="div' + songs[i].id + '">' +
            '<h3 id="song' + songs[i].id + '">' + songs[i].songName + '</h3>' +
            '<h5 id="artist' + songs[i].id + '">' + songs[i].artist + '</h5>' +
            '<p id="album' + songs[i].id + '">' + 'Album: ' + songs[i].album + '</p>' +
            '<p id="rating' + songs[i].id + '">' + 'Pitchfork rating: ' + songs[i].pitchforkAlbumRating + '</p>' +
            '<button id="' + songs[i].id + '" type="button" class="delete" onclick="deleteRecord()" style="display: none;">' + 'Delete' + '</button>' +
            '<button id="f' + songs[i].id + '" type="button" class="fav" onclick="addToFavorites()">' + 'Add to favorites' + '</button>' +
            '<button id="d' + songs[i].id + '" type="button" class="fav-del" onclick="removeFromFavorites()">' + 'Remove from favorites' + '</button>' +
            '</div>' +
            '</article>' +
            '</li>';
    }
}

function removeFromFavorites() {
    let buttonSongId = this.event.target.id.substring(1);
    fetch("http://localhost:8080/api/v1/users/auth-user")
        .then(
            response => response.text()
        ).then(
        user => {
            fetch("http://localhost:8080/api/v1/users/id",
                {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json, text/plain, */*',
                        'Content-Type': 'application/json'
                    },
                    body: user
                }
            ).then(response => response.text())
                .then(id => {
                        let info = {userId: id, songId: buttonSongId};
                        fetch("http://localhost:8080/api/v1/songs/favorites/remove",
                            {
                                method: "DELETE",
                                headers: {
                                    'Accept': 'application/json, text/plain, */*',
                                    'Content-Type': 'application/json'
                                },
                                body: JSON.stringify(info)
                            })
                            .then(response => response.text())
                            .then(songs => {
                            })
                    }
                );
        }
    );
}

function addToFavorites() {
    let buttonSongId = this.event.target.id.substring(1);
    fetch("http://localhost:8080/api/v1/users/auth-user")
        .then(
            response => response.text()
        ).then(
        user => {
            fetch("http://localhost:8080/api/v1/users/id",
                {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json, text/plain, */*',
                        'Content-Type': 'application/json'
                    },
                    body: user
                }
            ).then(response => response.text())
                .then(id => {
                    let info = {userId: id, songId: buttonSongId};
                    console.log(info);
                    fetch("http://localhost:8080/api/v1/songs/favorites/add",
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
                        })
                }
            );
        }
    );
}

function deleteRecord() {
    fetch("http://localhost:8080/api/v1/songs",
        {
            method: "DELETE",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: this.event.target.id
        })
        .then(response => response.json())
        .then(songs => {
            clearHTML();
            displayItems(songs);

            let elements = document.getElementsByClassName('delete');
            for (let i = 0; i < elements.length; i++) {
                elements[i].style.display = 'inline';
            }

            document.getElementById('add').style.display = 'inline';
        })
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
    if (document.getElementById('all-rec').checked || document.getElementById('edit-list').checked) {
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
    document.getElementById('sort').disabled = false;
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
    document.getElementById('sort').disabled = false;
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

    hideButtons();
}

function searchRecords() {
    document.getElementById('art-search').disabled = false;
    document.getElementById('alb-search').disabled = false;
    document.getElementById('gen-search').disabled = false;
    document.getElementById('add-form').style.display = 'none';
    document.getElementById('sort').disabled = true;

    hideButtons();
}

function hideButtons() {
    let elements = document.getElementsByClassName('delete');
    for (let i = 0; i < elements.length; i++) {
        elements[i].style.display = 'none';
    }

    document.getElementById('add').style.display = 'none';
}

function add() {
    document.getElementById('add-form').style.display = 'block';
}

function submit() {
    let newSong = {
        songName: document.getElementById('song-name').value,
        artist: document.getElementById('artist').value,
        album: document.getElementById('album').value,
        releaseDate: document.getElementById('release-date').value,
        genre: document.getElementById('genre').value,
        coverImageUrl: document.getElementById('cover').value,
        pitchforkAlbumRating: document.getElementById('rating').value
    };

    fetch("http://localhost:8080/api/v1/songs",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newSong)
        })
        .then(response => response.json())
        .then(songs => {
            clearHTML();
            displayItems(songs);
        })
}

function editList() {
    document.getElementById('sort').disabled = false;
    fetch("http://localhost:8080/api/v1/songs")
        .then(
            response => response.json()
        ).then(
        songs => {
            clearHTML();
            displayItems(songs);

            let elements = document.getElementsByClassName('delete');
            for (let i = 0; i < elements.length; i++) {
                elements[i].style.display = 'inline';
            }

            document.getElementById('add').style.display = 'inline';
        }
    );
}

function adminSection() {
    fetch("http://localhost:8080/api/v1/users/auth-user")
        .then(
            response => response.text()
        ).then(
        user => {
            fetch("http://localhost:8080/api/v1/users/admins")
                .then(
                    response => response.json()
                ).then(
                admins => {
                    document.getElementById('user').innerHTML +=
                        `${user}`;

                    for (let i = 0; i < admins.length; i++) {
                        if (user === admins[i]) {
                            document.getElementById('edit-list').style.display = 'inline';
                            document.getElementById('label-edit-list').style.display = 'inline';
                            break;
                        }
                    }
                }
            );
        }
    );
}

function showSongs() {
    fetch("http://localhost:8080/api/v1/songs")
        .then(
            response => response.json()
        ).then(
        songs => {
            displayItems(songs);
        }
    );
}

function getFavorites() {
    document.getElementById('sort').disabled = true;
    fetch("http://localhost:8080/api/v1/users/auth-user")
        .then(
            response => response.text()
        ).then(
        user => {
            let username = {
                username: user,
                firstName: null,
                lastName: null,
                password: null,
                role: null
            };

            fetch("http://localhost:8080/api/v1/songs/favorites",
                {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json, text/plain, */*',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(username)
                })
                .then(response => response.json())
                .then(songs => {
                    clearHTML();
                    displayItems(songs);
                })
        }
    );
}

getCategories("artists");
getCategories("albums");
getCategories("genres");

adminSection();

showSongs();