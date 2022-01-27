import React from "react";
import { Link } from "react-router-dom";
import Background from "../images/hero-background.jpg";
import Navbar from "./Navbar";

const Hero = ({ isOpen, toggle }) => {
  return (
    <>
      <div
        style={{ backgroundImage: `url(${Background})` }}
        className="justify-center items-center bg-no-repeat bg-cover bg-center h-screen"
      >
        <Navbar classname="top-0" isOpen={isOpen} toggle={toggle} />
        <div className="flex flex-col h-screen justify-center items-center">
          <h1 className="lg:text-8xl md:text-7xl text-5xl text-white mb-14">
            REAL-ESTATETOR
          </h1>
          <Link
            to="/"
            className=" md:text-3xl md:px-10 md:py-6 px-5 py-3 text-xl bg-yellow-500 rounded-full hover:bg-yellow-300 transition duration-300 ease-in-out animate-bounce flex gap-x-2 justify-between items-center"
          >
            <p className="text-">Go to Marketplace</p>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="md:h-8 md:w-8 h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"
              />
            </svg>
          </Link>
        </div>
      </div>
    </>
  );
};

export default Hero;
