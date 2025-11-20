import { Button, Text, Card } from "@fluentui/react-components";
import MenuLines from "./components/MenuLines";

export default function SpecificRestaurant() {
  return (
    <div
      style={{
        height: "100vh",
        width: "100vw",
        display: "flex",
        flexDirection: "column",
        backgroundColor: "#f5f5f5",
      }}
    >
      {/* Header */}
      <header
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          padding: "1rem 2%",
          backgroundColor: "#617073",
          color: "#ffffff",
        }}
      >
        <Text weight="bold" size={700}>
          MTOGO
        </Text>
        <div style={{ display: "flex", alignItems: "center", gap: "1.5rem" }}>
          <Text size={200}>📍 Your Location</Text>
          <Button
            appearance="primary"
            style={{ backgroundColor: "#ffffff", color: "#000000" }}
          >
            Button
          </Button>
        </div>
      </header>

      {/* Main Content */}
      <main
        style={{
          flexGrow: 1,
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          padding: "5%",
        }}
      >
        <Card
          style={{
            width: "80%",
            minWidth: "400px",
            maxWidth: "1200px",
            maxHeight: "70vh",
            padding: "2rem",
            backgroundColor: "#ffffff",
            borderRadius: "12px",
            boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
            display: "flex",
            flexDirection: "column",
            gap: "1rem",
            margin: "0 auto",
            overflowY: "auto", // Scroller
          }}
        >
          <Text weight="semibold" size={600} style={{ textAlign: "left" }}>
            Menu
          </Text>
          <br></br>

          <MenuLines />
        </Card>
      </main>
    </div>
  );
}
