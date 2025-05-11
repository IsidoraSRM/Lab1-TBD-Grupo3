import axios from 'axios';

const API_URL = 'http://localhost:8080/api/pedidos';

const orderService = {
  
  crearPedido(pedidoDto) {
    return axios.post(`${API_URL}/crear`, pedidoDto, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  },

  
  getPedidoById(id) {
    return axios.get(`${API_URL}/${id}`, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  },


  registerOrder(orderData) {
    const params = new URLSearchParams({
      clienteId: orderData.clienteId,
      prioridad: orderData.prioridad,
      nombreMetodo: orderData.nombreMetodo,
      monto: orderData.monto,
      nombre_servicio: orderData.nombre_servicio,
      descripcion: orderData.descripcion,
      categoria: orderData.categoria,
      direccionInicio: orderData.direccionInicio,
      direccionDestino: orderData.direccionDestino
    });

    return axios.post(`${API_URL}/register?${params}`, null, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  },

  cambiarEstadoPedido(idPedido, nuevoEstado) {
    return axios.put(`${API_URL}/${idPedido}/estado?estado=${nuevoEstado}`, null, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  },


  confirmarPedido(idPedido) {
    return axios.post(`${API_URL}/confirmar/${idPedido}`, null, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  },


  getAllOrders() {
    return axios.get(`${API_URL}/all`, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  },
  getPedidosByRepartidor(repartidorId) {
    return axios.get(`${API_URL}/repartidor/${repartidorId}`, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  },
  getPedidosByUserId(repartidorId) {
    return axios.get(`${API_URL}/repartidor/${repartidorId}`, {
      headers: { Authorization: `Bearer ${localStorage.getItem('authToken')}` }
    });
  }
  
};

export default orderService;