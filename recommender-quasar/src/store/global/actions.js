export function saveNickname(state, nickname) {
    state.commit('saveNickname', nickname)
}

export function savePassword(state, pass) {
    state.commit('savePassword', pass)
}

export function setAdmin(state, isAdmin) {
    state.commit('setAdmin', isAdmin)
}

export function saveEmail(state, email) {
    state.commit('saveEmail', email)
}