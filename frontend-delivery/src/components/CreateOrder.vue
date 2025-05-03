<template>
    <div class="create-container">
        <div class="create-card">
            <h2>Registrar Pedido</h2>
            <p>Como es el flujo de un delivery? </p>
            <p>1. Información de la Empresa</p>
            <form @submit.prevent="createOrder">
                <div class="form-group">
                    <label for="order-name">Nombre empresa</label>
                    <input 
                        type="text" 
                        id="order-name" 
                        v-model="orderData.name" 
                        placeholder="Ingrese el nombre de la empresa" 
                        required
                    />
                </div>
                <div class="form-group">
                    <label for="order-name">Direccion Empresa</label>
                    <input 
                        type="text" 
                        id="order-name" 
                        v-model="orderData.name" 
                        placeholder="Ingrese la Direccion Empresa" 
                        required
                    />
                </div>
                <div class="form-group">
                    <label for="order-name">Tipo de Documento</label>
                    <input 
                        type="text" 
                        id="order-name" 
                        v-model="orderData.name" 
                        placeholder="Ingrese el Tipo de Documento" 
                        required
                    />
                </div>
                <p>2. Informacion Documento</p>
                <div class="form-group">
                    <label for="order-name">Nombre Documento</label>
                    <input 
                        type="text" 
                        id="order-name" 
                        v-model="orderData.name" 
                        placeholder="Ingrese el nombre del Documento" 
                        required
                    />
                </div>
                <div class="form-group">
                    <label for="order-name">Descripción Documento</label>
                    <input 
                        type="text" 
                        id="order-name" 
                        v-model="orderData.name" 
                        placeholder="Ingrese una breve descripción del Documento" 
                        required
                    />
                </div>
                <div class="form-group">
                    <label for="order-name">Categoría Documento</label>
                    <input 
                        type="text" 
                        id="order-name" 
                        v-model="orderData.name" 
                        placeholder="Ingrese la categoría de Documento" 
                        required
                    />
                </div>
                <div class="form-group">
                    <label for="order-name">Cantidad</label>
                    <input 
                        type="text" 
                        id="order-name" 
                        v-model="orderData.name" 
                        placeholder="Ingrese la cantidad" 
                        required
                    />
                </div>

                <buttom type="submit" class="create-button">
                    Hacer Pedido
                </buttom>
            </form>
        </div>
    </div>

    
</template>

<script >
    export default {
        name: 'CreateOrder',
        data() {
        return {
            orderData: {
                name: '',
                // Aquí puedes agregar otras propiedades de tu pedido
            }
        }
    },
    methods: {
        createOrder() {
            // Implementa la lógica para crear el pedido
            console.log('Creando pedido:', this.orderData);
            // Aquí irá tu lógica para enviar los datos al servidor
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


</style>