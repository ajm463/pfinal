function inicializar() {
  if (location.search === '?registrado') {
    mostrarAviso('✓ ¡Registrado! Prueba a entrar', 'success');
  }
}

function entrar(datosJsonFormulario) {
  fetch('/api/users/me/session', {
    method: 'post',
    body: datosJsonFormulario,
    headers: {'content-type': 'application/json'}
  })
  .then(response => {
    if (response.ok) location.href = 'apuntarseClase.html';
    else mostrarAviso('✖︎ Credenciales incorrectas', 'error');
  });
}

function mostrarAviso(texto, tipo) {
  const aviso = document.getElementById("aviso");
  aviso.textContent = texto;
  aviso.className = tipo;
}

function form2json(event) {
  event.preventDefault();
  const data = new FormData(event.target);
  const datosFormulario = {
    email: data.get('email'),
    contrasena: data.get('contrasena')
  };
  return JSON.stringify(datosFormulario);
}
