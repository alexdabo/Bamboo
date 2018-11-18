import axios from 'axios';
export default class UserAjax {
    constructor(url='/user') {
        this.url = url;
        this.header = {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=utf-8',
            'userid': '',
        };

    }
    setUserId(userid) {
        this.header.userid = userid;
    }

    save(object) {
        const requestBody = object;
        return axios.post(this.url, requestBody, {headers: this.header});
    }
    find() {
        return axios.get(this.url, {headers: this.header});
    }
    update(object) {
        const requestBody = object;
        return axios.put(this.url, requestBody, {headers: this.header});
    }
    delete(object) {
        const requestBody = object.getUrl();
        return axios.delete(this.url, requestBody, {headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
            'userid': ''
          }
        });
    }
    findAndLogin() {
        const requestBody = null;
        this.header.method = 'findAndLogin';
        return axios.post(this.url, requestBody, {headers: this.header});
    }
    findByData(data) {
        const requestBody = 'data=' + data;
        this.header.method = 'findByData';
        return axios.post(this.url, requestBody, {headers: this.header});
    }
    findById(id) {
        const requestBody = 'id=' + id;
        this.header.method = 'findById';
        return axios.post(this.url, requestBody, {headers: this.header});
    }
    updatePass(userName, password, newpassword) {
        var requestBody = 'userName=' + userName;
        requestBody += '&password=' + password;
        requestBody += '&newPassword=' + newpassword;
        this.header.method = 'updatePass';
        return axios.post(this.url, requestBody, {headers: this.header});
    }

}
