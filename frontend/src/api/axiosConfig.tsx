import { default as axios } from 'axios';

const API_BASE_URL = 'http://localhost:8080';
// @ts-ignore
const API_LOGIN_URL = "http://localhost:8080/api/v2/users/auth/action/login"
// @ts-ignore
const API_REGISTER_URL = "http://localhost:8080/api/v2/users/auth/action/register"
// @ts-ignore
const API_REGISTER_URL = "http://localhost:8080/api/v2/users/auth/action/register"
// @ts-ignore
const API_GET_USER_URL = "http://localhost:8080/api/v2/users/query/{id}"

const JSessionId = axios.create({
    withCredentials: true,
    baseURL: API_BASE_URL,
    timeout: 1000,
    headers: {
        'Content-Type': 'application/json',
    }
});


export  {JSessionId};