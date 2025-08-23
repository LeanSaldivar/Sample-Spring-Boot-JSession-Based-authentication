import apiClient from "./apiClient.tsx";
import type {UserRegistration} from "../types/UserRegistration.tsx";
import type {UserLogin} from "../types/UserLogin.tsx";

export const login = (credentials: UserLogin) => {
    return apiClient.post('/users/auth/action/login', credentials);
};

export const register = (userData: UserRegistration) => {
    return apiClient.post('/users/auth/action/register', userData);
};
