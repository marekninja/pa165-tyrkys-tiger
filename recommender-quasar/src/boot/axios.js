import Vue from 'vue'
import axios from 'axios'

const axiosInstance = axios.create({
    // baseURL: 'http://localhost:8080/pa165/api/v1'
    baseURL: '/pa165/api/v1'
})

// axiosInstance.defaults.headers['Pragma'] = 'no-cache';
// axiosInstance.defaults.headers['Cache-Control'] = 'no-cache';
// axiosInstance.defaults.headers['Expires'] = '0';

// 'Cache-Control': 'no-cache',
// axiosInstance.defaults.headers = {
//     'Cache-Control': 'no-cache',
//     'Pragma': 'no-cache',
//     'Expires': '0',
// };

Vue.prototype.$axios = axiosInstance

export { axiosInstance }