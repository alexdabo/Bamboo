import Admin from '@/views/Admin.vue'



import Beneficiary        from "@/components/beneficiary/Beneficiary.vue"

const beneficiaries ={
  path: 'beneficiaries',
  name: 'Beneficiaries',
  component: Beneficiary
}

export default {
  path: '/admin',
  name: 'admin',
  component: Admin,
  children: [
    beneficiaries
  ]
}
