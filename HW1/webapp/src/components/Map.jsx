import { GoogleMap, Marker, useJsApiLoader } from "@react-google-maps/api"



function Map(props) {

    const center = {
        lat: props.coord.lat,
        lng: props.coord.lon
    }

    const { isLoaded } = useJsApiLoader({
        googleMapsApiKey: "AIzaSyCFt_kSC4GXSHvkqPqeCbMuCa1qNxKTwes"
    });
    
    if (!isLoaded) {
        return (
            <p>Loading...</p>
        )
    }
    
    return (
        <>
            <GoogleMap
                zoom={10}
                center={center}
                mapContainerStyle={{"width": "100%", "height": "100%", "borderRadius": "8px"}}
                options={{
                    zoomControl: false,
                    streetViewControl: false,
                    fullscreenControl: false,
                    mapTypeControl: false
                }}
                >
                <Marker position={center} />
            </GoogleMap>
        </>
    )
    
  }
  
  export default Map
  