import { Card, Text, Button } from "@fluentui/react-components";
import FavoriteStar from "./Favourite";
import { Add16Regular } from "@fluentui/react-icons";

export default function MenuLines() {
  const starters = [
    {
      title: "Nr. 1 – Margherita",
      description: "Classic pizza with tomato, mozzarella, and fresh basil",
    },
    {
      title: "Nr. 2 – Caesar Salad",
      description: "Romaine lettuce, parmesan, croutons, and Caesar dressing",
    },
    {
      title: "Nr. 3 – Garlic Bread",
      description: "Toasted bread with garlic butter and herbs",
    },
    {
      title: "Nr. 4 – Tomato Soup",
      description: "Creamy tomato soup served with a side of bread",
    },
  ];

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "1rem" }}>
      {starters.map((item, index) => (
        <Card
          key={index}
          style={{
            width: "100%",
            padding: "1.5rem",
            backgroundColor: "#ffffff",
            borderRadius: "10px",
            boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
            display: "flex",
            flexDirection: "column",
            gap: "0.5rem",
            position: "relative", // allows top-right positioning
          }}
        >
          {/* Top-right actions */}
          <div
            style={{
              position: "absolute",
              bottom: "1rem", // move to bottom
              right: "1rem",
              display: "flex",
              gap: "0.5rem",
              alignItems: "center", // vertical alignment of icons
            }}
          >
            <FavoriteStar />
            <Button
              appearance="subtle"
              icon={<Add16Regular />}
              onClick={() => console.log(`Added ${item.title} to cart`)}
              aria-label="Add item"
              style={{ padding: 0 }}
            />
          </div>

          {/* Card content */}
          <Text weight="semibold" size={500}>
            {item.title}
          </Text>
          <Text size={200} style={{ color: "#555" }}>
            {item.description}
          </Text>
        </Card>
      ))}
    </div>
  );
}
