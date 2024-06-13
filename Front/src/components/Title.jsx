import { useEffect, useState } from "react";
import { PictureCanvas } from "./PictureCanvas";
import { randomTitle } from "../api";

export const Title = () => {
  const [title, setTitle] = useState(null);

  useEffect(() => {
    const getTitle = async () => {
      const titleName = await randomTitle();
      setTitle(titleName);
    };
    getTitle();
  }, []);

  const changeTitle = async () => {
    const titleName = await randomTitle();
    setTitle(titleName);
  };

  if (!title) return <p>ちょっと待って…</p>;

  return (
    <div>
      <PictureCanvas title={title} changeTitle={changeTitle}></PictureCanvas>
    </div>
  );
};
