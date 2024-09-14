import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import "./index.css"; // Import custom CSS

const SliderComponent = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 1000,
    autoplay: true,
    autoplaySpeed: 3000,
    slidesToShow: 1,
    slidesToScroll: 1,
  };

  const images = [
    "/image/slider/10.webp",
    "/image/slider/11.webp",
  ];

  return (
    <div className="slider-container">
      <Slider {...settings}>
        {images.map((image, index) => (
          <div key={index} className="slider-item">
            <img src={image} alt={`Slide ${index}`} className="slider-image" />
          </div>
        ))}
      </Slider>
    </div>
  );
};

export default SliderComponent;
