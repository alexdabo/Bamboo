export default class User {
    constructor() {
        this.id = 0;
        this.userName = '';
        this.password = '';
        this.roll = '';
        this.dni = '';
        this.lastName = '';
        this.firstName = '';
        this.address = '';
        this.telephone = '';
        this.email = '';
    }

    setValues(data) {
        this.id = data.id;
        this.userName = data.userName;
        this.password = data.password;
        this.roll = data.roll;
        this.dni = data.dni;
        this.lastName = data.lastName;
        this.firstName = data.firstName;
        this.address = data.address;
        this.telephone = data.telephone;
        this.email = data.email;
    }
    getUrl() {
        var data = 'id=' + this.id;
        data += '&userName=' + this.userName;
        data += '&password=' + this.password;
        data += '&roll=' + this.roll;
        data += '&dni=' + this.dni;
        data += '&lastName=' + this.lastName;
        data += '&firstName=' + this.firstName;
        data += '&address=' + this.address;
        data += '&telephone=' + this.telephone;
        data += '&email=' + this.email;
        return data;
    }
}
