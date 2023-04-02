import MyNavbar from "../components/MyNavbar"
import ResultCard from "../components/ResultCard"
import { BiSearchAlt } from "react-icons/bi"

function AirQualityCurrent() {

    return (
        <>
            <MyNavbar />

            <div className="container mx-auto py-10">

                <div className="text-center mt-10 mb-14">
                    <p className="text-5xl font-extrabold text-white">Today's Air Quality</p>
                </div>

                
                <form>
                    <div className="w-1/2 left-1/2 relative -translate-x-1/2">
                        <div className="my-5">
                            <label htmlFor="email" className="block mb-1 text-sm font-medium text-white">City</label>
                            <input type="text" id="email" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" placeholder="City name" required />
                        </div>

                        <div className="my-5">
                            <label htmlFor="password" className="block mb-1 text-sm font-medium text-white">Country Code</label>
                            <input type="text" id="password" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" placeholder="Country  (e.g. PT)" required />
                        </div>

                        <div className="my-5 text-center">
                            <button type="submit" className="my-btn mx-4" onClick={() => {navigate('/current')}}>
                                Search
                                <BiSearchAlt className='inline-block ml-5' size={25} />
                            </button>
                        </div>
                    </div>
                </form>


                <div className='relative left-1/2 -translate-x-1/2 xl:w-3/4 my-16  px-8'>
                    <p className="text-xl text-white">Today's Air Quality in {"--CITY--"}</p>

                    {/* FOR LOOP HERE */}
                    <ResultCard />

                </div>

            </div>
        </>
    )
    
  }
  
  export default AirQualityCurrent
  