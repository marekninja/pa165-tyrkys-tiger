export default function() {
    // TODO: for our purposes is prefilled
    // TODO: need user id for recommendations
    return {
        user: {
            // User user = user("Milanko Háčik","milanko@azet.sk","milani$$",false,"heslo");
            id: 1,
            name: "Milanko Háčik",
            nickName: "milani$$",
            email: "milanko@azet.sk",
            // password: "heslo",
            administrator: false,
            passwordHash: "heslo",
            dateOfBirth: null
        }
    }
}