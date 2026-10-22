<img align="center" src="https://github.com/alejandraconb-dev/StarWarsAPI/blob/main/StarWarsAPI.png" width="750"> 

# 📌StarWarsAPI
Interactive web application that allows you to consult the data of the movies of the **Star Wars** saga.

## 📎 Description of the project
* A lightweight HTTP server is implemented (`HttpServer`) that processes requests, consumes the Star Wars external API (SWAPI) by `HttpClient` and format the information using **GSON**.
* **Local Persistence on the Server** (Automatic data backup): Automatically generates a file`.json` for each successful query on the server disk.
* **Intelligent Episode Mapping**: Synchronizes the numbers entered by the user (1 to 7) with the corresponding real IDs of the external API.
* Use of `localStorage` to keep the information fixed on the screen before automatic reloads of the development environment.
## 🖥️ Technologies Used:

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

- Java 8 or higher.
- IDE: as IntelliJ or Eclipse or code editor: Visual Studio Code.
- Used libraries: java.net.http, com.google.gson.

## ▶️ How to execute

- Clone or download the project.
- Run the main class **`ServidorStarWars.java`**. The server will be started locally on the port`8080`.
- Open the file **`index.html`** with the extension **Live Server** in VS Code.
- Enter a movie number (1-7) when requested.

## ⚠️ Notes

- Make sure that ConsultaPelicula uses the correct URL: https://swapi.py4e.com/api/films/.
</br>

<img src="https://github.com/alejandraconb-dev/StarWarsAPI/blob/main/StarWarsAPI.GIF" width="5000">

💙 <strong>Alejandra Contreras</strong> :octocat: </br>
<a href="https://www.linkedin.com/in/alejandraconb-dev/" target="_blank">
</br>
<img align="left"  src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
<img align="center" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Relieved%20Face.png" target="_blank" height="40"></a>
