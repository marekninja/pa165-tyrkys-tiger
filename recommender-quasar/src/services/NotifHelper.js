import { Notify } from 'quasar'

class NotifHelper {
    notifyPosit(message) {
        Notify.create({
            color: 'green-4',
            textColor: 'white',
            icon: 'cloud_done',
            message: message
        })
    }

    notifyNegat(message) {
        Notify.create({
            color: 'negative',
            textColor: 'white',
            icon: 'error',
            message: message
        })
    }

}

export default new NotifHelper;