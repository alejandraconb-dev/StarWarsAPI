<img align="center" src="https://github.com/alejandraconb-dev/StarWarsAPI/blob/main/StarWarsAPI.png" width="750"> 

# 📌StarWarsAPI
Aplicación web interactiva que permite consultar los datos de las películas de la saga de **Star Wars**.

## 📎 Descripción del proyecto
* Se implementa un servidor HTTP liviano (`HttpServer`) que procesa solicitudes, consume la API externa de Star Wars (SWAPI) mediante `HttpClient` y formatea la información usando **GSON**.
* **Persistencia Local en Servidor** (Resguardo automático de datos): Genera automáticamente un archivo `.json` por cada consulta exitosa en el disco del servidor.
* **Mapeo de Episodios Inteligente:**: Sincroniza los números ingresados por el usuario (1 al 7) con los IDs reales correspondientes de la API externa.
* Uso de `localStorage` para mantener la información fija en pantalla ante recargas automáticas del entorno de desarrollo.

## 🖥️ Tecnologías Utilizadas:

- Java (JDK 17+).
- `com.sun.net.httpserver`
- Javascript (Async/Await, Fetch API, LocalStorage).
- HTML/CSS.
- JSON.
- Google GSON.
- Postman.
- Visual Studio Code.
- Eclipse IDE.

## 📦 Requisitos

- Java 8 o superior.
- IDE: como IntelliJ o Eclipse o editor de código: Visual Studio Code.
- Bibliotecas usadas: java.net.http, com.google.gson.
alejandraconb-dev

## ▶️ Cómo ejecutar

- Clona o descarga el proyecto.
- Ejecuta la clase principal **`ServidorStarWars.java`**. El servidor se iniciará localmente en el puerto `8080`.
- Abre el archivo **`index.html`** con la extensión **Live Server** en VS Code.
- Ingresa un número de película (1-7) cuando se solicite.

## ⚠️ Notas

- Asegurese de que ConsultaPelicula use la URL correcta: https://swapi.py4e.com/api/films/.
</br>

<img src="https://github.com/alejandraconb-dev/StarWarsAPI/blob/main/StarWarsAPI.GIF" width="5000">

💙 <strong>Alejandra Contreras</strong> :octocat: </br>
<a href="https://www.linkedin.com/in/alejandraconb-dev/" target="_blank">
</br>
<img align="left"  src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
<img align="center" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Relieved%20Face.png" target="_blank" height="40"></a>
