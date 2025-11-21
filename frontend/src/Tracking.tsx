import { useState } from 'react';
import {
  PrimaryButton,
  Panel,
  PanelType,
} from '@fluentui/react';

export default function Tracking() {
  const [isPanelOpen, setIsPanelOpen] = useState(false);

  return (
    <div style={{ minHeight: '100vh', backgroundColor: '#ffffff', fontFamily: 'Segoe UI, sans-serif' }}>
      <header
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          padding: '0 32px',
          height: 80,
          borderBottom: '1px solid #ccc',
        }}
      >
        <h1 style={{ fontSize: '2rem', fontWeight: 'bold' }}>MTOGO</h1>
        <div style={{ display: 'flex', alignItems: 'center', gap: 16 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: 8, opacity: 0.9 }}>
            <span className="material-icons">location_on</span>
            <span>Your Location</span>
          </div>
          <PrimaryButton text="Menu Panel" onClick={() => setIsPanelOpen(true)} />
        </div>
      </header>

      <Panel
        isOpen={isPanelOpen}
        onDismiss={() => setIsPanelOpen(false)}
        type={PanelType.medium}
        headerText="Menu Panel"
      >
        <p>Account page here</p>
      </Panel>

      <main style={{ padding: 200 }}>
        <div style={{ display: 'flex', gap: 200 }}>
            {/* Left panel */}
            <div style={{ 
                display: "flex", 
                flexDirection: "column", 
                height: "100%", 
                justifyContent: "space-between" 
            }}>
                <div>
                    <h2>Expected Delivery</h2>
                    <p>12:34</p>
                </div>

                <div>
                    <h2>Leave a Review</h2>
                    <p>12:34</p>
                </div>
            </div>


            {/* Right panel */}
            <div style={{ flexGrow: 1, height: '600px', backgroundColor: '#e0e0e0', borderRadius: '8px', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                Map Placeholder
            </div>
        </div>
      </main>
    </div>
  );
}
