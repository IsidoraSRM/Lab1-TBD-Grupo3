<script>
export default {
    name: 'HomeView',
    data() {
        return {
            userName: 'Juan Pérez',
            activeOrders: 2,
            recentOrders: [
                { id: '10025', date: '12/05/2024', type: 'Contrato', status: 'Entregado', statusColor: 'success' },
                { id: '10026', date: '14/05/2024', type: 'Factura', status: 'En camino', statusColor: 'warning' },
                { id: '10027', date: '15/05/2024', type: 'Certificado', status: 'Procesando', statusColor: 'info' }
            ],
            filterStatus: 'all',
            searchQuery: ''
        }
    },
    computed: {
        filteredOrders() {
            let orders = this.recentOrders;
            
            // Filtrar por estado
            if (this.filterStatus !== 'all') {
                orders = orders.filter(order => {
                    if (this.filterStatus === 'active') {
                        return order.status !== 'Entregado';
                    } else {
                        return order.status === this.filterStatus;
                    }
                });
            }
            
            // Filtrar por búsqueda
            if (this.searchQuery) {
                const query = this.searchQuery.toLowerCase();
                orders = orders.filter(order => 
                    order.id.toLowerCase().includes(query) || 
                    order.type.toLowerCase().includes(query)
                );
            }
            
            return orders;
        }
    },
    methods: {
        getStatusClass(status) {
            return {
                'success': status === 'Entregado',
                'warning': status === 'En camino',
                'info': status === 'Procesando',
                'danger': status === 'Cancelado'
            };
        }
    }
}
</script>

<template>
    <div class="home-view">
        <!-- Sección de Bienvenida -->
        <section class="welcome-section">
            <div class="welcome-card card">
                <h2>Bienvenido, {{ userName }}</h2>
                <p>Tienes {{ activeOrders }} pedidos en curso</p>
                
                <div class="quick-actions">
                    <router-link to="/client" class="btn btn-primary">Nuevo Pedido</router-link>
                    <router-link to="/order-history" class="btn btn-secondary">Ver Historial</router-link>
                </div>
            </div>
        </section>

        <!-- Sección de Pedidos Recientes -->
        <section class="recent-orders">
            <div class="card">
                <div class="section-header">
                    <h3>Pedidos Recientes</h3>
                    
                    <div class="filters">
                        <div class="search-box">
                            <input 
                                type="text" 
                                v-model="searchQuery" 
                                placeholder="Buscar por número o tipo..."
                            >
                            <i class="fas fa-search"></i>
                        </div>
                        
                        <select v-model="filterStatus" class="status-filter">
                            <option value="all">Todos</option>
                            <option value="active">En curso</option>
                            <option value="Entregado">Entregados</option>
                            <option value="En camino">En camino</option>
                            <option value="Procesando">Procesando</option>
                        </select>
                    </div>
                </div>
                
                <div class="table-responsive">
                    <table class="orders-table">
                        <thead>
                            <tr>
                                <th># Pedido</th>
                                <th>Fecha</th>
                                <th>Tipo Documento</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="order in filteredOrders" :key="order.id">
                                <td>{{ order.id }}</td>
                                <td>{{ order.date }}</td>
                                <td>{{ order.type }}</td>
                                <td>
                                    <span class="status-badge" :class="getStatusClass(order.status)">
                                        {{ order.status }}
                                    </span>
                                </td>
                                <td>
                                    <button class="btn-action" v-if="order.status === 'En camino'">
                                        <i class="fas fa-truck"></i> Rastrear
                                    </button>
                                    <button class="btn-action">
                                        <i class="fas fa-eye"></i> Ver Detalle
                                    </button>
                                </td>
                            </tr>
                            <tr v-if="filteredOrders.length === 0">
                                <td colspan="5" class="no-orders">No se encontraron pedidos</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>

        <!-- Sección de Información Útil -->
        <section class="useful-info">
            <div class="info-cards">
                <div class="info-card card">
                    <div class="card-icon">
                        <i class="fas fa-headset"></i>
                    </div>
                    <h4>¿Problemas con un pedido?</h4>
                    <p>Nuestro equipo de soporte está disponible para ayudarte</p>
                    <button class="btn btn-outline">
                        <i class="fas fa-envelope"></i> Contactar
                    </button>
                </div>
                
                <div class="info-card card">
                    <div class="card-icon">
                        <i class="fas fa-book"></i>
                    </div>
                    <h4>Cómo preparar tus documentos</h4>
                    <p>Guía completa para empaquetar y enviar tus documentos</p>
                    <button class="btn btn-outline">
                        <i class="fas fa-download"></i> Descargar PDF
                    </button>
                </div>
            </div>
        </section>
    </div>
</template>

<style scoped>
.home-view {
    padding: 20px;
    background-color: #F5F5F5;
    min-height: calc(100vh - 120px); /* Ajustar según tu navbar y footer */
}

/* Estilos para las tarjetas */
.card {
    background: white;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
    padding: 20px;
    margin-bottom: 20px;
}

/* Sección de Bienvenida */
.welcome-card {
    text-align: center;
    padding: 30px 20px;
}

.welcome-card h2 {
    color: #125A6C;
    margin-bottom: 10px;
}

.welcome-card p {
    color: #666;
    margin-bottom: 25px;
    font-size: 18px;
}

.quick-actions {
    display: flex;
    justify-content: center;
    gap: 15px;
    flex-wrap: wrap;
}

/* Botones */
.btn {
    padding: 10px 20px;
    border-radius: 5px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.3s ease;
    border: none;
    display: inline-flex;
    align-items: center;
    gap: 8px;
}

.btn-primary {
    background-color: #20D547;
    color: white;
}

.btn-primary:hover {
    background-color: #1AB53D;
    transform: translateY(-2px);
}

.btn-secondary {
    background-color: #E2DCD2;
    color: #125A6C;
}

.btn-secondary:hover {
    background-color: #D5CEC3;
}

.btn-outline {
    background: transparent;
    border: 1px solid #125A6C;
    color: #125A6C;
}

.btn-outline:hover {
    background-color: rgba(18, 90, 108, 0.1);
}

.btn-action {
    background: none;
    border: none;
    color: #125A6C;
    cursor: pointer;
    padding: 5px 10px;
    margin: 0 5px;
    font-size: 14px;
    transition: color 0.3s;
}

.btn-action:hover {
    color: #D17600;
}

/* Sección de Pedidos Recientes */
.section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    flex-wrap: wrap;
    gap: 15px;
}

.filters {
    display: flex;
    gap: 15px;
    align-items: center;
}

.search-box {
    position: relative;
}

.search-box input {
    padding: 8px 15px 8px 35px;
    border: 1px solid #ddd;
    border-radius: 5px;
    width: 200px;
}

.search-box i {
    position: absolute;
    left: 10px;
    top: 50%;
    transform: translateY(-50%);
    color: #777;
}

.status-filter {
    padding: 8px 15px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background: white;
    color: #555;
}

.orders-table {
    width: 100%;
    border-collapse: collapse;
}

.orders-table th {
    text-align: left;
    padding: 12px 15px;
    background-color: #f8f9fa;
    color: #125A6C;
    font-weight: 600;
}

.orders-table td {
    padding: 12px 15px;
    border-bottom: 1px solid #eee;
    vertical-align: middle;
}

.orders-table tr:hover td {
    background-color: #f8f9fa;
}

.status-badge {
    padding: 5px 10px;
    border-radius: 20px;
    font-size: 13px;
    font-weight: 500;
    display: inline-block;
}

.status-badge.success {
    background-color: rgba(32, 213, 71, 0.1);
    color: #20D547;
}

.status-badge.warning {
    background-color: rgba(245, 196, 72, 0.2);
    color: #B55529;
}

.status-badge.info {
    background-color: rgba(18, 90, 108, 0.1);
    color: #125A6C;
}

.status-badge.danger {
    background-color: rgba(181, 85, 41, 0.1);
    color: #B55529;
}

.no-orders {
    text-align: center;
    padding: 30px;
    color: #777;
}

/* Sección de Información Útil */
.useful-info {
    margin-top: 30px;
}

.info-cards {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
}

.info-card {
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100%;
}

.card-icon {
    width: 60px;
    height: 60px;
    background-color: rgba(18, 90, 108, 0.1);
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 15px;
    color: #125A6C;
    font-size: 24px;
}

.info-card h4 {
    color: #125A6C;
    margin-bottom: 10px;
}

.info-card p {
    color: #666;
    margin-bottom: 20px;
    flex-grow: 1;
}

/* Responsive */
@media (max-width: 768px) {
    .section-header {
        flex-direction: column;
        align-items: flex-start;
    }
    
    .filters {
        width: 100%;
        flex-wrap: wrap;
    }
    
    .search-box input {
        width: 100%;
    }
    
    .orders-table {
        display: block;
        overflow-x: auto;
    }
    
    .quick-actions {
        flex-direction: column;
        width: 100%;
    }
    
    .btn {
        width: 100%;
        justify-content: center;
    }
}
</style>