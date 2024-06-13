import React from "react";
import { Link } from "react-router-dom";

export const Header = () => {
  <header>
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <link to="/gallery">画像集</link>
        </li>
      </ul>
    </nav>
  </header>;
};
