export function loginSuccess(state, user) {
    state.status.loggedIn = true;
    state.user = user;
    state.user.isAdmin = user.administrator
}

export function fullUser(state, user) {
    state.userFull = user;
    state.userFull.password = state.user.password
    state.userFull.isAdmin = user.administrator
}

export function loginFailure(state) {
    state.status.loggedIn = false;
    state.user = null;
}
export function logout(state) {
    state.status.loggedIn = false;
    state.user = null;
    state.userFull = null;
}
export function registerSuccess(state) {
    state.status.loggedIn = false;
}
export function registerFailure(state) {
    state.status.loggedIn = false;
}