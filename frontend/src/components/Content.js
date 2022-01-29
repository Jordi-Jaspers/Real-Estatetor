import React from "react";
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";
import Slider from "react-slick";
import listings from "../data/latest-listings-data";

const Content = () => {
  const [imageIndex, setImageIndex] = React.useState(0);

  const NextArrow = ({ onClick }) => {
    return <FaArrowRight className="right-arrow" onClick={onClick} />;
  };

  const PrevArrow = ({ onClick }) => {
    return <FaArrowLeft className="left-arrow" onClick={onClick} />;
  };

  const settings = {
    infinite: true,
    dots: true,
    autoplay: true,
    lazyLoad: true,
    speed: 500,
    slidesToShow: 3,
    centerMode: true,
    centerPadding: 0,
    nextArrow: <NextArrow />,
    prevArrow: <PrevArrow />,
    beforeChange: (current, next) => setImageIndex(next),
  };

  return (
    <div className="listing-content">
      <div className="my-4">
        <h1 className="text-center text-4xl pt-5 ">Latest Listings</h1>
      </div>
      <div className="h-3/5">
        <Slider {...settings} className="top-1/4">
          {listings.map((listing, index) => (
            <div
              className={index === imageIndex ? "slide active-slide" : "slide"}
            >
              <img src={listing.image} alt={listing.title} />
              <h2 className="font-bold text-l md:text-2xl px-3 py-2 md:py-3">
                {listing.title}
              </h2>
              <p className="listing-description">{listing.description}</p>
              <p className="md:py-3 px-3 text-sm">
                <b>Price: </b>
                {listing.price}
              </p>
            </div>
          ))}
        </Slider>
      </div>
    </div>
  );
};

export default Content;
