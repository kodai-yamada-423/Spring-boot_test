import { useState } from "react";
import "./App.css";
import { Title } from "./components/Title";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import { Gallery } from "./components/Gallery";
import { Button, Icon, UIProvider, extendTheme } from "@yamada-ui/react";
import { MdStarRate } from "react-icons/md";

function App() {
  const [link, setLink] = useState("/gallery");
  const [name, setName] = useState("ギャラリー");

  return (
    <>
      <BrowserRouter>
        <Link
          to={link}
          onClick={() => {
            if (name === "ギャラリー") {
              setLink("/");
              setName("お絵描き");
            } else {
              setLink("/gallery");
              setName("ギャラリー");
            }
          }}
        >
          <Button
            colorScheme="lime"
            variant="ghost"
            leftIcon={<Icon as={MdStarRate} />}
          >
            {name}
          </Button>
        </Link>

        <Routes>
          <Route path="/" element={<Title></Title>} />
          <Route path="/gallery" element={<Gallery></Gallery>} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
