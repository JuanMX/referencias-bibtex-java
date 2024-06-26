# Generador de referencias BibTeX en Java

![Generador de referencias BibTeX en Java](./Imagenes/Generador_referencias_BibTex_v1-5.png)

![jfilechooser](./Imagenes/jfilechooser_v1-5.png)

![joptionpane](./Imagenes/joptionpane_v1-5.png)

Genera referencias de libros y artículos. 

Se pueden agregar las referencias bibliográficas **sin** llenar los campos de texto, solamente haciendo click en los botones de *Agregar Libro* o *Agregar Articulo*.

Lo anterior es útil para tener una plantilla con las referencias bibliográficas.

## Cambiar el tema de la GUI (Look and feel)

![Generador de referencias BibTeX en Java](./Imagenes/menu_look_and_feel_v1-4.png)

A partir de la versión [v1.4](https://github.com/JuanMX/referencias-bibtex-java/releases/tag/v1.4_05-05-2024_2), en el menú de opciones se agregó un listado con los *Look and feel* (a veces conocidos como temas o aspectos) proporcionados por la versión de Java del usuario, con la intención de mejorar la experiencia de uso.

Un ejemplo es el Look and feel **Windows**:

![Look and feel Windows](./Imagenes/Generador_referencias_BibTex_windows_v1-5.png)

Otro ejemplo es el Look and feel **GTK+**:

![Look and feel GTK+](./Imagenes/Generador_referencias_BibTex_GTK+_v1-5.png)

Por el momento el cambio de tema no se puede hacer en tiempo de ejecución mostrando el siguiente mensaje:

![El tema no se puede poner en tiempo de ejecucion](./Imagenes/joptionpane_look_and_feel_tiempo_de_ejecucion_v1-5.png)

## Requerimientos

Esta hecho en Java sin ninguna librería adicional **pero** se necesita Java jdk o jre para ejecutarlo. Se pueden encontrar aqui:

https://www.oracle.com/java/technologies/downloads/#java8

https://www.java.com/es/download/manual.jsp

**Se sugiere usar jdk8 o jre8 para evitar evitar errores o problemas.**

Al usar el `.jar` (un ejecutable de Java) si tienes el jdk instalado, estas en **Linux** y no se ejecuta, prueba cambiando los **permisos** de ejecución con: click derecho -> propiedades.

![El tema no se puede poner en tiempo de ejecucion](./Imagenes/jar_ejecutable.png)

## Notas

- En todos los campos de texto se puede poner cualquier letra o número.

- Al guardar el archivo la información del area de texto se elimina, se debe verificar que la información sea correcta al igual que el nombre de archivo.

- Es un programa *offline*, no usa internet.

## Agradecimientos

![tango](https://upload.wikimedia.org/wikipedia/commons/1/12/System-software-update.svg)
![tango2](https://upload.wikimedia.org/wikipedia/commons/4/47/Go-home-2.svg)
![tango3](https://upload.wikimedia.org/wikipedia/commons/b/b1/Go-bottom.svg)
![tango4](https://upload.wikimedia.org/wikipedia/commons/8/8f/Package-x-generic.svg)
![tango5](https://upload.wikimedia.org/wikipedia/commons/2/26/X-office-address-book.svg)
![tango6](https://upload.wikimedia.org/wikipedia/commons/1/1b/X-office-document.svg)
![tango7](https://upload.wikimedia.org/wikipedia/commons/4/4f/Document-open.svg)
![tango8](https://upload.wikimedia.org/wikipedia/commons/2/2f/Document-save-as.svg)
![tango9](https://upload.wikimedia.org/wikipedia/commons/e/e7/Dialog-information_on.svg)
![tango10](https://upload.wikimedia.org/wikipedia/commons/f/f9/Edit-find-replace.svg)
![tango11](https://upload.wikimedia.org/wikipedia/commons/7/7f/Dialog-error.svg)



Al proyecto Tango por los iconos, actualmente bajo [dominio público](https://en.wikipedia.org/wiki/Public_domain).

<a href="https://commons.wikimedia.org/wiki/Tango_icons" target="_blank">https://commons.wikimedia.org/wiki/Tango_icons</a>