const btn = document.getElementById('btnConsultar');
const input = document.getElementById('numPelicula');
const resultadoDiv = document.getElementById('resultado');
const errorDiv = document.getElementById('error');

function mostrarPelicula(data) {
    document.getElementById('titulo').textContent = data.title;
    document.getElementById('episodio').textContent = data.episode_id;
    document.getElementById('director').textContent = data.director;
    document.getElementById('fecha').textContent = data.release_date;
    document.getElementById('crawl').textContent = data.opening_crawl;
    resultadoDiv.classList.remove('hidden');
}

window.addEventListener('DOMContentLoaded', () => {
    const peliculaGuardada = localStorage.getItem('resultadoStarWars');
    if (peliculaGuardada) {
        const data = JSON.parse(peliculaGuardada);
        mostrarPelicula(data);
    }
});

btn.addEventListener('click', async (event) => {
    event.preventDefault();

    const valorInput = input.value.trim();
    resultadoDiv.classList.add('hidden');
    errorDiv.classList.add('hidden');

    // Validación 1: Verificar si está vacío o no es un número entero
    if (valorInput === "" || isNaN(valorInput)) {
        errorDiv.textContent = "Por favor, debe poner solo números y del 1 al 7.";
        errorDiv.classList.remove('hidden');
        return;
    }

    const num = parseInt(valorInput);

    // Validación 2: Verificar el rango (1 al 7)
    if (num < 1 || num > 7) {
        errorDiv.textContent = "Por favor, debe poner solo números y del 1 al 7.";
        errorDiv.classList.remove('hidden');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/pelicula?numero=${num}`);
        const data = await response.json();

        if (data.error) {
            throw new Error(data.error);
        }

        localStorage.setItem('resultadoStarWars', JSON.stringify(data));
        mostrarPelicula(data);

    } catch (err) {
        errorDiv.textContent = "Error del Backend Java: " + err.message;
        errorDiv.classList.remove('hidden');
    }
});