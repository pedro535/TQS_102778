import { useState } from "react"
import axios from "axios"
import MyNavbar from "../components/MyNavbar"
import ResultCard from "../components/ResultCard"
import { Spinner } from "flowbite-react"
import { BiSearchAlt } from "react-icons/bi"
import Map from "../components/Map"


const baseURL = "http://localhost:8080/api/airquality/current"

function AirQualityCurrent() {

    const [response, setResponse] = useState({});
    const [showResults, setShowResults] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);


    const handleSubmit = (event) => {

        //reset state
        setShowResults(false);
        setResponse({});
        setError(false);
        setLoading(true);

        event.preventDefault();
        const city = event.target.city.value;
        const countryCode = event.target.countryCode.value;

        axios.get(baseURL, {
            params: {
                "city": city,
                "countryCode": countryCode
            }
        })
        .then((response) => {
            setResponse(response.data);
            setLoading(false);
            setShowResults(true);
        })
        .catch((error) => {
            setLoading(false);
            setError(true);
        })
    }



    const renderLoading = () => {
        return (
            <div className='w-fit mx-auto'>
                <Spinner color="purple" size='xl' />
            </div>
        )
    }



    const renderError = () => {
        return (
            <div className='w-fit mx-auto'>
                <p id="error-location" className="text-xl text-white">There are no results for this location</p>
            </div>
        )
    }



    const renderResults = () => {
        const results = response.results;
        const days = Object.keys(results);

        return (
            <div>
                <p id="search-summary" className="text-xl text-white inline mr-3">Today's Air Quality in "{response.city}"</p>
                <span id="coords" className=" text-white font-light text-sm">({response.coord.lat}, {response.coord.lon})</span>
                
                <div className='w.full h-72 my-4'>
                    <Map coord={response.coord} />
                </div>

                <div>
                    {results[days[0]].map((r) => {
                        return (
                            <ResultCard key={r.dateTime} results={r} />
                        )
                    })}
                </div>
            </div>
        )
    }



    return (
        <>
            <MyNavbar />

            <div className="container mx-auto py-10">

                <div className="text-center mt-10 mb-14">
                    <p className="text-5xl font-extrabold text-white">Today's Air Quality</p>
                </div>

                
                <form onSubmit={handleSubmit}>
                    <div className="w-1/2 left-1/2 relative -translate-x-1/2">
                        <div className="my-5">
                            <label htmlFor="city" className="block mb-1 text-sm font-medium text-white">City</label>
                            <input type="text" name="city" id="city" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" placeholder="City name" required />
                        </div>

                        <div className="my-5">
                            <label htmlFor="countryCode" className="block mb-1 text-sm font-medium text-white">Country Code</label>
                            <input type="text" name="countryCode" id="countryCode" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" placeholder="Country  (e.g. PT)" required />
                        </div>

                        <div className="my-5 text-center">
                            <button id="search-todays-AQ" type="submit" className="my-btn mx-4">
                                Search
                                <BiSearchAlt className='inline-block ml-5' size={25} />
                            </button>
                        </div>
                    </div>
                </form>


                <div className='relative lg:left-1/2 lg:-translate-x-1/2 lg:w-3/4 my-16  px-4'>
                    {loading ? renderLoading() : <></>}
                    {showResults ? renderResults() : <></>}
                    {error ? renderError() : <></>}
                </div>

            </div>
        </>
    )
    
  }
  
  export default AirQualityCurrent
  