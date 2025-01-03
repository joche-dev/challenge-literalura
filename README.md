﻿# Challenge ONE Back End - literAlura

## Descripción
Este proyecto consiste en una aplicación de catálogo de libros que ofrece una interacción textual vía consola con los usuarios. Se realizan solicitudes a la API de libros Gutendex, se manipulan los datos en formato JSON, se almacenan en una base de datos PostgreSQL y, finalmente, se filtran y muestran los libros y autores de interés.

Para más detalles sobre la API, visita su sitio web: [Gutendex](https://www.gutendex.com/)

## Funcionalidades
- **Interfaz de Usuario:** Ofrece una interfaz sencilla e intuitiva a través de la consola.
- **Buscar libros por título:** Permite buscar un libro ingresando su título. Si el libro es encontrado en la API Gutendex, se muestra en la consola y se guarda en la base de datos.
- **Listar libros registrados:** Muestra todos los libros que han sido encontrados en la API y almacenados en la base de datos.
- **Listar autores registrados:** Presenta un listado de todos los autores cuyos libros están registrados en la base de datos.
- **Listar autores vivos en un determinado año:** Filtra y muestra los autores registrados que estaban vivos en el año ingresado.
- **Listar libros por idioma:** Filtra y muestra los libros registrados según el idioma especificado.
- **Top 10 libros más descargados:** Muestra los 10 libros más descargados en orden descendente.

## Visualización de Funcionalidades
A continuación, se muestran ejemplos visuales de cada funcionalidad:

#### 1. Interfaz de Usuario
<img src="https://github.com/user-attachments/assets/4f32b69d-21dd-44e0-875b-180a9a572455" alt="Menu literAlura" width="300">

#### 2. Buscar libros por título
<img src="https://github.com/user-attachments/assets/5d85d9dc-a87d-4233-9a54-ed470c3087fc" alt="Buscar libros por titulo" width="450">

#### 3. Listar libros registrados
<img src="https://github.com/user-attachments/assets/c2eb47b2-2006-43bf-abcd-e1898fec9688" alt="Menu literAlura" width="300">

#### 4. Listar autores registrados
<img src="https://github.com/user-attachments/assets/8431b624-9b4f-4e79-820d-5f216828606f" alt="Menu literAlura" width="300">

#### 5. Listar autores vivos en un determinado año
<img src="https://github.com/user-attachments/assets/d919933f-42b9-4f0e-b1a1-87559309075c" alt="Buscar libros por titulo" width="650">

#### 6. Listar libros por idioma
<img src="https://github.com/user-attachments/assets/aa1bdde0-4ec2-45b3-8521-dea8a1f0f8db" alt="Menu literAlura" width="300">

#### 7. Top 10 libros más descargados
<img src="https://github.com/user-attachments/assets/dbd0b145-bdfb-4343-8516-b8bb3d8f911c" alt="Menu literAlura" width="300">

## Recursos Utilizados
- **Entorno de Desarrollo**: IntelliJ IDEA Community Edition
- **Lenguaje**: Java 17.0.10
- **Framework**: Spring Boot 3.4.0
- **Dependencias**: Spring Data JPA, PostgreSQL Driver, Jackson Databind
- **API**: Gutendex.com
- **Base Datos**: PostgreSQL

## Instrucciones para Ejecutar el Proyecto Localmente
Sigue estos pasos para ejecutar el proyecto en tu entorno local:

1. **Clonar el Repositorio**:
   ```bash
    git clone https://github.com/joche-dev/challenge-literalura.git
    ```
2. **Crear Base de datos literalura**:
    - Abrir pgAdmin: Inicia pgAdmin e ingresa tus credenciales si es necesario.
    - Conectarte al servidor: En el panel izquierdo, haz clic en el servidor al que deseas conectarte para expandirlo (puede requerir ingresar la contraseña).
    - Abrir la sección de Bases de Datos: Haz clic derecho en la carpeta Databases y selecciona Create > Database....
    - Configurar la nueva base de datos: En la ventana emergente, completa los campos; en database name colocar literalura.
    - Para finalizar, haz clic en Save o Ok para crear la base de datos.
3. **Abrir el Proyecto en IntelliJ y Configurar el archivo application.properties**: Navega al archivo application.properties, debes revisar la configuración de conexión con la base de datos PostgreSQL.
4. **Compilar y Ejecutar el Archivo LiteraluraApplication**: Navega al archivo LiteraluraApplication.java, compílalo y ejecútalo para comenzar a utilizar la aplicación.
