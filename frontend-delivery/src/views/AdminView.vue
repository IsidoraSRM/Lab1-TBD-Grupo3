<template>
  <div class="admin-dashboard">
    <!-- Barra superior -->
    <div class="admin-header">
      <div class="header-left">
        <h1>Panel de Administración</h1>
        <p class="welcome-message">Bienvenido, Administrador</p>
      </div>
      <div class="header-right">
        <button class="refresh-btn" @click="fetchData">
          <i class="fas fa-sync-alt"></i> Actualizar
        </button>
        <span class="last-updated">Última actualización: {{ lastUpdated }}</span>
      </div>
    </div>

    <!-- Resumen rápido -->
    <div class="quick-stats">
      <div class="stat-card">
        <div class="stat-icon bg-primary">
          <i class="fas fa-shopping-bag"></i>
        </div>
        <div class="stat-info">
          <h3>Pedidos hoy</h3>
          <p class="stat-value">{{ stats.ordersToday }}</p>
          <p class="stat-change" :class="stats.ordersChange >= 0 ? 'positive' : 'negative'">
            <i :class="stats.ordersChange >= 0 ? 'fas fa-arrow-up' : 'fas fa-arrow-down'"></i>
            {{ Math.abs(stats.ordersChange) }}% desde ayer
          </p>
        </div>
      </div>

      <div class="stat-card">
        <div class="stat-icon bg-success">
          <i class="fas fa-users"></i>
        </div>
        <div class="stat-info">
          <h3>Clientes nuevos</h3>
          <p class="stat-value">{{ stats.newCustomers }}</p>
          <p class="stat-change positive">
            <i class="fas fa-arrow-up"></i>
            12% este mes
          </p>
        </div>
      </div>

      <div class="stat-card">
        <div class="stat-icon bg-warning">
          <i class="fas fa-truck"></i>
        </div>
        <div class="stat-info">
          <h3>Entregas pendientes</h3>
          <p class="stat-value">{{ stats.pendingDeliveries }}</p>
          <p class="stat-change negative">
            <i class="fas fa-arrow-down"></i>
            5% menos que ayer
          </p>
        </div>
      </div>

      <div class="stat-card">
        <div class="stat-icon bg-danger">
          <i class="fas fa-exclamation-triangle"></i>
        </div>
        <div class="stat-info">
          <h3>Problemas reportados</h3>
          <p class="stat-value">{{ stats.reportedIssues }}</p>
          <p class="stat-change" :class="stats.issuesChange >= 0 ? 'positive' : 'negative'">
            <i :class="stats.issuesChange >= 0 ? 'fas fa-arrow-up' : 'fas fa-arrow-down'"></i>
            {{ Math.abs(stats.issuesChange) }}% esta semana
          </p>
        </div>
      </div>
    </div>

    <!-- Sección principal -->
    <div class="main-content">
      <!-- Consultas SQL -->
      <div class="card query-section">
        <div class="card-header">
          <h2>Consultas Analíticas</h2>
          <select v-model="selectedQuery" class="query-select">
            <option value="" disabled>Seleccione una consulta</option>
            <option value="1">Cliente que más ha gastado</option>
            <option value="2">Productos más pedidos por categoría</option>
            <option value="3">Empresas con más entregas fallidas</option>
            <option value="4">Tiempo promedio por repartidor</option>
            <option value="5">Mejores repartidores</option>
            <option value="6">Medios de pago en pedidos urgentes</option>
          </select>
          <button class="btn-run-query" @click="runQuery" :disabled="!selectedQuery">
            Ejecutar
          </button>
        </div>
        <div class="card-body">
          <div v-if="queryLoading" class="loading-indicator">
            <i class="fas fa-spinner fa-spin"></i> Procesando consulta...
          </div>
          <div v-else-if="queryResults" class="query-results">
            <div class="results-header">
              <h3>{{ queryTitle }}</h3>
              <button class="btn-export" @click="exportToCSV">
                <i class="fas fa-file-export"></i> Exportar CSV
              </button>
            </div>
            <div class="table-responsive">
              <table class="results-table">
                <thead>
                  <tr>
                    <th v-for="(header, index) in queryHeaders" :key="index">{{ header }}</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(row, rowIndex) in queryResults" :key="rowIndex">
                    <td v-for="(cell, cellIndex) in row" :key="cellIndex">{{ cell }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div v-else class="empty-state">
            <i class="fas fa-chart-bar"></i>
            <p>Seleccione y ejecute una consulta para ver los resultados</p>
          </div>
        </div>
      </div>

      <!-- Vistas SQL como estadísticas clave -->
      <div class="card stats-section">
        <div class="card-header">
          <h2>Vistas SQL Clave</h2>
          <select v-model="selectedView" class="query-select" @change="loadView">
            <option value="" disabled>Seleccione una vista</option>
            <option value="13">Resumen de pedidos por cliente</option>
            <option value="14">Desempeño por repartidor</option>
            <option value="15">Empresas con mayor volumen</option>
          </select>
        </div>
        <div class="card-body">
          <div v-if="viewLoading" class="loading-indicator">
            <i class="fas fa-spinner fa-spin"></i> Cargando vista...
          </div>
          <div v-else-if="viewResults" class="query-results">
            <div class="results-header">
              <h3>{{ viewTitle }}</h3>
              <button class="btn-export" @click="exportViewToCSV">
                <i class="fas fa-file-export"></i> Exportar CSV
              </button>
            </div>
            <div class="table-responsive">
              <table class="results-table">
                <thead>
                  <tr>
                    <th v-for="(header, index) in viewHeaders" :key="index">{{ header }}</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(row, rowIndex) in viewResults" :key="rowIndex">
                    <td v-for="(cell, cellIndex) in row" :key="cellIndex">{{ cell }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div v-else class="empty-state">
            <i class="fas fa-table"></i>
            <p>Seleccione una vista SQL para ver los resultados</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Notificaciones recientes -->
    <div class="notifications-sidebar">
      <div class="card">
        <div class="card-header">
          <h2>Notificaciones Recientes</h2>
          <button class="btn-mark-all" @click="markAllAsRead">
            <i class="fas fa-check-double"></i> Marcar todas como leídas
          </button>
        </div>
        <div class="card-body">
          <div v-if="notifications.length === 0" class="empty-notifications">
            <i class="fas fa-bell-slash"></i>
            <p>No hay notificaciones nuevas</p>
          </div>
          <ul v-else class="notifications-list">
            <li v-for="notification in notifications" :key="notification.id" 
                :class="{'unread': !notification.read}">
              <div class="notification-icon" :class="notification.type">
                <i :class="notification.icon"></i>
              </div>
              <div class="notification-content">
                <p class="notification-text">{{ notification.text }}</p>
                <span class="notification-time">{{ notification.time }}</span>
              </div>
              <button class="btn-dismiss" @click="dismissNotification(notification.id)">
                <i class="fas fa-times"></i>
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminView',
  data() {
    return {
      lastUpdated: new Date().toLocaleTimeString(),
      stats: {
        ordersToday: 42,
        ordersChange: 5.3,
        newCustomers: 8,
        pendingDeliveries: 15,
        reportedIssues: 3,
        issuesChange: -2.1
      },
      selectedQuery: '',
      queryLoading: false,
      queryResults: null,
      queryTitle: '',
      queryHeaders: [],
      selectedView: '',
      viewLoading: false,
      viewResults: null,
      viewTitle: '',
      viewHeaders: [],
      notifications: [
        {
          id: 1,
          type: 'warning',
          icon: 'fas fa-exclamation-circle',
          text: 'Pedido #1045 retrasado - Problema con el repartidor',
          time: 'Hace 15 min',
          read: false
        },
        {
          id: 2,
          type: 'success',
          icon: 'fas fa-check-circle',
          text: 'Nuevo registro de cliente premium',
          time: 'Hace 1 hora',
          read: true
        },
        {
          id: 3,
          type: 'info',
          icon: 'fas fa-info-circle',
          text: 'Actualización del sistema programada para hoy a las 23:00',
          time: 'Hace 3 horas',
          read: true
        }
      ]
    }
  },
  methods: {
    fetchData() {
      this.lastUpdated = new Date().toLocaleTimeString()
    },
    runQuery() {
      this.queryLoading = true
      this.queryResults = null
      
      setTimeout(() => {
        switch(this.selectedQuery) {
          case '1':
            this.queryTitle = 'Cliente que más ha gastado en pedidos entregados'
            this.queryHeaders = ['Cliente', 'Total gastado', 'N° de pedidos']
            this.queryResults = [
              ['Juan Pérez', '$1,250,000', 15],
              ['María González', '$980,000', 12],
              ['Carlos Rojas', '$875,000', 10]
            ]
            break
          case '2':
            this.queryTitle = 'Productos más pedidos por categoría (último mes)'
            this.queryHeaders = ['Categoría', 'Producto', 'Cantidad']
            this.queryResults = [
              ['Documentos legales', 'Certificado de nacimiento', 45],
              ['Documentos legales', 'Escritura de propiedad', 32],
              ['Documentos académicos', 'Certificado de notas', 28]
            ]
            break
          case '3':
            this.queryTitle = 'Empresas con más entregas fallidas'
            this.queryHeaders = ['Empresa', 'Entregas fallidas', 'Porcentaje']
            this.queryResults = [
              ['Envíos Rápidos SA', 8, '12%'],
              ['Distribuidora Doc', 5, '8%'],
              ['Mensajería Express', 3, '5%']
            ]
            break
          case '4':
            this.queryTitle = 'Tiempo promedio entre pedido y entrega por repartidor'
            this.queryHeaders = ['Repartidor', 'Tiempo promedio', 'Pedidos completados']
            this.queryResults = [
              ['Pedro Sánchez', '2h 15m', 24],
              ['Ana Martínez', '2h 30m', 18],
              ['Luis Fernández', '2h 45m', 15]
            ]
            break
          case '5':
            this.queryTitle = 'Top 3 repartidores con mejor rendimiento'
            this.queryHeaders = ['Repartidor', 'Puntuación', 'Entregas exitosas']
            this.queryResults = [
              ['María López', '4.9/5', 35],
              ['Carlos Jiménez', '4.8/5', 32],
              ['Laura Gómez', '4.7/5', 28]
            ]
            break
          case '6':
            this.queryTitle = 'Medios de pago en pedidos urgentes'
            this.queryHeaders = ['Medio de pago', 'Cantidad', 'Porcentaje']
            this.queryResults = [
              ['Tarjeta de crédito', 65, '58%'],
              ['Transferencia bancaria', 32, '28%'],
              ['Efectivo', 15, '14%']
            ]
            break
        }
        this.queryLoading = false
      }, 1500)
    },
    loadView() {
      this.viewLoading = true
      this.viewResults = null
      
      setTimeout(() => {
        switch(this.selectedView) {
          case '13':
            this.viewTitle = 'Resumen de pedidos por cliente'
            this.viewHeaders = ['Cliente', 'Total gastado', 'N° de pedidos', 'Último pedido']
            this.viewResults = [
              ['Ana Vargas', '$1,450,000', 18, '2023-05-15'],
              ['Luis Méndez', '$1,200,000', 15, '2023-05-14'],
              ['Carlota Ruiz', '$980,000', 12, '2023-05-10'],
              ['Pedro Soto', '$875,000', 10, '2023-05-08']
            ]
            break
          case '14':
            this.viewTitle = 'Desempeño por repartidor'
            this.viewHeaders = ['Repartidor', 'Entregas completadas', 'Tiempo promedio', 'Calificación']
            this.viewResults = [
              ['María López', 45, '2h 10m', '4.9/5'],
              ['Carlos Jiménez', 38, '2h 25m', '4.7/5'],
              ['Laura Gómez', 32, '2h 30m', '4.6/5'],
              ['Juan Pérez', 28, '2h 45m', '4.5/5']
            ]
            break
          case '15':
            this.viewTitle = 'Empresas con mayor volumen de documentos'
            this.viewHeaders = ['Empresa', 'Documentos entregados', 'Clientes atendidos', 'Tasa de éxito']
            this.viewResults = [
              ['Envíos Express', 1250, 85, '98%'],
              ['Mensajería Rápida', 980, 72, '96%'],
              ['Distribuidora Doc', 750, 65, '95%'],
              ['Entregas Seguras', 620, 58, '94%']
            ]
            break
        }
        this.viewLoading = false
      }, 1200)
    },
    exportToCSV() {
      console.log('Exportando consulta a CSV...')
    },
    exportViewToCSV() {
      console.log('Exportando vista a CSV...')
    },
    markAllAsRead() {
      this.notifications = this.notifications.map(n => ({ ...n, read: true }))
    },
    dismissNotification(id) {
      this.notifications = this.notifications.filter(n => n.id !== id)
    }
  },
  mounted() {
    this.fetchData()
  }
}
</script>

<style scoped>
.admin-dashboard {
  display: grid;
  grid-template-columns: 1fr 300px;
  grid-template-rows: auto 1fr;
  grid-gap: 20px;
  padding: 20px;
  min-height: 100vh;
  background-color: #f5f7fa;
}

.admin-header {
  grid-column: 1 / -1;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}

.header-left h1 {
  margin: 0;
  font-size: 24px;
  color: #333;
}

.welcome-message {
  margin: 5px 0 0;
  color: #666;
  font-size: 14px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}

.refresh-btn {
  padding: 8px 15px;
  background: #3F51B5;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
}

.last-updated {
  font-size: 13px;
  color: #777;
}

.quick-stats {
  grid-column: 1 / -1;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

.stat-card {
  background: white;
  border-radius: 8px;
  padding: 20px;
  display: flex;
  align-items: center;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}

.stat-icon {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 15px;
  color: white;
  font-size: 20px;
}

.bg-primary { background: #3F51B5; }
.bg-success { background: #4CAF50; }
.bg-warning { background: #FFC107; }
.bg-danger { background: #F44336; }

.stat-info h3 {
  margin: 0 0 5px;
  font-size: 14px;
  color: #666;
}

.stat-value {
  margin: 0;
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.stat-change {
  margin: 5px 0 0;
  font-size: 12px;
}

.stat-change.positive { color: #4CAF50; }
.stat-change.negative { color: #F44336; }

.main-content {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}

.card-header {
  padding: 15px 20px;
  border-bottom: 1px solid #eee;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-header h2 {
  margin: 0;
  font-size: 18px;
}

.card-body {
  padding: 20px;
}

.query-select {
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin: 0 10px;
}

.btn-run-query {
  padding: 8px 15px;
  background: #3F51B5;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn-run-query:disabled {
  background: #ccc;
  cursor: not-allowed;
}

.loading-indicator {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
  color: #666;
  gap: 10px;
}

.empty-state {
  text-align: center;
  padding: 40px;
  color: #999;
}

.empty-state i {
  font-size: 40px;
  margin-bottom: 15px;
  color: #ddd;
}

.results-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.results-header h3 {
  margin: 0;
  font-size: 16px;
}

.btn-export {
  padding: 6px 12px;
  background: transparent;
  border: 1px solid #3F51B5;
  color: #3F51B5;
  border-radius: 4px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 5px;
  font-size: 13px;
}

.table-responsive {
  overflow-x: auto;
}

.results-table {
  width: 100%;
  border-collapse: collapse;
}

.results-table th, .results-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.results-table th {
  background: #f9f9f9;
  font-weight: 500;
  color: #555;
}

.results-table tr:hover td {
  background: #f5f5f5;
}

.stats-section .card-body {
  padding: 20px;
}

.stats-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.stat-item {
  background: #f9f9f9;
  padding: 15px;
  border-radius: 6px;
}

.stat-item h3 {
  margin-top: 0;
  color: #555;
  font-size: 16px;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}

.stats-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.stats-list li {
  padding: 8px 0;
  display: flex;
  justify-content: space-between;
}

.stat-label {
  font-weight: 500;
  color: #666;
}

.notifications-sidebar {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.notifications-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.notifications-list li {
  display: flex;
  padding: 12px 0;
  border-bottom: 1px solid #eee;
  align-items: flex-start;
}

.notifications-list li.unread {
  background: #f8f9ff;
}

.notification-icon {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 12px;
  color: white;
  flex-shrink: 0;
}

.notification-icon.warning { background: #FFC107; }
.notification-icon.success { background: #4CAF50; }
.notification-icon.info { background: #2196F3; }

.notification-content {
  flex-grow: 1;
}

.notification-text {
  margin: 0;
  font-size: 14px;
}

.notification-time {
  display: block;
  font-size: 12px;
  color: #999;
  margin-top: 3px;
}

.btn-dismiss {
  background: none;
  border: none;
  color: #999;
  cursor: pointer;
  margin-left: 10px;
}

.empty-notifications {
  text-align: center;
  padding: 40px;
  color: #999;
}

.empty-notifications i {
  font-size: 40px;
  margin-bottom: 15px;
  color: #ddd;
}

.btn-mark-all {
  background: none;
  border: none;
  color: #3F51B5;
  cursor: pointer;
  font-size: 13px;
  display: flex;
  align-items: center;
  gap: 5px;
}

</style>