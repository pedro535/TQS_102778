import { Link } from "react-router-dom"

function MyNavbar() {

    return (
        <>
            <nav className="bg-dark-blue">
                <div className="flex flex-wrap items-center justify-between mx-auto py-3 px-10">
                    <a href="/" className="flex items-center">
                        <span className="self-center text-2xl font-semibold whitespace-nowrap text-white">Air Quality</span>
                    </a>


                    <div className="hidden w-full md:block md:w-auto" id="navbar-default">
                        <ul className="font-medium flex flex-col p-4 md:p-0 mt-4 border md:flex-row md:space-x-8 md:mt-0 md:border-0">
                            <li>
                                <Link className="block py-2 px-1 hover:text-gray-400 text-white font-light text-sm" to={"/"}>Home</Link>
                            </li>

                            <li>
                                <Link className="block py-2 px-1 hover:text-gray-400 text-white font-light text-sm" to={"/current"}>Today's Air Quality</Link>
                            </li>

                            <li>
                                <Link className="block py-2 px-1 hover:text-gray-400 text-white font-light text-sm" to={"/forecast"}>Air Quality Forecast</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </>
    )
    
  }
  
  export default MyNavbar
  