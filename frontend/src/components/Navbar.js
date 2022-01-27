import React from "react";
import { Link } from "react-router-dom";
import { Dropdown } from "./Dropdown";

const Navbar = ({ toggle, isOpen }) => {
  return (
    <>
      <header
        className="flex justify-between items-center h-16 sticky top-0 z-10 backdrop-filter backdrop-blur-lg text-black shadow-sm font-mono z-2"
        role="navigation"
      >
        <Link to="/" className="pl-8 text-white font-bold">
          REAL-ESTATETOR
        </Link>
        <div className="px-4 cursor-pointer md:hidden" onClick={toggle}>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth={2}
              d="M4 6h16M4 12h16M4 18h16"
            />
          </svg>
        </div>
        <nav className="pr-8 text-white font-bold md:block hidden">
          <Link className="p-4" to="/">
            Home
          </Link>
          <Link className="p-4" to="/about">
            About
          </Link>
          <Link className="p-4" to="/marketplace">
            Marketplace
          </Link>
          <Link className="p-4" to="/contact">
            Contact
          </Link>
          <button className="bg-yellow-500 hover:bg-yellow-700 py-2 px-4 rounded-full">
            Connect Wallet
          </button>
        </nav>
      </header>
      <Dropdown isOpen={isOpen} toggle={toggle} />
    </>
  );
};

export default Navbar;
