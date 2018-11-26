<template lang="html">
  <div class="login-screen">
    <div class="row login-container">
      <div class="col s12 z-depth-4 card-panel">
        <form class="login-form" @submit.prevent="login">
          <div class="row" style="margin-top: -60px">
            <div class="input-field col s12 center">
              <img src="../assets/image/logo.svg" alt="" class="circle responsive-img profile-image-login">
              <h1 class="center login-form-text" style="margin-top: -5px">Bamboo</h1>
            </div>
          </div>

          <div class="row margin">
            <div class="input-field col s12">
              <i class="material-icons prefix">person_outline</i>
              <input id="username" type="text" v-model='userName' required>
              <label for="username" class="center-align">Usuario o correo electrónico</label>
            </div>
          </div>

          <div class="row margin">
            <div class="input-field col s12">
              <i class="material-icons prefix">lock_outline</i>
              <input id="password" type="password" v-model='password' required="ddd">
              <label for="password">Contraseña</label>
            </div>
          </div>

          <div class="row">
            <div class="input-field col s12">
              <button class="btn waves-effect waves-light col s12">Entrar</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>

</template>

<script>
export default {
	data() {
		return {
			userName: '',
			password: ''
		};
	},
	methods: {
		login() {
			swal('Logged', this.userName);
		}
	}
}
</script>

<style>
@import '../assets/css/login.css';
</style>
