<template>
  <div>
    <h1>Home</h1>
    <button @click="goToLogin">Go to Login</button>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  methods: {
    goToLogin() {
      this.$router.push({ name: 'Login' });
    }
  }
};
</script>
