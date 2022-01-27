import React from "react";
import listings from "../data/latest-listings-data";
import Slider from "./Slider";

const Content = () => {
  return (
    <div className="flex flex-col justify-center items-center">
      <h1 className="text-4xl font-bold p-10">Latest Listings</h1>
      <Slider listings={listings} />
    </div>
  );
};

export default Content;
