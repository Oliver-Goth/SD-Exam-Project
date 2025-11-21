import { Routes, Route } from 'react-router-dom';
import Menu from './Menu';
import Tracking from './Tracking';

function App() {
  return (
    <div className="layout-wrapper">
      <div className="content-wrapper">
        <div className='content'>
          <Routes>
            <Route path="/menu" element={<Menu />} />
            <Route path="/tracking" element={<Tracking />} />
          </Routes>
        </div>
      </div>
    </div>
  )
}

export default App;
