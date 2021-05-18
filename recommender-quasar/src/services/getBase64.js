export default function getBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader()
            // reader.onloadend = (e) => resolve(imageToDataUri(e, 400, 400))
        reader.readAsDataURL(file)
        reader.onload = () => resolve(/base64,(.+)/.exec(reader.result)[1])
        reader.onerror = error => reject(error)
    })
}