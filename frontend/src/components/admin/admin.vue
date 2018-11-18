<template>
<div class="wrapper">
	<!-- Sidebar  -->
	<nav id="sidebar" v-bind:class="{'active':isActive}" class="sidebar sidenav-fixed">
		<ul class="sidebar-body ">
			<li class="sidebar-logo collapsible-header ">
				<img src="../../assets/image/icon.png" width="45">

				<b class="to-hide" style="position:absolute; margin-top:10px; margin-left:10px">SimbaWater</b>
			</li>
			<li class="sidebar-item">
				<div class="collapsible-header tooltipped" @click="module='account'" data-position="right" data-tooltip="Cuentas">
					<i class="material-icons" v-bind:class="{'cyan-text':module==='account'}">account_balance_wallet</i>
					<span class="to-hide">Cuentas</span>
				</div>
			</li>
			<li class="sidebar-item">
				<div class="collapsible-header tooltipped" @click="module='user'" data-position="right" data-tooltip="Usuarios">
					<i class="material-icons" v-bind:class="{'cyan-text':module==='user'}">person</i>
					<span class=" to-hide">Usuarios</span>
				</div>
			</li>
			<li class="sidebar-item">
				<div class="collapsible-header tooltipped" @click="module='beneficiary'" data-position="right" data-tooltip="Beneficiarios">
					<i class="material-icons" v-bind:class="{'cyan-text':module==='beneficiary'}">people</i>
					<span class="to-hide">Beneficiarios</span>
				</div>
			</li>
			<li class="sidebar-item">
				<div class="collapsible-header tooltipped" @click="module='watermeter'" data-position="right" data-tooltip="Medidores">
					<i class="material-icons sidebar-icons" v-bind:class="{'cyan-text':module==='watermeter'}">widgets</i>
					<span class="to-hide">Medidores</span>
				</div>
			</li>
			<li class="sidebar-item">
				<div class="collapsible-header tooltipped" @click="module='services'" data-position="right" data-tooltip="Servicios">
					<i class="material-icons" v-bind:class="{'cyan-text':module==='services'}">format_list_bulleted</i>
					<span class="to-hide">Servicios</span>
				</div>
			</li>
			<li class="sidebar-item">
				<div class="collapsible-header tooltipped" @click="module='reports'" data-position="right" data-tooltip="Reportes">
					<i class="material-icons" v-bind:class="{'cyan-text':module==='reports'}">description</i>
					<span class="to-hide">Reportes</span>
				</div>
			</li>
		</ul>
	</nav>

	<!-- Page Content  -->
	<div id="content" v-bind:class="{'active':isActive}">
		<div class="navbar navbar-fixeda">
			<nav class="white">
				<div class="nav-wrapper">
					<div class="left">
						<a class="menu-icon black-text waves-effect menu-icon" @click="isActive=!isActive">
							<i class="material-icons">menu</i>
						</a>
					</div>
					<div class="right">
						<a class="dropdown-trigger black-text waves-effect menu-icon" data-target="account-dropdown">
							<i class="material-icons">account_circle</i>
						</a>
					</div>
				</div>
			</nav>
		</div>

		<div id='account-dropdown' class='dropdown-content white'>
			<div class="white-text text-accent-1 grey darken-2" style="padding: 0px 15px"><b>Alexander Bonilla</b></div>
			<div>
				<ul>
					<li><a class="black-text" @click="module='settings'"><i class="material-icons grey-text">settings</i>Configuraciones</a></li>
					<li><a class="black-text" @click="module='help'"><i class="material-icons grey-text">help</i>Ayuda</a></li>
					<li><a href="session?method=logout" class="black-text"> <i class="material-icons grey-text">exit_to_app</i>Salir</a></li>
				</ul>
			</div>
		</div>

		<usermodule v-if="module==='user'" />

	</div>



</div>
</template>

<script>
import usermodule from './user/user.vue'
export default {
	name: 'admin',
	data() {
		return {
			isActive: false,
			module: 'user',
		}
	},
	components: {
		usermodule
	},
	methods: {
		find() {
			swal('Hello Vue ssss');
		}
	},
	props: {
		user: Object
	}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import '../../assets/css/layout.css';

.nav-wrapper {
	padding: 0 10px;
}

.menu-icon {
	cursor: pointer;
	width: 50px;
	margin-top: -3px;
	text-align: center;
}
</style>
