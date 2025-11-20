import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import Payment from './pages/payment.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
    <Payment />
  </StrictMode>,
)
