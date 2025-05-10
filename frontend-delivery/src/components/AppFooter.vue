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
            <div class="footer-content">
                <div class="footer-brand">
                    <p class="copyright">&copy; 2025 DocDelivery. Grupo 3 TBD.</p>
                    <p class="tagline">Entregando soluciones, conectando personas</p>
                </div>
                <div class="footer-links">
                    <ul class="link-list">
                        <li><a href="#">Políticas de Privacidad</a></li>
                        <li><a href="#">Términos de Servicio</a></li>
                        <li><a href="#">Contáctanos</a></li>
                    </ul>
                    <ul class="social-media">
                        <li><a href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a></li>
                        <li><a href="#" aria-label="Twitter"><i class="fab fa-twitter"></i></a></li>
                        <li><a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a></li>
                        <li><a href="#" aria-label="LinkedIn"><i class="fab fa-linkedin-in"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
</template>

<style scoped>
/* 
Paleta de colores:
- #D17600 (Naranjo quemado) → Botones y CTAs
- #F5C448 (Mostaza) → Secciones destacadas
- #B55529 (Terracota) → Contrastes elegantes
- #E2DCD2 (Gris cálido) → Fondos
- #125A6C (Azul petróleo) → Detalles y profundidad
*/

.footer {
    background-color: #125A6C;
    color: #E2DCD2;
    padding: 40px 0 20px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    box-shadow: 0 -5px 15px rgba(0, 0, 0, 0.1);
}

.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

.footer-content {
    display: flex;
    flex-direction: column;
    gap: 30px;
}

.footer-brand {
    text-align: center;
}

.copyright {
    font-size: 16px;
    margin-bottom: 8px;
    font-weight: 500;
}

.tagline {
    font-size: 14px;
    color: rgba(226, 220, 210, 0.8);
    margin: 0;
    font-style: italic;
}

.footer-links {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
}

.link-list {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 15px 30px;
}

.link-list a {
    color: #E2DCD2;
    text-decoration: none;
    font-size: 14px;
    transition: color 0.3s ease;
    position: relative;
}

.link-list a:hover {
    color: #F5C448;
}

.link-list a::after {
    content: '';
    position: absolute;
    width: 0;
    height: 2px;
    bottom: -4px;
    left: 0;
    background-color: #F5C448;
    transition: width 0.3s ease;
}

.link-list a:hover::after {
    width: 100%;
}

.social-media {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
    gap: 20px;
}

.social-media a {
    color: #E2DCD2;
    text-decoration: none;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    background-color: rgba(226, 220, 210, 0.1);
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease;
}

.social-media a:hover {
    background-color: #F5C448;
    color: #125A6C;
    transform: translateY(-3px);
}

/* Estilos responsivos */
@media (min-width: 768px) {
    .footer-content {
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
    }
    
    .footer-brand {
        text-align: left;
    }
    
    .footer-links {
        align-items: flex-end;
    }
    
    .link-list {
        justify-content: flex-end;
    }
}
</style>