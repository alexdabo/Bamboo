<template>
<div>
	<v-toolbar flat color="white">
		<v-toolbar-title>BENEFICIARIOS</v-toolbar-title>
		<v-divider class="mx-2" inset vertical></v-divider>
		<v-spacer></v-spacer>
		<v-text-field solo v-model="search" label="Buscar" append-icon="search" hide-details clearable></v-text-field>
		<v-spacer></v-spacer>
		<v-btn color="primary" class="mb-2" icon @click="findBeneficiaries()">
			<v-icon>refresh</v-icon>
		</v-btn>
		<v-btn color="primary" class="mb-2" icon>
			<v-icon>file_download</v-icon>
		</v-btn>
		<v-dialog v-model="dialog" persistent max-width="700px">
			<v-btn slot="activator" color="primary" icon class="mb-2">
				<v-icon>add</v-icon>
			</v-btn>
			<v-card>

				<v-card-title class="primary lighten-3  white--text">
					<span class="headline" v-if="editedIndex === -1">Nuevo Beneficiario</span>
					<span class="headline" v-else>Editar Beneficiario</span>
				</v-card-title>
				<form @submit.prevent="submit">
					<v-container grid-list-sm class="pa-4">
						<v-layout row wrap>
							<v-flex xs12 sm6>
								<v-text-field label="Apellidos" required v-model="editedItem.lastName" />
							</v-flex>
							<v-flex xs12 sm6>
								<v-text-field label="Nombres" required v-model="editedItem.firstName" />
							</v-flex>
							<v-flex xs12 sm6>
								<v-text-field label="Cédula" required v-model="editedItem.dni" required />
							</v-flex>
							<v-flex xs12 sm6>
								<v-text-field type="tel" label="Teléfono" required v-model="editedItem.telephone" mask="phone" />
							</v-flex>
							<v-flex xs12>
								<v-select :items="villages" label="Comunidad" required v-model="editedItem.village" item-text="name" item-value="id" return-object></v-select>
							</v-flex>
							<v-flex xs12>
								<v-text-field label="Dirrección" required v-model="editedItem.address" />
							</v-flex>
							<v-flex xs12>
								<v-text-field label="Lugar de referencia" required v-model="editedItem.placeReference" />
							</v-flex>
						</v-layout>
					</v-container>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="error" @click.native="close">
							<v-icon left>cancel</v-icon>Cancelar
						</v-btn>
						<v-btn color="primary" type="submit">
							<v-icon left>save</v-icon>Guardar
						</v-btn>
					</v-card-actions>
				</form>
			</v-card>
		</v-dialog>
	</v-toolbar>
	<v-data-table :headers="headers" :items="beneficiaries" :search="search" hide-actions class="elevation-1">
		<template slot="items" slot-scope="props">
			<td>{{ props.item.lastName }}</td>
			<td>{{ props.item.firstName }}</td>
			<td class="text-xs-left">{{ props.item.dni }}</td>
			<td class="text-xs-left">{{ props.item.village.name }}</td>
			<td class="text-xs-left">{{ props.item.telephone }}</td>
			<td class="text-xs-left">{{ props.item.address }}</td>
			<td class="text-xs-left">{{ props.item.placeReference }}</td>
			<td class="justify-center layout px-0">
				<v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
				<v-icon small @click="remove(props.item)">delete</v-icon>
			</td>
		</template>
		<v-alert slot="no-results" :value="true" color="error" icon="warning">
			No se encontro resultados para "{{ search }}".
		</v-alert>
	</v-data-table>
	<v-toast :toast="toast" />
</div>
</template>

<script lang="ts">
import Beneficiary from '@/components/beneficiary/Beneficiary.ts'
export default Beneficiary
</script>
