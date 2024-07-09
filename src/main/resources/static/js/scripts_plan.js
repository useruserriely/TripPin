let map;
let markers = [];

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 34.6937, lng: 135.5023 },
        zoom: 12,
    });
}

document.getElementById('searchBtn').addEventListener('click', function() {
    const searchInput = document.getElementById('placeSearch').value;

    const request = {
        query: searchInput,
        fields: ['name', 'geometry'],
    };

    const service = new google.maps.places.PlacesService(map);
    service.findPlaceFromQuery(request, function(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            clearMarkers();
            results.forEach(place => {
                addMarker(place);
                document.getElementById('resultsList').innerHTML += `<li>${place.name}</li>`;
            });
            map.setCenter(results[0].geometry.location);
        }
    });
});

function addMarker(place) {
    const marker = new google.maps.Marker({
        map: map,
        position: place.geometry.location,
    });
    markers.push(marker);
}

function clearMarkers() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
    document.getElementById('resultsList').innerHTML = '';
}

function nextStep(step) {
    document.querySelectorAll('.step p').forEach((el, index) => {
        el.classList.toggle('current-step', index === step - 1);
    });
    document.querySelectorAll('.step-content').forEach((el, index) => {
        el.classList.toggle('hidden', index !== step - 1);
    });
}
