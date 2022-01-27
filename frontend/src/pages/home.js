import React from "react";
import Content from "../components/Content";
import Hero from "../components/Hero";

const Home = ({ isOpen, toggle }) => {
  return (
    <>
      <Hero isOpen={isOpen} toggle={toggle} />
      <Content />
    </>
  );
};

export default Home;
