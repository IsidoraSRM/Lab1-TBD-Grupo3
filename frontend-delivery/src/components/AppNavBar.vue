<script>
import { authService } from '../services/authService';

export default {
    name: 'NavBar',
    data() {
        return {
            menuOpen: false,
            isLoggedIn: false,
            userRole: ''
        }
    },
    created() {
        this.checkAuthStatus();
    },
    methods: {
        toggleMenu() {
            this.menuOpen = !this.menuOpen;
        },
        checkAuthStatus() {
            this.isLoggedIn = authService.isAuthenticated();
            this.userRole = authService.getUserRole();
        },
        logout() {
            authService.logout();
            this.isLoggedIn = false;
            this.userRole = '';
            this.$router.push('/login');
        }
    }
}
</script>

<template>
    <nav>
        <router-link to="/" class="title" img="DocDelivery.png">
            <img src="@/assets/DocDelivery.png" alt="Logo" class="nav-logo">
            DocDelivery
        </router-link>
        <div class="menu" @click="toggleMenu">
            <span></span>
            <span></span>
            <span></span>   
        </div>
        <ul :class="{ open: menuOpen }">
            <li>
                <router-link to="/">Inicio</router-link>
            </li>
            <li v-if="!isLoggedIn">
                <router-link to="/login">Iniciar Sesión</router-link>
            </li>
            <li v-if="isLoggedIn && userRole === 'ADMIN'">
                <router-link to="/admin">Panel Admin</router-link>
            </li>
            <li v-if="isLoggedIn && userRole === 'TRABAJADOR'">
                <router-link to="/trabajador">Panel Trabajador</router-link>
            </li>
            <li v-if="isLoggedIn">
                <a href="#" @click.prevent="logout">Cerrar Sesión</a>
            </li>
        </ul>
    </nav>
</template>



<style >
/* si el link esta activo se marca con este color*/
.active {
    background-color: #A3D9A5;
}
/*
colores:
- Naranjo quemado (rgb(209, 118, 0))  #D17600 → Botones y CTAs
- Mostaza (rgb(245, 196, 72))   #F5C448 → Secciones destacadas
- Terracota (rgb(181, 85, 41)) #B55529  → Contrastes elegantes
- Gris cálido (rgb(226, 220, 210)) #E2DCD2  → Fondos
- Azul petróleo (rgb(18, 90, 108)) 	#125A6C  → Detalles y profundidad
*/
nav {
    display: flex;
    justify-content: space-between ;
    align-items: center;
    background-color: #125A6C;
    position: fixed;  /* no se mueve el navbar con scroll*/
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

nav .title {
    font-size: 1.5rem;
    margin:1rem;
    font-weight: bolder;
    text-decoration: none;
    color: #E2DCD2;
    display: flex;
    align-items: center;
}

.nav-logo {
    height: 30px; /* Ajusta el tamaño según lo necesites */
    width: auto;
    margin-right: 8px; /* Espacio entre el logo y el texto */
    vertical-align: middle; /* Alinea verticalmente con el texto */
    
}

nav ul {
    display: flex;
}

nav ul li {
    list-style: none;

}

nav ul li a {
    display: block;
    text-decoration: none;
    color:#E2DCD2;
    font-weight: bold;
    padding: 0.5rem;
    margin: 0 0.5rem;
    border-radius: 0.5rem;
}
 /* el link que esta selecionado no cambiara de color si se pasa el mouse por encima*/
nav ul li a:not(.active):hover {
    background-color: #B55529 ;
}

nav .menu {
    display: none;
    position: absolute;
    top: 0.75rem;
    right: 0.5rem;
    flex-direction: column;
    justify-content: space-between;
    width:2.25rem;
    height: 2rem;

}
nav .menu span {
    height: 0.4rem;
    width:100%;
    background-color: #E2DCD2;
    border-radius: 0.2rem;;
}
/* cuando la pantalla sea menor a 480 el navar cambiara a un menu hamburguesa  */
 @media (max-width: 480px) {
    
    nav .menu {
        display: flex;
    }
    nav {
        flex-direction: column;
        align-items: start;

    }
    nav ul {
        display: none;
        flex-direction: column;
        width: 100%;
        margin-bottom: 0.25rem;
    }
    /* el menu hamburguesa se abre al hacer click en el icono de menu */
    nav ul.open {
        display: flex;
    }
    nav ul li {
        width: 100%;
        text-align: center;
        
    }
    nav ul li a {
        margin: 0.2rem 0.5rem;

    }
}
</style>