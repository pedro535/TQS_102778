import { useNavigate } from 'react-router-dom';
import ResultCard from "../components/ResultCard"
import { BiSearchAlt } from "react-icons/bi"
import MyNavbar from "../components/MyNavbar"

function Homepage() {

    const navigate = useNavigate();

    return (
        <>
            <MyNavbar />

            <div className="container mx-auto py-10">
                <div className="text-center mt-10 mb-14">
                    <p className="text-5xl font-extrabold text-white">Air Quality</p>
                </div>


                <div className="flex justify-center">
                    <div className="">
                        <button className="my-btn w-96 mx-4" onClick={() => {navigate('/current')}}>
                            Today's Air Quality
                            <BiSearchAlt className='inline-block ml-5' size={25} />
                        </button>
                    </div>
                    <div className=" text-right">
                        <button className="my-btn w-96 mx-4" onClick={() => {navigate('/forecast')}}>
                            Air Quality Forecast
                            <BiSearchAlt className='inline-block ml-5' size={25} />
                        </button>
                    </div>
                </div>


                <div className='relative left-1/2 -translate-x-1/2 xl:w-3/4 my-16  px-8'>
                    <p className="text-xl text-white">Today's Air Quality in {"--CITY--"}</p>

                    {/* FOR LOOP HERE */}
                    <ResultCard />

                </div>
            </div>
        </>

    )
    
  }
  
  export default Homepage
  