import { default as axios } from 'axios';

async function get() {
    const response = await axios.get('http://localhost:8000/api/users');
    console.log(response);
    return response;
}

export  {get};