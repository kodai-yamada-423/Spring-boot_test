import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { UIProvider, extendTheme } from "@yamada-ui/react";

const globalStyle = {
  body: {
    bg: "cyan.100",
    color: "black",
  },
};
const customTheme = extendTheme({ styles: { globalStyle } })();

ReactDOM.createRoot(document.getElementById("root")).render(
  <UIProvider theme={customTheme}>
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </UIProvider>
);
