import {Routes, Route} from 'react-router-dom'; // 1. Import Routes and Route

import {Login} from './pages/Login';
import {Register} from './pages/Register';

export const App = ()=> {
    return (
        <div>
            <Routes>
                {}
                <Route index element={<Login/>}/>
                {}
                <Route path="/register" element={<Register/>}/>
                {}
                <Route path={"/login"} element={<Login/>}/>
                {}
            </Routes>
        </div>
    );
}

