import { Post } from "./models/Post";

export const POSTS: Post[] = [
    {
        id: 1,
        createdDate: new Date(),
        message: "Mock1",
        user: {id: 1, firstName: "Michael", lastName:"Archer", email:"michael.archer@revature.net", username: "Michael", password: "pass123"}
    },
    {
        id: 2,
        createdDate: new Date(),
        message: "Mock2",
        user: {id: 2, firstName: "Tommy", lastName:"Arnette", email:"tommy.arnette@revature.net", username: "Tommy", password: "pass123"},
        likes: 15
    },
    {
        id: 3,
        createdDate: new Date(),
        message: "Mock3",
        user: {id: 3, firstName: "Milo", lastName:"Cao", email:"milo.cao@revature.net", username: "Milo", password: "pass123"},
        images: ["some url from database that corresponds to an image in s3"]
    }
];

