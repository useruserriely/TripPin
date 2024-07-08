package personal.project.TripPin.map;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsService {
    @Value("${google.maps.api.key}")
    private String apiKey;

    public PlaceDetails getPlaceDetails(String placeId) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        return PlacesApi.placeDetails(context, placeId).await();
    }
}
