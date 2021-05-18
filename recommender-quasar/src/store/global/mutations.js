export function saveNickname(state, nickname) {
    state.user.nickname = nickname
}

export function savePassword(state, pass) {
    state.user.pass = pass
}

export function setAdmin(state, isAdmin) {
    state.user.isAdministrator = isAdmin
}

export function saveEmail(state, email) {
    state.user.email = email
}