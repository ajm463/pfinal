/*function datosPerfil() {
  return fetch('/api/users/me').then(res => res.json());
}

function articuloInicio() {
  return datosPerfil().then(perfil => {
    document.getElementById('nombre-inicio').textContent = perfil.name;
    document.getElementById('tel-inicio').textContent = perfil.role;
    document.getElementById('email-inicio').textContent = perfil.email;
  });
}

function salir() {
  fetch('/api/users/me/session', {method: 'delete'})
    .then(() => location.href = 'login.html');
}

function baja() {
  if (confirm("Esto borrará tu usuario, ¿estás seguro?")) {
    fetch('/api/users/me', {method: 'delete'})
      .then(() => location.href = 'login.html');
  }
}

addEventListener('hashchange', inicializar);

function inicializar() {
  Array.from(document.querySelectorAll('article')).forEach(a => a.hidden = true);
  Array.from(document.querySelectorAll('nav a')).forEach(a => a.classList.remove('active'));
  const articulo = location.hash || "#inicio";
  cargarArticulo(articulo).then(() => mostrarArticulo(articulo));
}

function cargarArticulo(articulo) {
  switch(articulo) {
    case '#inicio': return articuloInicio();
    default: return articuloInicio();
  }
}

function mostrarArticulo(articulo) {
  document.querySelector(articulo).hidden = false;
  document.querySelector(`a[href="${articulo}"]`).classList.add('active');
}

function form2json(event) {
  event.preventDefault();
  const data = new FormData(event.target);
  return JSON.stringify(Object.fromEntries(data.entries()));
}*/
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
