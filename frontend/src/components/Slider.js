import React, { useState } from "react";
import { FaArrowAltCircleLeft, FaArrowAltCircleRight } from "react-icons/fa";

const Slider = ({ listings }) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const length = listings.length;

  const handleOnNextClick = () => {
    setCurrentIndex(currentIndex === length - 1 ? 0 : currentIndex + 1);
  };

  const handleOnPrevClick = () => {
    setCurrentIndex(currentIndex === 0 ? length - 1 : currentIndex - 1);
  };

  if (!Array.isArray(listings) || !listings.length) {
    return null;
  }

  return (
    <div className="slider">
      <FaArrowAltCircleLeft
        className="left-arrow"
        onClick={handleOnPrevClick}
      />
      <FaArrowAltCircleRight
        className="right-arrow"
        onClick={handleOnNextClick}
      />

      {listings.map((listing, index) => {
        return (
          <div className="wrapper-slider">
            <div
              className={index === currentIndex ? "slide active" : "slide"}
              key={index}
            >
              {index === currentIndex && (
                <img
                  src={listing.location}
                  alt="listing"
                  className="latest-listing"
                />
              )}
              <h2 className="p-4 font-bold">{listing.title}</h2>
              <p className="px-4 font-light text-gray-400">
                {listing.description}
              </p>
              <p className="p-4 ">
                <b>Price: </b>
                {listing.price}
              </p>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default Slider;
