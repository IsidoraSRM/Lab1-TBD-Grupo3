<template>
    <div class="create-container">
        <div v-if="loading" class="loading-spinner">
            <div class="spinner"></div>
            <p>Procesando su solicitud...</p>
        </div>
        <div v-else-if="error" class="error-message">
            <h3>Error</h3>
            <p>{{ error }}</p>
            <button class="create-button" @click="resetForm">Intentar nuevamente</button>
        </div>
        <div v-else-if="success" class="success-message">
            <h3>¡Pedido creado correctamente!</h3>
            <p>Su pedido ha sido registrado con éxito.</p>
            <router-link to="/" class="create-button">Volver al inicio</router-link>
        </div>
        <div v-else class="create-card">
            <h2>Registrar Pedido</h2>
            <p>Ingrese los datos para su pedido</p>
            
            <form @submit.prevent="createOrder">
                <!-- Sección 1: Información del Servicio -->
                <p>1. Información del Servicio</p>
                <div class="form-group">
                    <label for="service-name">Nombre del Servicio</label>
                    <input 
                        type="text" 
                        id="service-name" 
                        v-model="orderData.nombre_servicio" 
                        placeholder="Ingrese el nombre del servicio" 
                        required
                    />
                </div>
                
                <div class="form-group">
                    <label for="service-description">Descripción</label>
                    <textarea 
                        id="service-description" 
                        v-model="orderData.descripcion" 
                        placeholder="Ingrese una descripción del servicio"
                        rows="3" 
                        required
                    ></textarea>
                </div>
                
                <div class="form-group">
                    <label for="service-category">Tipo de Servicio</label>
                    <select 
                        id="service-category" 
                        v-model="orderData.categoria"
                        required
                    >
                        <option value="" disabled selected>Seleccione una categoría</option>
                        <option value="Legal">Legal</option>
                        <option value="Financiero">Financiero</option>
                        <option value="Médico">Médico</option>
                        <option value="Educativo">Educativo</option>
                        <option value="Otro">Otro</option>
                    </select>
                </div>
                
                <!-- Sección 2: Dirección -->
                <p>2. Direcciones</p>
                <div class="form-group">
                    <label for="address-origin">Dirección de Origen</label>
                    <input 
                        type="text" 
                        id="address-origin" 
                        v-model="orderData.direccionInicio" 
                        placeholder="Dirección donde se recogerá el documento" 
                        required
                    />
                </div>
                
                <div class="form-group">
                    <label for="address-destination">Dirección de Destino</label>
                    <input 
                        type="text" 
                        id="address-destination" 
                        v-model="orderData.direccionDestino" 
                        placeholder="Dirección donde se entregará el documento" 
                        required
                    />
                </div>
                
                <!-- Sección 3: Información de Pago -->
                <p>3. Información de Pago</p>
                <div class="form-group">
                    <label for="payment-method">Método de Pago</label>
                    <select 
                        id="payment-method" 
                        v-model="orderData.nombreMetodo"
                        required
                    >
                        <option value="" disabled selected>Seleccione método de pago</option>
                        <option value="Tarjeta de Crédito">Tarjeta de Crédito</option>
                        <option value="Transferencia Bancaria">Transferencia Bancaria</option>
                        <option value="Efectivo">Efectivo</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="amount">Monto a pagar</label>
                    <input 
                        type="number" 
                        id="amount" 
                        v-model="orderData.monto" 
                        placeholder="Ingrese el monto a pagar" 
                        min="1000"
                        required
                    />
                </div>
                
                <div class="form-group">
                    <label for="priority">Prioridad</label>
                    <select 
                        id="priority" 
                        v-model="orderData.prioridad"
                        required
                    >
                        <option value="" disabled selected>Seleccione la prioridad</option>
                        <option value="ALTA">Alta</option>
                        <option value="MEDIA">Media</option>
                        <option value="BAJA">Baja</option>
                    </select>
                </div>
                
                <button type="submit" class="create-button" :disabled="isSubmitting">
                    {{ isSubmitting ? 'Procesando...' : 'Hacer Pedido' }}
                </button>
            </form>
        </div>
    </div>
</template>

<script>
import clienteService from '@/services/clienteService';
import orderService from '@/services/orderService';

export default {
    name: 'CreateOrder',
    data() {
        return {
            orderData: {
                clienteId: null,
                nombre_servicio: '',
                descripcion: '',
                categoria: '',
                direccionInicio: '',
                direccionDestino: '',
                nombreMetodo: '',
                monto: '',
                prioridad: ''
            },
            loading: false,
            isSubmitting: false,
            success: false,
            error: null
        }
    },
    created() {
        this.getClientId();
    },
    methods: {
        async getClientId() {
            this.loading = true;
            try {
                const userId = localStorage.getItem('userId');
                
                if (!userId) {
                    this.error = "No se pudo identificar al usuario. Por favor, inicie sesión nuevamente.";
                    return;
                }
                
                const response = await clienteService.getclienteByUserId(userId);
                this.orderData.clienteId = response.data;
                
                if (!this.orderData.clienteId) {
                    this.error = "No se encontró información de cliente para este usuario.";
                    return;
                }
                
                console.log("Cliente ID obtenido:", this.orderData.clienteId);
            } catch (error) {
                console.error("Error al obtener ID de cliente:", error);
                this.error = "Error al cargar información del cliente: " + (error.response?.data || error.message);
            } finally {
                this.loading = false;
            }
        },
        
        async createOrder() {
            if (!this.orderData.clienteId) {
                this.error = "No se ha podido obtener su ID de cliente. Por favor, inténtelo más tarde.";
                return;
            }
            
            this.isSubmitting = true;
            
            try {
                console.log("Enviando datos del pedido:", this.orderData);
                const response = await orderService.registerOrder(this.orderData);
                console.log("Pedido creado:", response.data);
                this.success = true;
            } catch (error) {
                console.error("Error al crear pedido:", error);
                this.error = "Error al crear el pedido: " + (error.response?.data || error.message);
            } finally {
                this.isSubmitting = false;
            }
        },
        
        resetForm() {
            this.error = null;
            this.success = false;
            this.orderData = {
                clienteId: this.orderData.clienteId, // Mantenemos el ID del cliente
                nombre_servicio: '',
                descripcion: '',
                categoria: '',
                direccionInicio: '',
                direccionDestino: '',
                nombreMetodo: '',
                monto: '',
                prioridad: ''
            };
        }
    }
}
</script>

<style scoped>

.create-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
  padding: 20px;    
}
.create-card {
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
p {
  font-weight: bold;
  font-size: 24px;
  text-align: justify;
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
.create-button {
  width: 95%;
  padding: 12px;
  background: #B55529 ;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  /*poner al centro del contenedor*/
    display: flex;
    justify-content: center;
    align-items: center;
}

.create-button:hover {
  background: #D17600;
}

.create-button:disabled {
  background: #ccc;
  cursor: not-allowed;
}
textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
    transition: border 0.3s ease;
    resize: vertical;
}

select {
    width: 100%;
    padding: 12px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
    transition: border 0.3s ease;
    background-color: white;
}

.loading-spinner {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 40px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(18, 90, 108, 0.13);
    width: 100%;
    max-width: 400px;
}

.spinner {
    width: 50px;
    height: 50px;
    border: 5px solid rgba(181, 85, 41, 0.1);
    border-radius: 50%;
    border-top-color: #B55529;
    animation: spin 1s linear infinite;
    margin-bottom: 20px;
}

@keyframes spin {
    to { transform: rotate(360deg); }
}

.error-message, .success-message {
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(18, 90, 108, 0.13);
    width: 100%;
    max-width: 400px;
    text-align: center;
}

.error-message {
    background-color: #ffebee;
    border-left: 4px solid #c62828;
}

.success-message {
    background-color: #e8f5e9;
    border-left: 4px solid #2e7d32;
}

.error-message h3, .success-message h3 {
    margin-top: 0;
    color: #333;
}

button {
    display: block;
}


</style>