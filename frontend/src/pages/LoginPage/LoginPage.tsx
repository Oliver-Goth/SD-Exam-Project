import { Card, Input, Label, Button, Text } from "@fluentui/react-components";

export default function LoginPage() {
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
        <Card
          style={{
            width: "40%", // scales with screen width
            minWidth: "300px", // ensures usability on smaller screens
            height: "auto",
            padding: "3%",
            display: "flex",
            flexDirection: "column",
            gap: "2%",
            boxShadow: "0 8px 24px rgba(0,0,0,0.2)",
            borderRadius: "12px",
            backgroundColor: "#ffffff",
          }}
        >
          <Text weight="semibold" size={700} style={{ textAlign: "center" }}>
            Sign In
            <br></br>
          </Text>

          <div style={{ display: "flex", flexDirection: "column", gap: "1%" }}>
            <Label>Email</Label>
            <Input placeholder="Enter your email" />
          </div>
          <br></br>

          <div style={{ display: "flex", flexDirection: "column", gap: "1%" }}>
            <Label>Password</Label>
            <Input type="password" placeholder="Enter your password" />
          </div>
          <br></br>

          <Button
            appearance="primary"
            style={{
              marginTop: "2%",
              width: "100%",
              backgroundColor: "#1a1a1a",
              color: "#ffffff",
              padding: "1% 0",
            }}
          >
            Sign In
          </Button>

          <Text
            as="a"
            href="#"
            size={200}
            style={{ marginTop: "1%", textAlign: "center" }}
          >
            Forgot password?
          </Text>
        </Card>
      </main>
    </div>
  );
}
