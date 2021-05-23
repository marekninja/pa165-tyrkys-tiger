export default function() {
    // TODO: for our purposes is prefilled
    // TODO: need user id for recommendations
    return {
        //TODO: user NULL on start
        user: JSON.parse(localStorage.getItem('user')),
        userFull: null,
        status: {
            loggedIn: localStorage.getItem('user') ? true : false,
        }
    }
}