import axios from 'axios';
import Vue from 'vue'

// const API_URL = 'http://localhost:8080/pa165/rest/users/';
const API_URL = '/users/';

class AuthService {
    login(user) {
        return Vue.prototype.$axios.post(API_URL + 'authentication', {
                nickName: user.username,
                password: user.password
            })
            .then(response => {
                if (response.data) {
                    response.data.user.isAdmin = response.data.user.administrator
                    localStorage.setItem('user', JSON.stringify(response.data.user));
                }
                return response.data.user;
            })
            // .catch(e => {
            //     // TODO: toto je hack
            //     // var token = {
            //     //     username: user.username,
            //     //     token: "token",
            //     //     isAdmin: "true"
            //     // }
            //     localStorage.setItem('user', JSON.stringify(token))
            //     return token
            //         // return user
            // })
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
