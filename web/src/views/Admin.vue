<template>
<v-app id="inspire">
	<!--SIDEBAR-->
	<v-navigation-drawer v-model="drawer" clipped fixed app class="primary lighten-5">
		<v-list>
			<side-bar-item icon="dashboard" title="dashboard" link="/admin/dashboard" />
			<side-bar-item icon="account_balance_wallet" title="Cuentas" link="/admin/accounts" />
			<side-bar-item icon="person" title="Usuarios" link="/admin/users" />
			<side-bar-item icon="people" title="Beneficiarios" link="/admin/beneficiaries" />
			<side-bar-item icon="widgets" title="Medidores" link="/admin/measurers" />
			<side-bar-item icon="format_list_bulleted" title="Servicios" link="/admin/services" />
			<side-bar-item icon="description" title="Reportes" link="/admin/reports" />
		</v-list>
	</v-navigation-drawer>

	<!--TOOLBAR-->
	<v-toolbar app fixed clipped-left color="primary">
		<v-toolbar-side-icon @click.stop="drawer = !drawer" color="primary"></v-toolbar-side-icon>
		<v-toolbar-title class="white--text">Bamboo</v-toolbar-title>
		<v-spacer></v-spacer>
		<v-btn icon color="primary" @click="toggleFullscreen()">
			<v-icon v-if="fullscreen===false">fullscreen</v-icon>
			<v-icon v-if="fullscreen===true">fullscreen_exit</v-icon>
		</v-btn>

		<v-menu offset-y origin="top bottom" :nudge-bottom="10" transition="scale-transition">
			<v-btn icon large flat slot="activator" class="primary">
				<v-icon>account_circle</v-icon>
			</v-btn>
			<v-card class="elevation-0">
				<v-toolbar card dense color="transparent">
					<v-toolbar-title>
						<h5>Alexander Bonilla</h5>
					</v-toolbar-title>
				</v-toolbar>
				<v-divider></v-divider>
				<v-card-text class="pa-0">
					<v-list dense>
						<side-bar-item class="pa-1" icon="settings" title="Configuraciones" link="/admin/settings" />
						<v-divider></v-divider>
						<side-bar-item class="pa-1" icon="logout" title="Salir" link="/login" />
						<v-divider></v-divider>
						<side-bar-item class="pa-1" icon="info" title="Ayuda" link="/admin/help" />
					</v-list>
				</v-card-text>
			</v-card>
		</v-menu>

	</v-toolbar>

	<!--CONTAINER-->
	<v-content>
		<router-view tag="v-container" fluid fill-height>

		</router-view>
	</v-content>
</v-app>
</template>

<script>
import SideBarItem from '@/components/widget/SideBarItem';
import Tmp from '@/components/widget/NotificationList';
export default {
	data: () => ({
		drawer: true,
		fullscreen: false,
		items: [{
				title: 'Configuraciones',
				color: 'light-green',
				icon: 'settings',
				timeLabel: ''
			},
			{
				divider: true,
				inset: true
			},
			{
				title: 'New order received',
				color: 'light-blue',
				icon: 'shopping_cart',
				timeLabel: ''
			},
			{
				divider: true,
				inset: true
			},
			{
				title: 'New payment made',
				color: 'cyan',
				icon: 'payment',
				timeLabel: ''
			}
		]
	}),
	components: {
		SideBarItem,
		Tmp
	},
	methods: {
		toggleFullscreen() {
			let doc = window.document;
			let docEl = doc.documentElement;

			let requestFullScreen = docEl.requestFullscreen || docEl.mozRequestFullScreen || docEl.webkitRequestFullScreen || docEl.msRequestFullscreen;
			let cancelFullScreen = doc.exitFullscreen || doc.mozCancelFullScreen || doc.webkitExitFullscreen || doc.msExitFullscreen;

			if (!doc.fullscreenElement && !doc.mozFullScreenElement && !doc.webkitFullscreenElement && !doc.msFullscreenElement) {
				requestFullScreen.call(docEl);
				this.fullscreen = true;
			} else {
				cancelFullScreen.call(doc);
				this.fullscreen = false;
			}
		}
	},
	props: {
		source: String
	}
}
</script>
