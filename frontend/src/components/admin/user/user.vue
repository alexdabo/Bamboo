<template lang="html">
  <div class="container-fluid">
    <!--  LIST  -->
    <div class="row" v-show="module==='list'">
      <div class="col s12" style="margin-top:10px;">
        <!--  Search Box  -->
        <div class="container">
          <div class="row">
            <div class="col s12 flex-container">
              <input style="width:100%" v-model="search" type="search" placeholder="Buscar por nombres o cédula" class="browser-default">
              <select style="width:100%" class="browser-default hide-on-small-and-down">
                <option value="">a</option>
                <option value="">b</option>
              </select>
              <div  class="flex-container">
              <button class="btn cyan waves-effect" @click="findByData"> <i class="material-icons">search</i> </button>
              <button class="btn cyan waves-effect" @click="find"> <i class="material-icons">refresh</i> </button>
              <button class="btn cyan waves-effect" @click="module='insert'"> <i class="material-icons">add</i> </button>
            </div>
            </div>
          </div>
        </div>
        <!--  Table  -->
        <div class="row">
          <div class="col s12">
            <table class="striped cyan">
              <thead class="white-text">
                <tr>
                  <th class="hide-on-med-and-down">Rol</th>
                  <th>Cédula</th>
                  <th>Nombres</th>
                  <th class="hide-on-med-and-down">Teléfono</th>
                  <th>Dirección</th>
                  <th class="hide-on-med-and-down">Email</th>
                  <th style="max-width: 150px;min-width: 150px;text-align: center;">Acciones</th>
                </tr>
              </thead>
              <tbody class="white">
                <tr v-for="user in users">
                  <td class="hide-on-med-and-down" v-if="user.roll==='A'">Administación</td>
                  <td class="hide-on-med-and-down" v-if="user.roll==='C'">Cobros</td>
                  <td class="hide-on-med-and-down" v-if="user.roll==='R'">Recolector</td>
                  <td>{{user.dni}}</td>
                  <td>{{user.lastName}} {{user.firstName}}</td>
                  <td class="hide-on-med-and-down">{{user.telephone}}</td>
                  <td>{{user.address}}</td>
                  <td class="hide-on-med-and-down">{{user.email}}</td>
                  <td style="max-width:  150px;min-width: 150px;text-align: center;">
                      <button class="material-icons btn blue white-text" @click="module='update';userToUpdate=user;" title="Editar"  >edit</button>
                      <button class="material-icons btn red  white-text" @click="remove(user)"  title="Eliminar">delete</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

      </div>
    </div>

    <!--  INSERT  -->
    <div class="container"  v-show="module==='insert'">
      <div class="card">
        <div class="card-close cyan white-text">
            <div class="right">
                <button class="btn-flat white-text" @click="module='list'"><i class="material-icons">cancel</i></button>
            </div>
            <h5 style="padding: 0 25px">Insertar Usuario</h5>
        </div>
        <insertform/>
      </div>
    </div>

    <!--  UPDATE  -->
    <div class="container"  v-show="module==='update'">
      <div class="card">
        <div class="card-close cyan white-text">
            <div class="right">
                <button class="btn-flat white-text" @click="module='list'"><i class="material-icons">cancel</i></button>
            </div>
            <h5 style="padding: 0 25px">Editar Usuario</h5>
        </div>
        <updateform v-bind:user="userToUpdate"/>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import insertform from './insertform.vue';
import updateform from './updateform.vue';
import User from './entity.js';
import UserAjax from './ajax.js';
const userAjax = new UserAjax();
export default {
	name: 'user',
	data() {
		return {
			module: 'list',
			search: 'pepe',
			userToUpdate: new User(),
			users: []
		};
	},
	methods: {
		find() {
			userAjax.find()
				.then(res => {
					this.users = [];
					for (let i = 0; i < res.data.length; i++) {
						let user = new User();
						user.setValues(res.data[i]);
						this.users.push(user);
					}
				})
				.catch(err => {
					console.error(err.message);
				});
		},
		findByData() {
			swal('Buscar por valores');
		},
		remove(user) {
			swal({
					title: "¿Está seguro?",
					text: "Una vez que se elimine, ¡no podrá recuperar el registro de " + user.lastName + ' ' + user.firstName + "!",
					icon: "warning",
					buttons: true,
					dangerMode: true
				})
				.then((willDelete) => {
					if (willDelete) {
						userAjax.delete(user)
							.then((res) => {
								if (res.data.deleted === true) {
									this.user = new User();
									M.toast({
										html: 'Eliminado',
										classes: 'alert-success'
									});
								}
							})
							.catch((err) => {
								swal({
									text: 'El registro no se puede eliminar.',
									icon: 'error',
									buttons: true,
									dangerMode: true
								});
								console.error(err.message);
								console.error(err.response);
							});
					}
				});
		}

	},
	components: {
		insertform,
		updateform
	},
	created() {
		this.find()
	}
}
</script>

<style lang="css">
.flex-container {
  display: flex;
  align-items: stretch;
}
.card-close{
  padding: 1px 0;
  margin-bottom: 25px;
}
</style>
