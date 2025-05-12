import axios from 'axios';

const API_URL = 'http://localhost:8080/api/empresas';

export default {
  getCompanyRanking() {
    return axios.get(`${API_URL}/top-ranking`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('authToken')}` // Requiere autenticación ADMIN
      }
    });
  } 
};
