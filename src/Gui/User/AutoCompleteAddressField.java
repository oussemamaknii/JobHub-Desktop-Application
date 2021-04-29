package Gui.User;

import Gui.User.AutoCompleteTextField;
import Services.Register;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.QueryAutocompleteRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;

//https://stackoverflow.com/questions/37647933/how-can-i-use-the-google-maps-apis-in-a-javafx-desktop-application/37650603
public class AutoCompleteAddressField extends AutoCompleteTextField
{
    private static final String API_KEY = "AIzaSyBr4DKSW58r6tZXyZDYnPTBc7IRAQS2R1U";

    public class AddressPrediction
    {
        private final AutocompletePrediction prediction;

        public AddressPrediction(AutocompletePrediction prediction)
        {
            this.prediction = prediction;
        }

        @Override
        public String toString()
        {
            return prediction.description;
        }

        protected AutocompletePrediction getPrediction()
        {
            return this.prediction;
        }

    }

    public AutoCompleteAddressField()
    {
        super(new TreeSet<>(Comparator.comparing(AutoCompleteAddressField.AddressPrediction::toString)));

        textProperty().addListener((ObservableValue<? extends String> o, String oldValue, String newValue) ->
        {
            if (newValue != null && !newValue.isEmpty())
            {
                new Thread(() ->
                {
                    AutocompletePrediction[] predictions = getPredictions(getText());

                    Platform.runLater(() ->
                    {
                        getEntries().clear();
                        for (AutocompletePrediction prediction : predictions)
                        {
                            getEntries().add(new AddressPrediction(prediction));
                        }
                    });
                }).start();

            }
        });
    }

    public static PlaceDetails getPlace(AddressPrediction prediction)
    {
        GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyBr4DKSW58r6tZXyZDYnPTBc7IRAQS2R1U").queryRateLimit(200).build();
        if (prediction != null && prediction.getPrediction() != null && !prediction.getPrediction().placeId.isEmpty())
        {
            PlaceDetailsRequest query = PlacesApi.placeDetails(geoApiContext, prediction.getPrediction().placeId);
            return query.awaitIgnoreError();
        }
        geoApiContext.shutdown();
        return null;
    }

    public static AutocompletePrediction[] getPredictions(String userInput)
    {
        GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyBr4DKSW58r6tZXyZDYnPTBc7IRAQS2R1U").queryRateLimit(200).build();

        QueryAutocompleteRequest query = PlacesApi.queryAutocomplete(geoApiContext, userInput);
        try
        {
            return query.await();
        } catch (ApiException | InterruptedException | IOException ex)
        {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        geoApiContext.shutdown();
        return new AutocompletePrediction[0];

    }

    public static String getComponentLongName(AddressComponent[] components, AddressComponentType type)
    {

        for (AddressComponent component : components)
        {
            for (AddressComponentType types : component.types)
            {
                if (types.equals(type))
                {
                    return component.longName;
                }
            }
        }
        return "";
    }
}
