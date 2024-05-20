async function obtenerUsuario() {
    try {
        const response = await fetch('/api/users/me/perfil');
        if (response.ok) {
            const usuario = await response.json();
            return usuario.id;
        } else {
            console.error('Error al obtener información del usuario:', response.statusText);
            return null;
        }
    } catch (error) {
        console.error('Error al obtener información del usuario:', error.message);
        return null;
    }
}

async function enroll(button, clase, hora, dia) {
    try {
        const usuario = await obtenerUsuario();
        if (!usuario) {
            console.error('Usuario no autenticado.');
            mostrarMensaje('Usuario no autenticado. Por favor, inicia sesión para continuar.', 'error');
            return;
        }

        const esInscripcion = button.getAttribute('data-enrolled') === 'false';
        const operacion = {
            usuario: usuario,
            clase: clase,
            hora: hora,
            dia: dia,
            apuntado: esInscripcion
        };

        const response = await fetch('/api/users/me/clase/hora', {
            method: 'POST',
            body: JSON.stringify(operacion),
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            button.setAttribute('data-enrolled', esInscripcion ? 'true' : 'false');
            button.textContent = esInscripcion ? 'Apuntado' : 'Apuntarse';
            button.style.backgroundColor = esInscripcion ? 'green' : 'red';
            mostrarMensaje(`Te has ${esInscripcion ? 'apuntado' : 'desapuntado'} correctamente a la clase de ${clase} el ${hora} - ${dia}`, 'success');
        } else {
            mostrarMensaje('Ha ocurrido un error. Por favor, inténtalo de nuevo más tarde.', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarMensaje('Ha ocurrido un error. Por favor, inténtalo de nuevo más tarde.', 'error');
    }
}

function mostrarMensaje(texto, tipo) {
    alert(texto);
}

function toggleList(listId) {
    const list = document.getElementById(listId);
    if (list) {
        list.style.display = list.style.display === 'none' ? 'block' : 'none';
    } else {
        console.error("No se encontró el elemento con el ID: " + listId);
    }
}



