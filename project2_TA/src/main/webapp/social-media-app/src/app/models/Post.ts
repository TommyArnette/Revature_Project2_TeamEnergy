import { User } from "./User";

export interface Post {
    id?: number,
    createdDate: Date,
    message: string,
    user: User,
    likes?: number,
    images?: string[]
}