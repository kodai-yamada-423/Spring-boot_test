import axios from "axios";

export const randomTitle = async () => {
  const title = await axios.get("/api/titles/random");
  return title.data;
};

export const savePicture = async (picture) => {
  const response = await axios.post(`/api/pictures`, picture, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  return response.data;
};

export const getAllPictures = async () => {
  const response = await axios.get(`/api/pictures`);
  return response.data;
};

export const getPicture = async (id) => {
  const response = await axios.get(`/api/pictures/${id}`);
  return response.data;
};

export const deletePicture = async (id) => {
  const response = await axios.delete(`/api/pictures/${id}`);
  return response.data;
};
