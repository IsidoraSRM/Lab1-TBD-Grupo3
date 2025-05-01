import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'

// Import your view components
import HomeView from './views/HomeView.vue'
import LoginView from './views/LoginView.vue'

// Define your routes
const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  }
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Create the app and use the router
const app = createApp(App)
app.use(router)
app.mount('#app')