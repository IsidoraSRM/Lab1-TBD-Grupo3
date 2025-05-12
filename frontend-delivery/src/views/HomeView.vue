<template>
    <div class="home">
      <!-- Hero Section -->
      <section class="hero-section">
          <div class="hero-card card">
              <h1>DocDelivery</h1>
              <h2>Sistema de Gestión de Pedidos - Delivery de documentos</h2>
              <p>Gestiona y realiza seguimiento a todos tus envíos de documentos de manera eficiente y segura.</p>
          </div>
      </section>

    </div>
  </template>
  
  <script>
  export default {
    name: 'HomeView'
  }
  </script>

<style>
/* Hero Section */
.hero-section {
    margin-bottom: 30px;
}

.hero-card {
    text-align: center;
    background-color: #125A6C;
    color: white;
    width: 100%;
    padding: 20px;
}

.hero-card h1 {
    font-size: 2.5rem;
    margin-bottom: 10px;
}

.hero-card h2 {
    font-size: 1.5rem;
    margin-bottom: 15px;
    font-weight: 400;
}

.hero-card p {
    font-size: 1.1rem;
    max-width: 700px;
    margin: 0 auto;
}




</style>