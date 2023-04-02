import { Card } from 'flowbite-react';
import { CircularProgressbar } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';

function AirQualityBanner() {

    const chartStyle = {
        path: {stroke: '#22c55e'},
        text: {fill: '#000000', fontSize: '30px', fontWeight: '500'},
        trail: {stroke: '#cbd5e1'}
    }

    return (

        <>
            <Card className='my-4'>
                <div className='grid grid-cols-12 gap-8'>
                    <div className='col-span-2 p-1'>
                        <CircularProgressbar value={80} text={"2"} styles={chartStyle} />
                        <div className='text-center mt-2 font-bold'>
                            Good
                        </div>
                    </div>

                    <div className='col-span-10'>
                        <h5 className="text-xl font-bold tracking-tight text-gray-900 dark:text-white inline mr-2">April 10, 2021</h5>
                        <span className='font-light text-sm'>14:00</span>

                        <div className="relative overflow-x-auto mt-4 sm:rounded-lg shadow-sm">
                            <table className="w-full text-sm text-left text-blue-100 dark:text-blue-100">
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
                                        <td className="px-6 py-4">203.61</td>
                                        <td className="px-6 py-4">0.0</td>
                                        <td className="px-6 py-4">2.66</td>
                                        <td className="px-6 py-4">62.64</td>
                                        <td className="px-6 py-4">1.24</td>
                                        <td className="px-6 py-4">03.89</td>
                                        <td className="px-6 py-4">7.01</td>
                                        <td className="px-6 py-4">1.44</td>
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
  