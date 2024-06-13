import { useEffect, useState } from "react";
import { deletePicture, getAllPictures } from "../api";
import {
  Button,
  Center,
  Divider,
  Flex,
  Grid,
  GridItem,
  Heading,
  Text,
} from "@yamada-ui/react";

export const Gallery = () => {
  const [pictures, setPictures] = useState([]);

  useEffect(() => {
    (async () => {
      const allPictures = await getAllPictures();
      setPictures(allPictures);
    })();
  }, []);

  return (
    <div>
      <Heading
        w="full"
        fontSize="6xl"
        fontWeight="bold"
        bgGradient="linear(to-l, #c82ba8, #8d42c2)"
        bgClip="text"
      >
        書いた物一覧
      </Heading>
      <Center h="full">
        <Grid templateColumns="repeat(3, 1fr)" gap="md">
          {pictures.map((picture) => (
            <GridItem w="full" h="full" key={picture.id}>
              <img
                src={`data:image/peg;base64,${picture.image}`}
                alt="書いた物"
              />
              <Text
                w="full"
                fontSize="xl"
                fontWeight="bold"
                bgGradient="linear(to-l, #7928CA, #FF0080)"
                bgClip="text"
              >
                お題：{picture.title}
              </Text>
              <Button
                colorScheme="danger"
                onClick={async () => {
                  await deletePicture(picture.id);
                  const allPictures = await getAllPictures();
                  setPictures(allPictures);
                }}
              >
                削除
              </Button>
              <p>{picture.create_time}</p>
            </GridItem>
          ))}
        </Grid>
      </Center>
    </div>
  );
};
