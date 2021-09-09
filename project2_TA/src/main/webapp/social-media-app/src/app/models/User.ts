import { Post } from "./Post";

export interface User {
    id?: number,
    firstName: string,
    lastName: string,
    email: string,
    username: string,
    password: string,
    profileDescription?: string,
    profileImage?: string,
    posts?: Post[]
}