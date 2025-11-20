import { BrowserRouter, Routes, Route } from "react-router-dom";
import { FluentProvider, webLightTheme } from "@fluentui/react-components";

import LoginPage from "./pages/LoginPage/LoginPage";
import SpecificRestaurant from "./pages/SpecificRestaurant/SpecificRestaurant";
import MainPage from "./pages/MainPage/MainPage";

export default function App() {
  return (
    <FluentProvider theme={webLightTheme}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={< MainPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/specificrestaurant" element={<SpecificRestaurant />} />
        </Routes>
      </BrowserRouter>
    </FluentProvider>
  );
}
