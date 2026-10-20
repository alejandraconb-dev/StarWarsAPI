const btn = document.getElementById('btnConsultar');
const input = document.getElementById('numPelicula');
const resultadoDiv = document.getElementById('resultado');
const errorDiv = document.getElementById('error');

btn.addEventListener('click', async () => {
    const num = input.value;
    resultadoDiv.classList.add('hidden');
    errorDiv.classList.add('hidden');

    try {
        // Petición dirigida al servidor Java local
        const response = await fetch(`http://localhost:8080/api/pelicula?numero=${num}`);
        const data = await response.json();

        if (data.error) {
            throw new Error(data.error);
        }

        // Mostrar el JSON procesado por Java en la interfaz
        document.getElementById('titulo').textContent = data.title;
        document.getElementById('episodio').textContent = data.episode_id;
        document.getElementById('director').textContent = data.director;
        document.getElementById('fecha').textContent = data.release_date;
        document.getElementById('crawl').textContent = data.opening_crawl;

        resultadoDiv.classList.remove('hidden');
    } catch (err) {
        errorDiv.textContent = "Error del Backend Java: " + err.message;
        errorDiv.classList.remove('hidden');
    }
});
