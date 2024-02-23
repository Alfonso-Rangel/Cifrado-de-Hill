# Cifrado de Hill

Aplicación web con Java y Spring Web para cifrar y descifrar mensajes utilizando el cifrado de Hill, un método que emplea conceptos del álgebra lineal.

La motivación principal de este proyecto fue profundizar en conceptos del álgebra lineal como multiplicación de matrices, cálculo de la inversa modular de una matriz, cálculo del determinante con el método de Laplace, cálculo de matrices de cofactores, matriz transpuesta y matriz adjunta.
Este proyecto emplea una arquitectura MVC para desacoplar la interfaz del funcionamiendo ya que en un futuro me guastaría mejorar el frontend de mi aplicación.

## Funcionamiento
La interfaz principal se compone de 3 elementos:
* Un campo donde irá el texto a procesar.
* Un campo donde irá la clave con la que de cifrará y descifrará.
* Un campo que muestra el texto ya cifrado o descifrado.
  
![interfaz](https://github.com/Alfonso-Rangel/Cifrado-de-Hill/assets/98926087/880ddade-c1be-49f9-816c-3e9b35b4964a)

---
Una vez se ingresa el texto y la clave, se muestra el texto cifrado.
La clave debe ser una matriz con inversa modular, pero si el usuario no sabe qué es eso, el programa tiene la opción de generar aleatoriamente una clave segura.

![encriptar](https://github.com/Alfonso-Rangel/Cifrado-de-Hill/assets/98926087/15f4406e-aece-41db-8cf5-622cf0b06972)

---
El cifrado reconoce 94 caracters ASCII que van desde el 32 hasta el 126, pero el código es muy general, lo que permite expandir esa cantidad en el futuro.
El objetivo del cifrado es que si no se tiene la clave con la que se cifró, al intentar descifrar no tendrá sentido el texto.

![clave erronea](https://github.com/Alfonso-Rangel/Cifrado-de-Hill/assets/98926087/9b5a2fa1-84b8-42f0-a43b-22bca22f4b04)

---
Pero si se toma el texto cifrado y se descifra usando la clave con la que se cifró, se mostrará el texto original.

![desencriptar](https://github.com/Alfonso-Rangel/Cifrado-de-Hill/assets/98926087/552d1533-5b4f-4991-b474-74d39f457a93)

## License

[MIT](https://choosealicense.com/licenses/mit/)
