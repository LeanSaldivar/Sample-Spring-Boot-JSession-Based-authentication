import { createContext, useContext, useState, type ReactNode } from 'react';
import { login as apiLogin } from '../api/authService';
import { logout as apiLogout } from '../api/authService';
import type { UserLogin } from '../types/UserLogin.ts';
import type { User } from '../types/User.ts';
// You would typically have a type for the user object returned from your API
// For now, we can use `any` or a placeholder.

interface AuthContextType {
    isAuthenticated: boolean;
    user: User | null;
    login: (credentials: UserLogin) => Promise<User>;
    logout: () => Promise<void>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    const [user, setUser] = useState<User | null>(null);

    // The login function now lives in the context. It calls the API,
    // and upon success, updates the global state.
    const login = async (credentials: UserLogin) => {
        try {
            const response = await apiLogin(credentials);
            const loggedInUser = response.data;
            setUser(loggedInUser);
            // You could also persist a token here, e.g., in localStorage
            return loggedInUser;
        } catch (error) {
            // Let the calling component handle the error UI, but re-throw it
            throw error;
        }
    };

    const logout = async () => {
        try {
            await apiLogout();
            setUser(null);
            console.log('Logout successful:');
        } catch (error) {
            console.error('Logout failed:', error);
            throw error;
        }
    };

    const value = { isAuthenticated: !!user, user, login, logout };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};