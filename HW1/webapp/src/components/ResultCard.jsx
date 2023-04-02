import { Card } from 'flowbite-react';
import { CircularProgressbar } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';

const chartStyle = {
    path: {stroke: '#22c55e'},
    text: {fill: '#000000', fontSize: '30px', fontWeight: '500'},
    trail: {stroke: '#cbd5e1'}
}


function AirQualityBanner(props) {
    const { dateTime, classification, components } = props.results;
    const airClassification = ["Good", "Fair", "Moderate", "Poor", "Very Poor"];
    const airClassificationPercentage = [95, 80, 60, 40, 20];

    
    const getDateName = () => {
        const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        const date = new Date(dateTime);
        return `${months[date.getMonth()]} ${date.getDate()}, ${date.getFullYear()}`;
    }


    return (
        <>
            <Card className='my-4'>
                <div className='grid grid-cols-12 gap-1'>

                    <div className='col-span-2 p-1 w-3/4'>
                        <div className='relative top-1/2 -translate-y-1/2'>
                            <CircularProgressbar value={airClassificationPercentage[classification - 1]} text={classification} styles={chartStyle} />
                            <div className='text-center mt-2 font-bold'>
                                {airClassification[classification - 1]}
                            </div>
                        </div>
                    </div>

                    <div className='col-span-10'>
                        <p className="text-lg font-bold tracking-tight text-gray-900 dark:text-white inline mr-3">{getDateName()}</p>
                        <span className='font-light text-xs'>{dateTime.split(" ")[1]}</span>

                        <div className="relative overflow-x-auto mt-4 sm:rounded-lg shadow-sm">
                            <table className="w-full text-sm text-left">
                                <thead className="text-xs text-black uppercase bg-gray-200 ">
                                    <tr>
                                        <th scope="col" className="px-6 py-3">CO</th>
                                        <th scope="col" className="px-6 py-3">NO</th>
                                        <th scope="col" className="px-6 py-3">NO2</th>
                                        <th scope="col" className="px-6 py-3">O3</th>
                                        <th scope="col" className="px-6 py-3">SO2</th>
                                        <th scope="col" className="px-6 py-3">PM2.5</th>
                                        <th scope="col" className="px-6 py-3">PM10</th>
                                        <th scope="col" className="px-6 py-3">NH3</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr className="text-xs text-black uppercase bg-white ">
                                        <td className="px-6 py-4">{components.co}</td>
                                        <td className="px-6 py-4">{components.no}</td>
                                        <td className="px-6 py-4">{components.no2}</td>
                                        <td className="px-6 py-4">{components.o3}</td>
                                        <td className="px-6 py-4">{components.so2}</td>
                                        <td className="px-6 py-4">{components.pm2_5}</td>
                                        <td className="px-6 py-4">{components.pm10}</td>
                                        <td className="px-6 py-4">{components.nh3}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </Card>
        </>
    )
    
  }
  
  export default AirQualityBanner
  