<script setup>
import { ref } from "vue";

// Puedes usar variables para datos dinámicos.
const userName = ref("Usuario");
</script>

<template>
  <main class="main-container">
    <!-- Barra de navegación -->
    <nav class="navbar">
      <div class="logo">Rayo Maquin</div>
      <div class="nav-links">
        <a href="#">Inicio</a>
        <a href="#">Perfil</a>
        <a href="#">Opciones</a>
        <a href="#" @click="logout">Cerrar Sesión</a>
      </div>
    </nav>

    <!-- Contenido principal -->
    <section class="welcome-section">
      <h1>¡Bienvenido, {{ userName }}!</h1>
      <p>Explora nuestras funcionalidades y descubre cómo podemos ayudarte.</p>
    </section>

    <!-- Secciones adicionales -->
    <section class="content-section">
      <div class="card">
        <h2>Arriendo</h2>
        <p>Accede para arrendar.</p>
        <button class="card-button">Ir</button>
      </div>
      <div class="card">
        <h2>Ver autos</h2>
        <p>Revisa los autos.</p>
        <button class="card-button">Ir</button>
      </div>
      <div class="card">
        <h2>pagos</h2>
        <p>No hay plata.</p>
        <button class="card-button">Ir</button>
      </div>
    </section>
  </main>
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.removeItem("login"); // Limpia el almacenamiento local
      window.location.href = "/"; // Redirige al login
    },
  },
};
</script>

<style>
/* Estilos generales */
body {
  margin: 0;
  font-family: "Arial", sans-serif;
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
}

.main-container {
  padding: 1rem;
}

/* Barra de navegación */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #ff80ab;
  color: #fff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
}

.nav-links a {
  color: #fff;
  margin-left: 1.5rem;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s ease;
}

.nav-links a:hover {
  color: #ffd1dc;
}

/* Sección de bienvenida */
.welcome-section {
  text-align: center;
  margin: 2rem 0;
}

.welcome-section h1 {
  font-size: 2rem;
  color: #ff4081;
}

.welcome-section p {
  font-size: 1.2rem;
  color: #ff6f61;
}

/* Sección de tarjetas */
.content-section {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 1.5rem;
  margin-top: 2rem;
}

.card {
  background: #fff3e0;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 300px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card h2 {
  font-size: 1.5rem;
  color: #ff4081;
  margin-bottom: 1rem;
}

.card p {
  font-size: 1rem;
  color: #757575;
  margin-bottom: 1.5rem;
}

.card-button {
  background: #ff80ab;
  color: #fff;
  border: none;
  padding: 0.75rem 1rem;
  border-radius: 8px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.card-button:hover {
  background: #ff4081;
}

.card:hover {
  transform: translateY(-10px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}
</style>

