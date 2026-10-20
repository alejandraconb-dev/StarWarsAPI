const btn = document.getElementById('btnConsultar');
const input = document.getElementById('numPelicula');
const resultadoDiv = document.getElementById('resultado');
const errorDiv = document.getElementById('error');

// Función para pintar los datos en la pantalla
function mostrarPelicula(data) {
    document.getElementById('titulo').textContent = data.title;
    document.getElementById('episodio').textContent = data.episode_id;
    document.getElementById('director').textContent = data.director;
    document.getElementById('fecha').textContent = data.release_date;
    document.getElementById('crawl').textContent = data.opening_crawl;
    resultadoDiv.classList.remove('hidden');
}

// Al cargar la página, verificamos si hay una consulta previa guardada
window.addEventListener('DOMContentLoaded', () => {
    const peliculaGuardada = localStorage.getItem('resultadoStarWars');
    if (peliculaGuardada) {
        const data = JSON.parse(peliculaGuardada);
        mostrarPelicula(data);
    }
});

btn.addEventListener('click', async (event) => {
    event.preventDefault(); // Evita recargas por defecto

    const num = input.value;
    resultadoDiv.classList.add('hidden');
    errorDiv.classList.add('hidden');

    if (!num) {
        errorDiv.textContent = "Por favor, ingresa un número de película.";
        errorDiv.classList.remove('hidden');
        return;
    }

    try {
        // Petición dirigida al servidor Java local
        const response = await fetch(`http://localhost:8080/api/pelicula?numero=${num}`);
        const data = await response.json();

        if (data.error) {
            throw new Error(data.error);
        }

        // Guardamos los datos en el navegador antes de que Live Server recargue
        localStorage.setItem('resultadoStarWars', JSON.stringify(data));

        // Mostramos el resultado
        mostrarPelicula(data);

    } catch (err) {
        errorDiv.textContent = "Error: " + err.message;
        errorDiv.classList.remove('hidden');
    }
});