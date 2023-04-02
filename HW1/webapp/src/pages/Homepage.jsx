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
                <div className='mt-28'>
                    <div className="text-center my-14">
                        <p className="text-5xl font-extrabold text-white">Air Quality</p>
                        <p className="mt-4 text-3xl font-extralight text-white">Stay informed about the air you breathe!</p>
                    </div>


                    <div className="flex justify-center">
                        <div className="">
                            <button className="my-btn w-80 mx-2" onClick={() => {navigate('/current')}}>
                                Today's Air Quality
                                <BiSearchAlt className='inline-block ml-5' size={25} />
                            </button>
                        </div>
                        <div className=" text-right">
                            <button className="my-btn w-80 mx-2" onClick={() => {navigate('/forecast')}}>
                                Air Quality Forecast
                                <BiSearchAlt className='inline-block ml-5' size={25} />
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </>

    )
    
  }
  
  export default Homepage
  