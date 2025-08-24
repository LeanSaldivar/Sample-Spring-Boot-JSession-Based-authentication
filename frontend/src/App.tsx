import {Routes, Route} from 'react-router-dom'; // 1. Import Routes and Route

import {Login} from './pages/Login';
import {Register} from './pages/Register';
import {Dashboard} from "./pages/dashboard.tsx";
import {AuthProvider} from "./api/authContext.tsx";

export const App = () => {
    return (
        <AuthProvider>
            <div>
                <Routes>
                    <Route index element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route path={"/login"} element={<Login/>}/>
                    <Route path={"/dashboard"} element={<Dashboard/>}/>
                </Routes>
            </div>
        </AuthProvider>
    );
}

