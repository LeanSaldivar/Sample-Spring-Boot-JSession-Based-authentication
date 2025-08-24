import {apiClient} from "./apiClient.ts";
import type {UserRegistration} from "../types/UserRegistration.ts";
import type {UserLogin} from "../types/UserLogin.ts";

export const login = (credentials: UserLogin) => {
    return apiClient.post('/users/auth/action/login', credentials);
};

export const register = (userData: UserRegistration) => {
    return apiClient.post('/users/auth/action/register', userData);
};

export const logout = () => {
    return apiClient.post('/users/auth/action/logout');
}
