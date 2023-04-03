import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './index.css'
import Homepage from './pages/Homepage'
import AirQualityCurrent from './pages/AirQualityCurrent'
import AirQualityForecast from './pages/AirQualityForecast'
import CacheStats from './pages/CacheStats'


const router = createBrowserRouter([
  {path: '/', element: <Homepage />},
  {path: '/current', element: <AirQualityCurrent />},
  {path: '/forecast', element: <AirQualityForecast />},
  {path: '/cache', element: <CacheStats />}
])


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
