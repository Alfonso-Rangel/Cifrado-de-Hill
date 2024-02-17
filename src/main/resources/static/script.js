function generarClave() {
    fetch('/generaClave', {
        method: 'POST'
    })
        .then(response => response.text())
        .then(clave => {
            document.getElementById('clave').value = clave;
            // Llamar a la función que verifica las validaciones después de asignar la clave generada
            validarContrasena();
        })
        .catch(error => console.error('Error al generar la clave:', error));
}

// Función para verificar las validaciones de la contraseña
function validarContrasena() {
    const password = document.getElementById('clave');
    patternTest(pattern.integer(), validations.integer);
    patternTest(pattern.positive(), validations.positive);
    patternTest(pattern.atLeastFour(), validations.atLeastFour);

    // Comprueba que se cumplan todos los requisitos
    if (hasClass(validations.integer, 'valid') &&
        hasClass(validations.positive, 'valid') &&
        hasClass(validations.atLeastFour, 'valid')
    ) {
        addClass(password.parentElement, 'valid');
    } else {
        removeClass(password.parentElement, 'valid');
    }
}


function encriptar() {
    const password = document.getElementById('clave');
    if (document.getElementById('texto').value === "") {
        shake("Ningún mensaje fue encontrado", "Ingresa el texto que desees encriptar o desencriptar");
    } else if (!password.parentElement.classList.contains('valid')) {
        mostrarElementos();
        shake("Llave inválida", "La contraseña no cumple con los requisitos");
    } else {
        let llave = password.value;
        let input = document.getElementById('texto').value;
        fetch('/cifrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ texto: input, clave: llave })
        })
            .then(response => response.text())
            .then(textoEncriptado => {
                let textarea = document.getElementById('output-copiar');
                textarea.value = textoEncriptado;
                ocultarElementos();
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}

function desencriptar() {
    const password = document.getElementById('clave');
    if(document.getElementById('texto').value === "") {
        shake("Ningún mensaje fue encontrado", "Ingresa el texto que desees encriptar o desencriptar");
    } else if (!password.parentElement.classList.contains('valid')) {
        mostrarElementos();
        shake("Llave inválida", "La contraseña no cumple con los requisitos");
    } else {
        let llave = password.value;
        let input = document.getElementById('texto').value;
        fetch('/descifrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ texto: input, clave: llave})
        })
            .then(response => response.text())
            .then(textoDesencriptado => {
                let textarea = document.getElementById('output-copiar');
                textarea.value = textoDesencriptado;
                ocultarElementos();
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}

function ocultarElementos() {
    document.getElementById("imagen-copiar").style.display = "none";
    document.getElementById("mensaje-copiar").style.display = "none";
    document.getElementById("output-copiar").style.display = "inline";
    document.getElementById("boton-copiar").style.display = "inline";
}

function mostrarElementos() {
    document.getElementById("imagen-copiar").style.display = "inline";
    document.getElementById("mensaje-copiar").style.display = "inline";
    document.getElementById("output-copiar").style.display = "none";
    document.getElementById("boton-copiar").style.display = "none";
}

function copiarTexto() {
    const copiar = document.getElementById("output-copiar").value;
    const botonCopiar = document.getElementById("boton-copiar");
    const simbolo = document.getElementById("simbolo-copiar");
    navigator.clipboard.writeText(copiar) .then(
        () => {
            botonCopiar.style.backgroundColor = '#50C878';
            botonCopiar.style.color = '#0A3871';
            simbolo.textContent = "done";
            setTimeout(function () {
                botonCopiar.style.backgroundColor = '#D8DFE8';
                botonCopiar.style.color = '#0A3871';
                simbolo.textContent = "content_copy";
            }, 400);
        },
        () => {
            botonCopiar.style.backgroundColor = '#CC2630';
            botonCopiar.style.color = '#0A3871';
            simbolo.textContent = "close";
            setTimeout(function () {
                botonCopiar.style.backgroundColor = '#D8DFE8';
                botonCopiar.style.color = '#0A3871';
                simbolo.textContent = "content_copy";
            }, 400);
        }
    );
}

function copiarTextoClave() {
    const copiar = document.getElementById("clave").value;
    const botonCopiar = document.getElementById("boton-copiar-clave");
    const simbolo = document.getElementById("simbolo-copiar-clave");
    navigator.clipboard.writeText(copiar) .then(
        () => {
            botonCopiar.style.backgroundColor = '#50C878';
            botonCopiar.style.color = '#0A3871';
            simbolo.textContent = "done";
            setTimeout(function () {
                botonCopiar.style.backgroundColor = '#D8DFE8';
                botonCopiar.style.color = '#0A3871';
                simbolo.textContent = "content_copy";
            }, 400);
        },
        () => {
            botonCopiar.style.backgroundColor = '#CC2630';
            botonCopiar.style.color = '#0A3871';
            simbolo.textContent = "close";
            setTimeout(function () {
                botonCopiar.style.backgroundColor = '#D8DFE8';
                botonCopiar.style.color = '#0A3871';
                simbolo.textContent = "content_copy";
            }, 400);
        }
    );
}
function shake(mensaje1, mensaje2) {
    const divAnimado = document.getElementById("mensaje-copiar");
    divAnimado.classList.add("shake-horizontal");
    divAnimado.innerHTML = `<h1>${mensaje1}</h1><p>${mensaje2}</p>`;
    setTimeout(function () {
        divAnimado.classList.remove("shake-horizontal");
    }, 800);
}

const password = document.querySelector('.password');
const validations = {
    integer: document.querySelector('.password-validations .integer'),
    positive: document.querySelector('.password-validations .positive'),
    atLeastFour: document.querySelector('.password-validations .at-least-four')
};

const pattern = {
    integer: function () {
        const regex = /^(\d+ ?)*$/; // Permite números enteros y espacios, incluso sin números
        return regex.test(password.value);
    },
    positive: function () {
        const regex = /^(?:0|\d+ ?)+$/; // Permite números positivos, incluyendo 0, separados por espacios
        return regex.test(password.value);
    },
    atLeastFour: function () {
        const regex = /\b\d+\b/g; // Encuentra cada conjunto de dígitos como un número independiente
        const matches = password.value.match(regex);
        return matches && matches.length >= 4;
    }
};

// Escucha la acción keyup en el campo de contraseña
password.addEventListener('keyup', function () {
    patternTest(pattern.integer(), validations.integer);
    patternTest(pattern.positive(), validations.positive);
    patternTest(pattern.atLeastFour(), validations.atLeastFour);

    // Comprueba que se cumplan todos los requisitos
    if (hasClass(validations.integer, 'valid') &&
        hasClass(validations.positive, 'valid') &&
        hasClass(validations.atLeastFour, 'valid')
    ) {
        addClass(password.parentElement, 'valid');
    } else {
        removeClass(password.parentElement, 'valid');
    }
});

// Función para probar los patrones
function patternTest(pattern, response) {
    if (pattern) {
        addClass(response, 'valid');
    } else {
        removeClass(response, 'valid');
    }
}

// Función para verificar si una clase está presente
function hasClass(el, className) {
    if (el.classList) {
        return el.classList.contains(className);
    } else {
        new RegExp('(^| )' + className + '( |$)', 'gi').test(el.className);
    }
}

// Función para agregar una clase
function addClass(el, className) {
    if (el.classList) {
        el.classList.add(className);
    } else {
        el.className += ' ' + className;
    }
}

// Función para eliminar una clase
function removeClass(el, className) {
    if (el.classList)
        el.classList.remove(className);
    else
        el.className = el.className.replace(new RegExp('(^|\\b)' + className.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
}
