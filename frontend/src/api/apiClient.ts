import {default as axios} from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/v2';

export const apiClient = axios.create({
    baseURL: API_BASE_URL, // Base URL for all API calls
    withCredentials: true, // This is the crucial part for sending cookies like JSESSIONID
});