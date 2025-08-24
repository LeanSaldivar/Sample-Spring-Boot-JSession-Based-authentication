import {useNavigate} from 'react-router-dom'; // Import useHistory hook
import {useState} from 'react';
import type {UserLogin} from "../types/UserLogin.ts";

export const Dashboard = () => {
    const navigate = useNavigate();
    const [formData] = useState<UserLogin>({});

     const handleLogout = async () => {
        // Perform logout actions here (e.g., clear session, remove authentication token)
        // After logout, redirect to the login page
         console.log("Logging out...")
        navigate('/');
    };

    return (
        <>
            <h1>Dashboard</h1>
            <p>Welcome to your dashboard!</p>
            <p>You are logged in as, {formData.email}</p>

            <button onClick={handleLogout}>Logout</button>
        </>
    )
}