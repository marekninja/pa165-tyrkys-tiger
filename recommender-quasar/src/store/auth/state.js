export default function() {
    // TODO: for our purposes is prefilled
    // TODO: need user id for recommendations
    return {
        user: JSON.parse(localStorage.getItem('user')),
        status: {
            loggedIn: localStorage.getItem('user') ? true : false,
        }
    }
}