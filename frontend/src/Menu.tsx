import { useState } from 'react';
import {
  PrimaryButton,
  Checkbox,
  Slider,
  Pivot,
  PivotItem,
  Stack,
  Panel,
  PanelType,
  ChoiceGroup,
  initializeIcons,
} from '@fluentui/react';

initializeIcons();

const menuItems = [
  { id: 1, name: 'Pizza Hut', img: 'https://via.placeholder.com/40' },
  { id: 2, name: 'McDonalds', img: 'https://via.placeholder.com/40' },
  { id: 3, name: 'Lucky Sushi', img: 'https://via.placeholder.com/40' },
];

const dietaryOptions = [
  { id: 'vegan', label: 'Vegan' },
  { id: 'glutenfree', label: 'Gluten-Free' },
  { id: 'halal', label: 'Halal' },
];

export default function MainMenu() {
  const [isPanelOpen, setIsPanelOpen] = useState(false);

  const [price, setPrice] = useState(75);
  const [priceOption, setPriceOption] = useState<'all' | 'below' | 'above'>('all');

  const [rating, setRating] = useState<number>(0);

  const [dietary, setDietary] = useState<Record<string, boolean>>({
    vegan: false,
    glutenfree: false,
    halal: false,
  });

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

      <main style={{ padding: 32 }}>
        <div style={{ display: 'flex', gap: 32 }}>
          <aside
            style={{
              width: 300,
              padding: 16,
              borderRadius: 8,
              backgroundColor: '#f5f5f5',
              boxShadow: 'inset 0 0 5px rgba(0,0,0,0.05)',
            }}
          >
            <Stack tokens={{ childrenGap: 20 }}>
              <div>
                <h3>Open Now</h3>
                <Checkbox label="Only show open restaurants" />
              </div>

              <div>
                <h3>Price</h3>
                <Slider
                  label={`Max Price: DKK. ${price}`}
                  min={0}
                  max={150}
                  step={1}
                  value={price}
                  onChange={setPrice}
                />
                <ChoiceGroup
                  selectedKey={priceOption}
                  options={[
                    { key: 'all', text: 'Show All' },
                    { key: 'below', text: 'Below' },
                    { key: 'above', text: 'Above' },
                  ]}
                  onChange={(_, option) => setPriceOption(option!.key as 'all' | 'below' | 'above')}
                  styles={{ root: { marginTop: 10 } }}
                />
              </div>

              <div>
                <h3>Rating</h3>
                <ChoiceGroup
                  selectedKey={rating.toString()}
                  options={[
                    { key: '5', text: '★★★★★' },
                    { key: '4', text: '★★★★' },
                    { key: '3', text: '★★★' },
                    { key: '2', text: '★★' },
                    { key: '1', text: '★' },
                  ]}
                  onChange={(_, option) => setRating(parseInt(option!.key))}
                  styles={{ root: { marginTop: 10 } }}
                />
              </div>

              <div>
                <h3>Dietary</h3>
                <Stack tokens={{ childrenGap: 8 }}>
                  {dietaryOptions.map((d) => (
                    <Checkbox
                      key={d.id}
                      label={d.label}
                      checked={dietary[d.id]}
                      onChange={(_, checked) =>
                        setDietary({ ...dietary, [d.id]: checked ?? false })
                      }
                    />
                  ))}
                </Stack>
              </div>
            </Stack>
          </aside>

          <section style={{ flex: 1 }}>
            <div style={{ display: 'flex', justifyContent: 'center', marginBottom: 32 }}>
              <Pivot>
                <PivotItem headerText="All" />
                <PivotItem headerText="Pizza" />
                <PivotItem headerText="Salads" />
                <PivotItem headerText="Pasta" />
              </Pivot>
            </div>

            <Stack tokens={{ childrenGap: 16 }}>
              {menuItems.map((item) => (
                <div
                  key={item.id}
                  style={{
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    padding: 16,
                    backgroundColor: '#f0f0f0',
                    borderRadius: 8,
                    boxShadow: '0 2px 4px rgba(0,0,0,0.05)',
                  }}
                >
                  <div style={{ display: 'flex', alignItems: 'center', gap: 16 }}>
                    <img
                      src={item.img}
                      alt={item.name}
                      style={{ width: 40, height: 40, borderRadius: '50%' }}
                    />
                    <span style={{ fontSize: '1rem', fontWeight: 500 }}>{item.name}</span>
                  </div>
                  <Checkbox />
                </div>
              ))}
            </Stack>
          </section>
        </div>
      </main>
    </div>
  );
}
