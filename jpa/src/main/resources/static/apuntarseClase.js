async function obtenerUsuario() {
    try {
        const response = await fetch('/api/users/me/perfil'); // Cambiado de /api/users/me a /api/users/me/perfil
        if (response.ok) {
            const usuario = await response.json();
            return usuario.id; // Suponiendo que el ID del usuario se encuentra en el objeto de usuario devuelto por la API
        } else {
            console.error('Error al obtener información del usuario:', response.statusText);
            return null;
        }
    } catch (error) {
        console.error('Error al obtener información del usuario:', error.message);
        return null;
    }
}



async function enroll(clase, hora, dia) {
    try {
        const usuario = await obtenerUsuario();
        if (!usuario) {
            console.error('Usuario no autenticado.');
            mostrarMensaje('Usuario no autenticado. Por favor, inicia sesión para continuar.', 'error');
            return;
        }

        const apuntado = confirm(`¿Quieres apuntarte a la clase de ${clase} el ${hora} - ${dia}?`);
        const operacion = {
            usuario: usuario,
            clase: clase,
            hora: hora,
            dia: dia,
            apuntado: apuntado
        };

        const response = await fetch('/api/users/me/clase/hora', {
            method: 'POST',
            body: JSON.stringify(operacion),
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            mostrarMensaje(`Te has ${apuntado ? 'apuntado' : 'desapuntado'} correctamente a la clase de ${clase} el ${hora} - ${dia}`, 'success');
            // Aquí puedes agregar la lógica para actualizar la interfaz de usuario si es necesario (ocultar botón de apuntarse, etc.)
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
        if (list.style.display === 'none') {
            list.style.display = 'block';
        } else {
            list.style.display = 'none';
        }
    } else {
        console.error("No se encontró el elemento con el ID: " + listId);
    }
}


