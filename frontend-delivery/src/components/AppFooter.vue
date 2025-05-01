<script>

export default {
    name: 'AppFooter',
    data() {
        return {
            // Aquí puedes agregar datos si es necesario
        }
    },
    methods: {
        // Aquí puedes agregar métodos si es necesario
    }
}

</script>



<template>

    <footer class="footer">
        <div class="container">
            <p>&copy; 2025 DocDelivery. Grupo 3 TBD.</p>
            <ul class="social-media">
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">Instagram</a></li>
            </ul>
        </div>
    </footer>
    
</template>


<style scoped>

/*
colores:
- Naranjo quemado (rgb(209, 118, 0))  #D17600 → Botones y CTAs
- Mostaza (rgb(245, 196, 72))   #F5C448 → Secciones destacadas
- Terracota (rgb(181, 85, 41)) #B55529  → Contrastes elegantes
- Gris cálido (rgb(226, 220, 210)) #E2DCD2  → Fondos
- Azul petróleo (rgb(18, 90, 108)) 	#125A6C  → Detalles y profundidad
*/

.footer {
    background-color: #125A6C; /* Gris cálido de tu paleta */
    color: #E2DCD2; /* Color del texto */
    padding: 20px 0;
    text-align: center;
    position: fixed;
    bottom: 0;
    width: 100%;
    z-index: 1000;
}
.footer .container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}
.footer p {
    margin: 0;
    font-size: 14px;
}
.footer .social-media {
    list-style: none;
    padding: 0;
    margin: 10px 0 0;
    display: flex;
    justify-content: center;
}
.footer .social-media li {
    margin: 0 10px;
}
.footer .social-media a {
    text-decoration: none;
    color: #E2DCD2; /* Color de los enlaces */
    font-size: 14px;
    transition: color 0.3s;
}

</style>