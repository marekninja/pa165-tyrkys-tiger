import AuthService from '../../services/AuthService'

export function login(state, user) {
    return AuthService.login(user).then(
        // return this.$auth.login(user).then(
        userOut => {
            //   commit('loginSuccess', user);
            state.commit('loginSuccess', userOut);
            return Promise.resolve(userOut);
        },
        error => {
            //   commit('loginFailure');
            state.commit('loginFailure');
            return Promise.reject(error);
        }
    );
}

// export function logout({ commit }) {
export function logout(state) {
    AuthService.logout();
    //   commit('logout');
    state.commit('logout');
}

// export function  register({ commit }, user) {
export function register(state, user) {
    return AuthService.register(user).then(
        response => {
            //   commit('registerSuccess');
            state.commit('registerSuccess');
            return Promise.resolve(response.data);
        },
        error => {
            // commit('registerFailure');
            state.commit('registerFailure');
            return Promise.reject(error);
        }
    );
}