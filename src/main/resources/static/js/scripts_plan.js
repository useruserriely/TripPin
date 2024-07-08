let map;
let service;
let infowindow;

function initMap() {
    const osaka = { lat: 34.6937, lng: 135.5023 };
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: osaka
    });

    infowindow = new google.maps.InfoWindow();
    service = new google.maps.places.PlacesService(map);
}

document.addEventListener('DOMContentLoaded', () => {
    const searchBtn = document.getElementById('searchBtn');
    searchBtn.addEventListener('click', () => {
        const searchQuery = document.getElementById('placeSearch').value;
        if (searchQuery) {
            const request = {
                query: searchQuery,
                fields: ['name', 'geometry'],
            };
            service.findPlaceFromQuery(request, (results, status) => {
                if (status === google.maps.places.PlacesServiceStatus.OK && results) {
                    map.setCenter(results[0].geometry.location);
                    const marker = new google.maps.Marker({
                        position: results[0].geometry.location,
                        map: map,
                    });
                    infowindow.setContent(results[0].name);
                    infowindow.open(map, marker);
                }
            });
        }
    });
});