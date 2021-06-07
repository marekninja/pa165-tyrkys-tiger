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
            message: 'Could not fullfill request'
        })
    }

    notifyNegatResp(error) {
        console.log(JSON.stringify(error, null, 1))
        this.message =
            (error.response && error.response.data) ||
            error.message || error.toString();
        this.notifyNegat('Could not fullfill request');
    }

}

export default new NotifHelper;