function enroll(clase, hora, dia) {
    const usuario = obtenerUsuario();
    if (!usuario) {
        // Manejar el caso en que el usuario no esté autenticado
        console.error('Usuario no autenticado.');
        return;
    }

    const apuntado = confirm(`¿Quieres apuntarte a la clase de ${clase} el ${hora} - ${dia}?`);
    const operacion = {
        usuario: usuario,
        clase: clase,
        apuntado: apuntado
    };

    fetch('/api/users/me/clase/hora', {
        method: 'POST',
        body: JSON.stringify(operacion),
        headers: { 'Content-Type': 'application/json' }
    })
        .then(response => {
            if (response.ok) {
                mostrarMensaje(`Te has ${apuntado ? 'apuntado' : 'desapuntado'} correctamente a la clase de ${clase} el ${hora} - ${dia}`, 'success');
                // Aquí puedes agregar la lógica para actualizar la interfaz de usuario si es necesario (ocultar botón de apuntarse, etc.)
            } else {
                mostrarMensaje('Ha ocurrido un error. Por favor, inténtalo de nuevo más tarde.', 'error');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            mostrarMensaje('Ha ocurrido un error. Por favor, inténtalo de nuevo más tarde.', 'error');
        });
}

function obtenerUsuario() {
    // Obtener el ID del usuario de la cookie de sesión
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
        if (cookie.startsWith('session=')) {
            return cookie.split('=')[1];
        }
    }
    return null; // Si no se encuentra la cookie de sesión
}

function mostrarMensaje(texto, tipo) {
    alert(texto); // Puedes reemplazar esto con un modal o una notificación en la interfaz de usuario
}


function obtenerUsuario() {
    // Obtener el ID del usuario de la cookie de sesión
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
        if (cookie.startsWith('session=')) {
            return cookie.split('=')[1];
        }
    }
    return null; // Si no se encuentra la cookie de sesión
}




function mostrarMensaje(texto, tipo) {
    alert(texto); // Puedes reemplazar esto con un modal o una notificación en la interfaz de usuario
}

