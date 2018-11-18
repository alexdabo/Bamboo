<template lang="html">
  <form @submit.prevent="save">
    <div class="row">
      <div class="col s12 m2 "><b>Cédula</b></div>
      <div class="col s12 m10 ">
        <input type="text" class="browser-default"  required v-model="user.dni" maxlength="10" minlength="10" placeholder="Ejm.:0812345678" pattern="[0-9]{10}" title="Un número de cédula válido debe tener 10 números.">
      </div>
    </div>
    <div class="row">
      <div class="col s12 m2 "><b>Apellidos</b></div>
      <div class="col s12 m10 ">
        <input type="text" class="browser-default"  required v-model="user.lastName">
      </div>
    </div>

    <div class="row">
      <div class="col s12 m2 "><b>Nombres</b></div>
      <div class="col s12 m10 ">
        <input type="text" class="browser-default"  required  v-model="user.firstName">
      </div>
    </div>

    <div class="row">
      <div class="col s12 m2 "><b>Teléfono</b></div>
      <div class="col s12 m10 ">
        <input type="text" class="browser-default"  v-model="user.telephone" maxlength="10" minlength="7" placeholder="Ejm.: 2123456 o 032123456" pattern="[0-9]{10}{7}" title="Ingrese un teléfono válido">
      </div>
    </div>

    <div class="row">
      <div class="col s12 m2 "><b>Roll de usuario</b></div>
      <div class="col s12 m10 ">
        <select class="browser-default"  v-model="user.roll">
          <option value="" disabled selected></option>
          <option value="A" >Administrador</option>
          <option value="C" >Cobros</option>
          <option value="R" >Recolector</option>
        </select>
      </div>
    </div>

    <div class="row">
      <div class="col s12 m2 "><b>Dirección</b></div>
      <div class="col s12 m10 ">
        <input type="text" class="browser-default"  required  v-model="user.address">
      </div>
    </div>
    <div class="row">
      <div class="col s12 m2 "><b>Correo electrónico</b></div>
      <div class="col s12 m10 ">
        <input type="email" class="browser-default"  required v-model="user.email" >
      </div>
    </div>

    <div class="row">
      <div class=" col s12">
        <div class="right">
          <button class="btn cyan  waves-effect waves-light " type="submit"><i class="material-icons left">save</i>Guardar</button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import User from './entity.js';
import UserAjax from './ajax.js';
export default {
	name: 'insertuser',
	data() {
		return {
			user: new User()
		};
	},
	methods: {
		save() {
			let ajax = new UserAjax();
			ajax.save(this.user)
				.then((res) => {
					if (res.data.saved === true) {
						this.user = new User();
						M.toast({
							html: 'Guardado',
							classes: 'alert-success'
						});
					}
				})
				.catch((err) => {
					M.toast({
						html: err.message,
						classes: 'alert-danger'
					});
				});
		}
	},
}
</script>

<style lang="css">
</style>
