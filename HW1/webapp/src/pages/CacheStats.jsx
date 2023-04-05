import MyNavbar from "../components/MyNavbar"
import CacheChart from "../components/CacheChart"

function CacheStats() {

    return (
        <>
            <MyNavbar />

            <div className="container mx-auto py-5">
                <div className="text-center my-14">
                    <p id="page-title" className="text-5xl font-extrabold text-white">System Cache Statistics</p>
                </div>

                <CacheChart />
            </div>
        </>

    )
  }
  
  export default CacheStats
  