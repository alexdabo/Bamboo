// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import wal from 'sweetalert';
import M from 'materialize-css';
import vfs from 'pdfmake/build/vfs_fonts';
import pdf from 'pdfmake/build/pdfmake';


document.addEventListener('DOMContentLoaded', function () {
    var dropdown = document.querySelectorAll('.dropdown-trigger');
    M.Dropdown.init(dropdown, {coverTrigger: false, constrainWidth: false});
    var collapsible = document.querySelectorAll('.collapsible');
    M.Collapsible.init(collapsible);
    var sidenav = document.querySelectorAll('.sidenav');
    M.Sidenav.init(sidenav);
    var datepicker = document.querySelectorAll('.datepicker');
    M.Datepicker.init(datepicker, {format: 'yyyy-mm-dd', setDefaultDate: true, defaultDate: new Date()});//mmm dd, yyyy
    var modal = document.querySelectorAll('.modal');
    M.Modal.init(modal);
    var select = document.querySelectorAll('select');
    M.FormSelect.init(select);
});

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
