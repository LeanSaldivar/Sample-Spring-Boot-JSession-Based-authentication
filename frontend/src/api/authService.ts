import {apiClient} from "./apiClient.ts";
import type {UserRegistration} from "../types/UserRegistration.ts";
import type {UserLogin} from "../types/UserLogin.ts";

export const login = (credentials: UserLogin) => {
    return apiClient.post('/users/auth/action/login', credentials);
};

export const register = (userData: UserRegistration) => {
    return apiClient.post('/users/auth/action/register', userData);
};
// To be used later
// export const logout = (cookie: UserLogin) => {
//     return apiClient.delete('/users/auth/action/logout', cookie);
// }
