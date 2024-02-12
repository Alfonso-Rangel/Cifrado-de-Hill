function obtenerLlave() {
    return "";
}

function encriptar() {
    if (document.getElementById('texto').value === "") {
        shake("Ningún mensaje fue encontrado", "Ingresa el texto que desees encriptar o desencriptar");
    } else {
        let llave = obtenerLlave();
        if (llave === "") {
            shake("Llave inválida", "Llena todos los campos y asegúrate de que tenga matriz inversa");
            return;
        }

        let input = document.getElementById('texto').value;
        input = input.toLowerCase();

        fetch('/cifrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ texto: input, llave: llave })
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
    if(document.getElementById('texto').value === "") {
        shake("Ningún mensaje fue encontrado", "Ingresa el texto que desees encriptar o desencriptar");
    }
    else {
        let llave = obtenerLlave();
        if (llave === "") {
            shake("Llave inválida", "Llena todos los campos y asegúrate de que tenga matriz inversa");
            return;
        }

        let input = document.getElementById('texto').value;
        fetch('/descifrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ texto: input, llave: llave})
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

function shake(mensaje1, mensaje2) {
    const divAnimado = document.getElementById("mensaje-copiar");
    divAnimado.classList.add("shake-horizontal");
    divAnimado.innerHTML = `<h1>${mensaje1}</h1><p>${mensaje2}</p>`;
    setTimeout(function () {
        divAnimado.classList.remove("shake-horizontal");
    }, 800);
}
