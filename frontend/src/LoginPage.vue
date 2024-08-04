<script setup>
import axios from 'axios';
const handleLogin = async () => {
    const loginData = {
    username: "ragbag",
    password: "ragbag"
};

axios.post('http://localhost:8080/auth/login', loginData)
    .then(response => {
        console.log('Response:', response.data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
    
}
</script>
<template>
    <div class="bg-pink-300">
        <h1>Login Page</h1>

        <br>
        <form>
            <div>
                <label for="email">Email:</label>

                <input type="email" v-model="email" required />
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" v-model="password" required />
            </div>
            <button @click="handleLogin">Login</button>
        </form>
    </div>
</template>