import { Button, Text } from "@fluentui/react-components";

export default function MainPage() {
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
          padding: "10%",
          paddingTop: "10%",
        }}
      >
        MAIN PAGE CONTENT
      </main>
    </div>
  );
}
