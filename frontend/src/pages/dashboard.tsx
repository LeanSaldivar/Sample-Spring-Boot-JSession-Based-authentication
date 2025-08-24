import {useNavigate} from 'react-router-dom'; // Import useHistory hook
import { useAuth } from "../api/authContext.tsx";

export const Dashboard = () => {
    const navigate = useNavigate();
    const { logout, user } = useAuth();

     const handleLogout = async () => {
         try {
             await logout();
             console.log('Logout successful:')
             navigate('/login');
         } catch (error) {
             console.error('Error logging out:', error);
         }
    };

    return (
        <>
            <h1>Dashboard</h1>
            <p>Welcome to your dashboard!</p>
            <p>You are logged in as, {user?.userName}</p>

            <button onClick={handleLogout}>Logout</button>
        </>
    )
}