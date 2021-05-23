import axios from 'axios';
import Vue from 'vue'

const API_URL = 'http://localhost:8080/pa165/rest/users/';

class AuthService {
    login(user) {
        return axios
            .post(API_URL + 'authentication', {
                nickname: user.username,
                password: user.password
            })
            .then(response => {
                if (response.data) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                return response.data;
            })
            .catch(e => {
                // TODO: toto je hack
                var token = {
                    username: user.username,
                    token: "token",
                    isAdmin: "true"
                }
                localStorage.setItem('user', JSON.stringify(token))
                return token
                    // return user
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