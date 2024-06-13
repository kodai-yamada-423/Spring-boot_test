import React, { useRef, useState } from "react";
import { savePicture } from "../api";
import {
  Button,
  Center,
  Flex,
  Heading,
  Icon,
  Spacer,
  Switch,
} from "@yamada-ui/react";
import { FcPlus } from "react-icons/fc";

export const PictureCanvas = (propse) => {
  const { title, changeTitle } = propse;
  const canvasRef = useRef(null);
  const [isPicture, setIsPicture] = useState(false);
  const [isEraser, setIsEraser] = useState(false);

  const startPicture = ({ nativeEvent }) => {
    const { offsetX, offsetY } = nativeEvent;
    const context = canvasRef.current.getContext("2d");
    context.beginPath();
    context.moveTo(offsetX, offsetY);
    setIsPicture(true);
  };

  const picture = ({ nativeEvent }) => {
    if (!isPicture) return;
    const { offsetX, offsetY } = nativeEvent;
    const context = canvasRef.current.getContext("2d");
    context.lineTo(offsetX, offsetY);
    context.strokeStyle = isEraser ? "white" : "black";
    context.lineWidth = isEraser ? 15 : 2;
    context.stroke();
  };

  const stopPicture = () => {
    const context = canvasRef.current.getContext("2d");
    context.closePath();
    setIsPicture(false);
  };

  const clickSave = async () => {
    const canvas = canvasRef.current;

    const offscreenCanvas = document.createElement("canvas");
    offscreenCanvas.width = canvas.width;
    offscreenCanvas.height = canvas.height;
    const offscreenContext = offscreenCanvas.getContext("2d");

    offscreenContext.fillStyle = "white";
    offscreenContext.fillRect(
      0,
      0,
      offscreenCanvas.width,
      offscreenCanvas.height
    );
    offscreenContext.drawImage(canvas, 0, 0);

    offscreenCanvas.toBlob(async (blob) => {
      const formData = new FormData();
      formData.append("image", blob);
      formData.append("title", title.topic);
      formData.append("user_id", 1);

      try {
        console.log(formData);
        await savePicture(formData);
        alert("保存したーよ");
      } catch (error) {
        console.error(error);
        alert("保存に失敗しました");
      }
    });
  };

  const resetCanvas = () => {
    changeTitle();
    const canvas = canvasRef.current;
    const context = canvasRef.current.getContext("2d");
    context.clearRect(0, 0, canvas.width, canvas.height);
  };

  const toggleEraser = () => {
    setIsEraser(!isEraser);
  };

  return (
    <div>
      <div>
        <Heading
          w="full"
          size="2xl"
          bgGradient="linear(to-l, #7928CA, #0d00ff)"
          bgClip="text"
          isTruncated
        >
          お題: {title.topic}
        </Heading>
        <Button
          variant={"outline"}
          colorScheme={"indigo"}
          onClick={resetCanvas}
        >
          お題を変える
        </Button>
      </div>
      <div>
        <canvas
          ref={canvasRef}
          width="500"
          height="500"
          onMouseDown={startPicture}
          onMouseMove={picture}
          onMouseUp={stopPicture}
          onMouseLeave={stopPicture}
          style={{ border: "1px solid #000", backgroundColor: "white" }}
        ></canvas>
      </div>
      <Center>
        <Flex gap="md">
          <Switch colorScheme="success" onChange={toggleEraser}>
            消しゴムモード
          </Switch>
          <Spacer />
          <Button
            onClick={clickSave}
            colorScheme="emerald"
            variant="solid"
            leftIcon={<Icon as={FcPlus} />}
          >
            画像保存
          </Button>
        </Flex>
      </Center>
    </div>
  );
};
