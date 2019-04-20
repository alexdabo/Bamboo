import Role from '@/model/entity/Role'

export default class User {
    public id?: number
    public email?: string
    public userName?: string
    public password?: string
    public lastName?: string
    public firstName?: string
    public dni?: string
    public role: Role = new Role()
    public telephone?: string
    public address?: string
}
