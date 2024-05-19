document.addEventListener('DOMContentLoaded', function() {
  let slides = document.getElementsByClassName("review");
  let prevButton = document.querySelector('.prev');
  let nextButton = document.querySelector('.next');

  let slideIndex = 0;
  showSlides(slideIndex); // Muestra el primer slide

  prevButton.addEventListener('click', function() { moveSlide(-1); });
  nextButton.addEventListener('click', function() { moveSlide(1); });

  function moveSlide(n) {
    slideIndex += n;
    if (slideIndex >= slides.length) {
      slideIndex = 0;
    } else if (slideIndex < 0) {
      slideIndex = slides.length - 1;
    }
    showSlides(slideIndex);
  }

  function showSlides(n) {
    for (let i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    slides[n].style.display = "block";
  }
});