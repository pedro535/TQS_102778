import { useEffect, useState } from 'react';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import { Bar } from 'react-chartjs-2';
import { Card, Spinner } from 'flowbite-react';
import axios from 'axios';

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);


const baseURL = "http://localhost:8080/api/cache/stats"


function CacheChart() {
    
    const [cacheStats, setCaheStats] = useState({});
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);
    const labels = ['Requests', 'Hits', 'Misses', 'Size'];
    
    
    const getCacheStats = () => {
        setLoading(true);
        
        //api call
        axios.get(baseURL)
        .then((response) => {
            setCaheStats(response.data);
            setLoading(false);
        })
        .catch((error) => {
            setLoading(false);
            setError(true);
        })
    }
    

    useEffect(getCacheStats, []);


    if (loading) {
        return (
            <Card className='my-4'>
                <div className='w-fit mx-auto'>
                    <Spinner color="purple" size='xl' />
                </div>
            </Card>
        )
    }
    
    if (error) {
        return (
            <div className='w-fit mx-auto'>
                <p className="text-xl text-white">Something went wrong</p>
            </div>
        )
    }


    const data = {
        labels,
        datasets: [
            {
                label: 'System Cache',
                data: [cacheStats.requests, cacheStats.hits, cacheStats.misses, cacheStats.size],
                backgroundColor: '#625877',
            }
        ],
    };
    
    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            }
        },
    };

    return (
        <>
            <Card className='my-4'>
                <div className='grid grid-cols-2 gap-5 p-10'>
                    <div>
                        <Bar options={options} data={data} />
                    </div>

                    <div>
                        <div className='relative top-1/2 -translate-y-1/2'>
                            <div className='text-center mb-4'>
                                <p className='font-bold text-xl inline mr-2'>Total Requests:</p>
                                <span id='cache-requests' className='font-light text-2xl'>{cacheStats.requests}</span>
                            </div>

                            <div className='text-center my-4'>
                                <p className='font-bold text-xl inline mr-2'>Misses:</p>
                                <span id='cache-misses' className='font-light text-2xl'>{cacheStats.misses}</span>
                            </div>

                            <div className='text-center my-4'>
                                <p className='font-bold text-xl inline mr-2'>Hits:</p>
                                <span id='cache-hits' className='font-light text-2xl'>{cacheStats.hits}</span>
                            </div>

                            <div className='text-center mt-4'>
                                <p className='font-bold text-xl inline mr-2'>Size:</p>
                                <span id='cache-size' className='font-light text-2xl'>{cacheStats.size}</span>
                            </div>
                        </div>
                    </div>

                </div>
            </Card>
        </>
    )
  }
  
  export default CacheChart
  