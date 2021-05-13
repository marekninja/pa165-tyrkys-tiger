import Vue from 'vue'
import axios from 'axios'

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/pa165/api/v1'
})

Vue.prototype.$axios = axiosInstance

export { axiosInstance }