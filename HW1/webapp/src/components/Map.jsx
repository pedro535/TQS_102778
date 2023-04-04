import { GoogleMap, Marker, useJsApiLoader } from "@react-google-maps/api"
import { Spinner } from "flowbite-react"



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
            <>
                <div className='w-fit mx-auto'>
                    <Spinner color="purple" />
                </div>
            </>
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
  