<template>
<div id="app">
	<admin />
</div>
</template>

<script>
import admin from './components/admin/admin.vue'

export default {
	name: 'App',
	components: {
		admin
	}
}
</script>

<style>
@import "../node_modules/materialize-css/dist/css/materialize.min.css";
@import "../node_modules/material-design-icons/iconfont/material-icons.css";
@import "assets/css/inputs.css";

.alert-danger {
	color: #721c24;
	padding: 10px;
	margin: -20px 0px 10px 0px;
	border-radius: 5px;
	background-color: #f8d7da;
	border-color: #f5c6cb;
}

.alert-success {
	color: #155724;
	padding: 10px;
	margin: -20px 0px 10px 0px;
	border-radius: 5px;
	background-color: #d4edda;
	border-color: #c3e6cb;
}
</style>
