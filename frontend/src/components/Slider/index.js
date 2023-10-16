import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";

const SliderComponent = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 1000, // Adjust animation speed
    autoplay: true, // Enable automatic sliding
    autoplaySpeed: 3000, // Set time between slides (in milliseconds)
    slidesToShow: 1, // Number of slides to show at once
    slidesToScroll: 1, // Number of slides to scroll at once
  };

  const images = [
    "/image/slider/slider.jpg",
    "/image/slider/slider2.jpg",
    "/image/slider/slider3.jpg",
    "/image/slider/slider4.jpg",
  ];

  return (
    <div id="carouselExampleControlsNoTouching" class="carousel slide" data-bs-touch="false" data-bs-interval="false">
      <div className="row">
        <div className="col-md-6">
          <Slider {...settings}>
            {images.map((image, index) => (
              <div key={index}>
                <img src={image} alt={`Slide ${index}`} />
              </div>
            ))}
          </Slider>
        </div>
      </div>
    </div>
  );
};

export default SliderComponent;
