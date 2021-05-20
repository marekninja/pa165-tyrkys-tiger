import axios from 'axios';
import Vue from 'vue'

const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
    login(user) {
        return axios
            .post(API_URL + 'signin', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            })
            .catch(e => {
                // TODO: toto je hack
                var token = {
                    username: "meno",
                    token: "token",
                    isAdmin: "true"
                }
                localStorage.setItem('user', JSON.stringify(token))
                return user
            })
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return axios.post(API_URL + 'signup', {
            username: user.username,
            email: user.email,
            password: user.password
        });
    }
}

export default new AuthService();

// const authServiceInstance = new AuthService()

// console.log('auth service created: ', authServiceInstance)

// Vue.prototype.$auth = authServiceInstance
// Vue.prototype.$fun = 'fun'

// export { authServiceInstance };