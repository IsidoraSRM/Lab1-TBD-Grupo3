<template>
  <div class="login-container">
    <div class="login-card">
      <h2>Iniciar Sesión</h2>
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
      
      <form @submit.prevent="loginUser">
        <div class="form-group">
          <label for="email">Correo Electrónico</label>
          <input 
            type="email" 
            id="email" 
            v-model="credentials.email" 
            placeholder="Ingrese su correo" 
            required
          />
        </div>

        <div class="form-group">
          <label for="password">Contraseña</label>
          <input 
            type="password" 
            id="password" 
            v-model="credentials.password" 
            placeholder="Ingrese su contraseña" 
            required
          />
        </div>

        <button type="submit" class="login-button" :disabled="loading">
          {{ loading ? 'Cargando...' : 'Iniciar Sesión' }}
        </button>
      </form>

      <div class="register-link">
        ¿No tienes una cuenta? 
        <a href="#" @click.prevent="goToRegister">Regístrate</a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'LoginView',
  data() {
    return {
      credentials: {
        email: '',
        password: ''
      },
      loading: false,
      errorMessage: ''
    }
  },
  methods: {
    async loginUser() {
      this.loading = true;
      this.errorMessage = '';
      
      try {
        const response = await axios.post(
          process.env.VUE_APP_API_URL + 'auth/login',
          this.credentials
        );
        
        // Guardar token y datos de usuario en localStorage
        localStorage.setItem('authToken', response.data.token);
        localStorage.setItem('userId', response.data.userId);
        localStorage.setItem('userRole', response.data.role);
        
        // Redireccionar según el rol
        this.redirectBasedOnRole(response.data.role);
        
      } catch (error) {
        console.error('Error de login:', error);
        this.errorMessage = error.response?.data || 'Error al iniciar sesión. Verifique sus credenciales.';
      } finally {
        this.loading = false;
      }
    },
    
    redirectBasedOnRole(role) {
      // Aquí puedes personalizar la redirección según los roles
      if (role === 'ADMIN') {
        this.$router.push('/admin');
      } else if (role === 'TRABAJADOR') {
        this.$router.push('/trabajador');
      } else {
        // Para usuarios normales
        this.$router.push('/');
      }
    },
    
    goToRegister() {
      // Implementar si tienes una página de registro
      alert('Funcionalidad de registro pendiente');
      // this.$router.push('/register');
    }
  }
}
</script>

<style scoped>

/*
colores:
- Naranjo quemado (rgb(209, 118, 0))  #D17600 → Botones y CTAs
- Mostaza (rgb(245, 196, 72))   #F5C448 → Secciones destacadas
- Terracota (rgb(181, 85, 41)) #B55529  → Contrastes elegantes
- Gris cálido (rgb(226, 220, 210)) #E2DCD2  → Fondos
- Azul petróleo (rgb(18, 90, 108)) 	#125A6C  → Detalles y profundidad
*/

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 60px);
  padding: 20px;
}

.login-card {
  background: #ffffff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(18, 90, 108, 0.13);
  width: 100%;
  max-width: 400px;
}

h2 {
  font-weight: bold;
  font-size: 30px;
  text-align: center;
  margin-bottom: 24px;
  color: #125A6C;
}

.form-group {
  margin-bottom: 20px;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}


label {
  display: block;
  margin-bottom: 6px;
  font-weight: bold;
  color: #555;
  font-size: 16px;
  align-self: flex-start;
}

input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
  transition: border 0.3s ease;
}

input:focus {
  border-color: #D17600;
  outline: none;
}

.login-button {
  width: 100%;
  padding: 12px;
  background: #D17600;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
}

.login-button:hover {
  background: #F5C448;
}

.login-button:disabled {
  background: #ccc;
  cursor: not-allowed;
}

.register-link {
  margin-top: 20px;
  text-align: center;
}

.register-link a {
  color: #125A6C;
  text-decoration: underline;
}

.error-message {
  background: #fff2f2;
  color: #ff3333;
  padding: 10px;
  border-radius: 4px;
  margin-bottom: 20px;
  text-align: center;
  border: 1px solid #ffcaca;
}
</style>