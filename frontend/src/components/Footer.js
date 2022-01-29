import React from "react";
import { FaEnvelope, FaGithub, FaInstagram, FaLinkedin } from "react-icons/fa";
import { IconContext } from "react-icons/lib";

export const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="contact-information">
          <h3 className="text-3xl">REAL-ESTATETOR</h3>
          <br></br>
          <p>Landsraderweg 138</p>
          <p>Heijenrath, 6276 PH Limburg - Netherlands</p>
          <br></br>
          <p>
            <b>Tel: </b>06-84679242
          </p>
          <br></br>
          <IconContext.Provider value={{ size: "2rem" }}>
            <div className="social">
              <a
                href="https://www.linkedin.com/in/jordi-jaspers/"
                onMouseOver={({ target }) => (target.style.color = "#eab208")}
                onMouseOut={({ target }) => (target.style.color = "#ffffff")}
              >
                <FaLinkedin />
              </a>
              <a
                href="https://www.instagram.com/jordi_jaspers/"
                onMouseOver={({ target }) => (target.style.color = "#eab208")}
                onMouseOut={({ target }) => (target.style.color = "#ffffff")}
              >
                <FaInstagram />
              </a>
              <a
                href="https://github.com/Jordi-Jaspers"
                onMouseOver={({ target }) => (target.style.color = "#eab208")}
                onMouseOut={({ target }) => (target.style.color = "#ffffff")}
              >
                <FaGithub />
              </a>
              <a
                href="https://github.com/Jordi-Jaspers"
                onMouseOver={({ target }) => (target.style.color = "#eab208")}
                onMouseOut={({ target }) => (target.style.color = "#ffffff")}
              >
                <FaEnvelope />
              </a>
            </div>
          </IconContext.Provider>
        </div>
        <div className="contact-form">
          <h3 className="text-2xl">Subscribe to newsletter</h3>
          <br></br>
          <form className="flex flex-col" action="">
            <input
              type="text"
              placeholder="email"
              className="bg-transparent border-b-2"
            />
            <br></br>
            <span>
              <input type="checkbox"></input>
              <span className="p-2">
                I have read the Privacy policy and I give my consent to the
                processing of my personal data.
              </span>
            </span>
            <br></br>
            <button
              type="submit"
              className="border rounded-md px-2 w-fit hover:text-yellow-500 hover:border-yellow-500 self-center"
            >
              Subscribe
            </button>
          </form>
          <br></br>
          <span className="privacy-policy">
            <h4>PRIVACY POLICY</h4>
            <a
              href="https://letmegooglethat.com/?q=Privacy+policy"
              className="text-xs"
            >
              Click here to read our privacy policy
            </a>
          </span>
        </div>
      </div>
      <div className="copyright">
        Copyright © 2022 by REAL-ESTATETOR Inc. All rights reserved.
      </div>
    </footer>
  );
};
